package com.example.hrinterface.service;

import com.example.hrinterface.dao.DigitalDocumentDAO;
import com.example.hrinterface.dao.PersonalDocumentDAO;
import com.example.hrinterface.entity.DigitalDocument;
import com.example.hrinterface.entity.PersonalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    PersonalDocumentDAO personalDocumentDAO;

    @Autowired
    DigitalDocumentDAO digitalDocumentDAO;

    public PersonalDocument findDocumentByID(int ID){
        try{
            return personalDocumentDAO.findDocumentByID(ID);
        }catch (Exception e){System.out.println(e);}
        return null;
    }

    public List<PersonalDocument> getDocsByEmployeeId(int id){
        try{
            return personalDocumentDAO.getDocsByEmployeeId(id);
        }catch (Exception e){System.out.println(e);}
        return null;
    }

    public DigitalDocument getDigitalDocumentById(Integer digitalDocId){
        try {
            return digitalDocumentDAO.getDigitalDocById(digitalDocId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //modify
    public void addNewDigitalDoc(DigitalDocument digitalDocument){
        try {
            digitalDocumentDAO.addNewDocument(digitalDocument);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //modify
    public void updateDigitalDoc(DigitalDocument digitalDocument){
        if(digitalDocument == null){
            return;
        }
        digitalDocumentDAO.updateDigitalDoc(digitalDocument);
    }
}
