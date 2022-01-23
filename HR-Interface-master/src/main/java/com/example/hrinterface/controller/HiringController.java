package com.example.hrinterface.controller;

import com.example.hrinterface.constant.JwtConstant;
import com.example.hrinterface.dao.UserRoleDAO;
import com.example.hrinterface.domain.ProfileDetail;
import com.example.hrinterface.domain.RegistrationProfile;
import com.example.hrinterface.entity.Employee;
import com.example.hrinterface.entity.RegistrationToken;
import com.example.hrinterface.entity.UserRole;
import com.example.hrinterface.security.util.HiringJwtUtil;
import com.example.hrinterface.service.DocumentService;
import com.example.hrinterface.service.EmailService;
import com.example.hrinterface.service.HRService;
import com.example.hrinterface.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class HiringController {
    @Autowired
    HRService hrService;
    @Autowired
    ProfileService profileService;
    @Autowired
    EmailService emailService;
    @Autowired
    DocumentService documentService;

    @PostMapping("/api/hr/token")
    public String generateToken(@RequestBody Map<String, Object> payload){
        if(!payload.containsKey("email")){
            return "error";
        }
        else{
            Date expiration = Date.from(ZonedDateTime.now().plusMinutes(JwtConstant.JWT_VALID_DURATION).toInstant());
            String email = (String)payload.get("email");
            String subject = email + expiration.toString();
            String token = HiringJwtUtil.generateToken(subject, JwtConstant.JWT_VALID_DURATION);
            RegistrationToken registrationToken = new RegistrationToken();
            registrationToken.setToken(token);
            registrationToken.setEmail(email);
            registrationToken.setValidDuration(expiration.toString());
            hrService.createToken(registrationToken);
            System.out.println(hrService.findToken(token));
            sendEmailForRegistration("click this link to register: " + "http://localhost:9999/auth/register/"+token, email);
            return "http://localhost:9999/auth/register/"+token;
        }
    }

    public void sendEmailForRegistration(String link, String email){
        emailService.sendEmail(email, "Registration", link);
    }

    @PostMapping("/api/hr/sendnotification/{id}")
    public boolean sendNotification(@PathVariable Integer id){
        try {
            emailService.sendEmail(profileService.findPersonByID(profileService.findEmployeeByID(id).getID()).getEmail(),
                    "Work Authorization Expire Notification", "Your work authorization is almost expired");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @GetMapping("/api/hr/registration")
    public List<RegistrationProfile> sendRegList(){
        List<UserRole> userRoleList = profileService.getRegList();
        List<RegistrationProfile> registrationProfileList = new ArrayList<>();
        for(int i=0; i<userRoleList.size(); i++){
            registrationProfileList.add(new RegistrationProfile(userRoleList.get(i), profileService.findEmployeebyUserID(userRoleList.get(i).getUserID())));
        }
        return registrationProfileList;
    }

    @GetMapping("/api/hr/reg-info/{id}")
    public Object getEmployeeDetail(@PathVariable(required = false)Integer id){
        if(id == null){
            return "error";
        }
        Employee e = profileService.findEmployeebyUserID(id);
        if(e == null){
            return "error";
        }
        else {
            ProfileDetail pd = new ProfileDetail(e, profileService.getContactById(e.getID()), profileService.getAddressByPersonId(e.getID()), documentService.getDocsByEmployeeId(e.getID()));
            return pd;
        }
    }

    @PostMapping("api/hr/reg-review/{id}")
    public Object reviewApplication(@PathVariable Integer id, @RequestBody Map<String, Object> payload){
        if(!payload.containsKey("review")){
            return "error";
        }
        String decision = (String)payload.get("review");
        UserRole userRole = profileService.findUserRoleByID(id);
        if(decision.equals("approve")){
            userRole.setStatus("Completed");
            profileService.updateUserRole(userRole);
            return userRole;
        }
        else if(decision.equals("reject")){
            userRole.setStatus("Rejected");
            profileService.updateUserRole(userRole);
            return userRole;
        }
        else{
            return "error";
        }
    }
}
