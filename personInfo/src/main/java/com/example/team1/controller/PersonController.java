package com.example.team1.controller;

import com.example.team1.domain.*;
import com.example.team1.entity.Employee;
import com.example.team1.entity.Person;
import com.example.team1.exception.PersonInfoNotFoundException;
import com.example.team1.response.UploadFileResponse;
import com.example.team1.service.*;
import com.example.team1.service.FileStorage.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apiuser/personInfo")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadRestController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private PersonalDocService personalDocService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/")
    public ResponseEntity<PersonInfoDomain> getPersonalInfo(@RequestParam("id") Integer personId){
        Person person = personService.findPersonById(personId);
        PersonInfoDomain res = new PersonInfoDomain();
        if(person!=null){
            Employee employee = employeeService.getEmployeeByPersonId(person);
            if(employee!= null){
                res.setFirstName(person.getFirstName());
                res.setLastName(person.getLastName());
                res.setMiddleName(person.getMiddleName());
                res.setAvatar(employee.getAvatar());
                res.setGender(person.getGender());
                res.setDriverLicense(employee.getDriverLicense());
                res.setDriverLicense(employee.getDriverLicenseExpirationDate());
                res.setEmail(person.getEmail());
                res.setCellPhone(person.getCellPhone());
                res.setAlternatePhone(person.getAlternatePhone());
                EmployeeDomain employeeDomain = employeeService.getEmployeeInfoByPersonId(person);
                List<AddressDomain> addressList = addressService.getAddressByPersonId(person);
                List<EmergencyContactDomain> emergencyContactDomains = contactService.getEmergencyByPersonId(person);
                List<PersonalDocsDomain> docsDomains = personalDocService.getPersonalDocInfoByEmployeeId(employee);
                res.setAddressList(addressList);
                res.setDocumentList(docsDomains);
                res.setEmployment(employeeDomain);
                res.setContact(emergencyContactDomains);
                res.setId(personId);
                res.setSSN(person.getSsn());
                res.setDob(person.getDOB());
                return ResponseEntity.ok(res);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/updateAddress")
    public ResponseEntity<List<AddressDomain>> updateAddress(@RequestBody List<AddressDomain> addressDomains,
                                                       @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        if(person==null){
            throw new PersonInfoNotFoundException("Person with id: "+personId+" does not exist.");
        }
        addressService.updateAddress(addressDomains, person);
        return ResponseEntity.ok(addressDomains);
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<EmployeeDomain> updateEmployee(@RequestBody EmployeeDomain employeeDomain, @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        if(person==null){
            throw new PersonInfoNotFoundException("Person with id: "+personId+" does not exist.");
        }
        employeeService.updateEmployee(employeeDomain, person);
        return ResponseEntity.ok(employeeDomain);
    }

    @PostMapping("/updateDoc")
    public ResponseEntity<PersonalDocsDomain> updateDoc(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "comment", required = false)String comment,
            @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        if(person==null){
            throw new PersonInfoNotFoundException("Person with id: "+personId+" does not exist.");
        }
        Employee employee = employeeService.getEmployeeByPersonId(person);
        if(employee==null){
            return ResponseEntity.notFound().build();
        }
        UploadFileResponse response = uploadFile(file);
        PersonalDocsDomain docsDomain = PersonalDocsDomain.builder()
                .comment(comment)
                .path(response.getPath())
                .title(response.getFileName()).build();
        personalDocService.updatePersonalDoc(docsDomain, employee);
        return ResponseEntity.ok(docsDomain);
    }

    @PostMapping("/updateContact")
    public ResponseEntity<ContactDomain> updateContact(@RequestBody ContactDomain contactDomain, @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        personService.updateContact(contactDomain, personId);
        return ResponseEntity.ok(contactDomain);
    }

    @PostMapping("/updateEmergency")
    public ResponseEntity<EmergencyContactDomain> updateEmergency(
            @RequestBody EmergencyContactDomain contactDomain,
            @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        if(person==null){
            if(person==null){
                throw new PersonInfoNotFoundException("Person with id: "+personId+" does not exist.");
            }
        }
        contactService.updateEmergency(contactDomain, person);
        return ResponseEntity.ok(contactDomain);
    }

    @PostMapping("/updatePersonInfo")
    public ResponseEntity<UpdatePersonInfo> updatePerson(
            @RequestBody UpdatePersonInfo updatePersonInfo,
            @RequestParam(name = "id", required = false) Integer personId){
        if(personId==null && updatePersonInfo.getId()!=null){
            personService.updateInfo(updatePersonInfo);
            return ResponseEntity.ok(updatePersonInfo);
        }else if(personId<=0 || updatePersonInfo.getId()==null || updatePersonInfo.getId()<=0){
            return ResponseEntity.notFound().build();
        }
        personService.updateInfoWithGivenId(updatePersonInfo, personId);
        return ResponseEntity.ok(updatePersonInfo);
    }

    @PostMapping("/addPersonalDocs")
    public ResponseEntity<List<PersonalDocsDomain>> addNewDocs(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("id") Integer personId){
        if(personId<=0){
            return ResponseEntity.notFound().build();
        }
        Person person = personService.findPersonById(personId);
        if(person==null){
            throw new PersonInfoNotFoundException("Person with id: "+personId+" does not exist.");
        }
        Employee employee = employeeService.getEmployeeByPersonId(person);
        if(person==null || employee==null){
            return ResponseEntity.notFound().build();
        }
        List<PersonalDocsDomain> docsDomainList = new ArrayList<>();
        List<UploadFileResponse> responses = uploadMultipleFiles(files);
        for(UploadFileResponse response : responses){
            docsDomainList.add(PersonalDocsDomain.builder()
                    .title(response.getFileName())
                    .path(response.getPath())
                    .build());
        }
        personalDocService.addNewDocs(docsDomainList, employee);
        return ResponseEntity.ok(docsDomainList);
    }

    //file uploading
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    //download file
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //upload multiple file
    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
}
