package com.example.team1.service;

import com.example.team1.DAO.Dao.PersonalDocumentDao;
import com.example.team1.domain.PersonalDocsDomain;
import com.example.team1.entity.Employee;
import com.example.team1.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalDocService {

    @Autowired
    private PersonalDocumentDao personalDocumentDao;

    @Transactional
    public PersonalDocument getPersonalDocById(Integer id){
        Optional<PersonalDocument> optional = Optional.ofNullable(personalDocumentDao.getDocumentById(id));
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Transactional
    public List<PersonalDocsDomain> getPersonalDocInfoByEmployeeId(Employee employee){
        List<PersonalDocsDomain> domains = new ArrayList<>();
        List<PersonalDocument> documents = personalDocumentDao.getDocsByEmployeeId(employee);
        if(documents!=null && documents.size()>0){
            for(PersonalDocument docs : documents){
                PersonalDocsDomain personalDocsDomain = new PersonalDocsDomain(docs.getTitle(),
                        docs.getPath(), docs.getComment());
                domains.add(personalDocsDomain);
            }
        }
        return domains;
    }

    @Transactional
    public void updatePersonalDocs(List<PersonalDocsDomain> docsDomains, Employee employee){
        List<PersonalDocument> documents = personalDocumentDao.getDocsByEmployeeId(employee);
        if(documents!=null && !documents.isEmpty() && docsDomains!=null && !docsDomains.isEmpty()){
            for(int i = 0;i<docsDomains.size();i++){
                PersonalDocsDomain domain = docsDomains.get(i);
                if(i<documents.size()){
                    PersonalDocument doc = documents.get(i);
                    doc.setComment(domain.getComment());
                    doc.setTitle(domain.getTitle());
                    doc.setPath(domain.getPath());
                    personalDocumentDao.update(doc);
                }else {
                    addNewDocs(domain, employee);
                }
            }
        }
    }

    @Transactional
    public void addNewDocs(PersonalDocsDomain docsDomain, Employee employee){
        PersonalDocument document = new PersonalDocument();
        document.setEmployee(employee);
        document.setTitle(docsDomain.getTitle());
        document.setComment(docsDomain.getComment());
        document.setPath(docsDomain.getPath());
        personalDocumentDao.addDocs(document);
    }
}

