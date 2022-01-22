package com.example.hrinterface.service;

import com.example.hrinterface.dao.ApplicationWorkflowDAO;
import com.example.hrinterface.dao.VisaStatusDAO;
import com.example.hrinterface.entity.ApplicationWorkflow;
import com.example.hrinterface.entity.VisaStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisaService {
    @Autowired
    VisaStatusDAO visaStatusDAO;
    @Autowired
    ApplicationWorkflowDAO applicationWorkflowDAO;

    public VisaStatus getVisaById(int ID){
        try{
            return visaStatusDAO.getVisaById(ID);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public VisaStatus getVisaByUserId(int ID){
        try{
            return visaStatusDAO.getVisaByUserId(ID);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public ApplicationWorkflow getWorkflowById(int ID){
        try{
            return applicationWorkflowDAO.getWorkflowById(ID);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ApplicationWorkflow getWorkflowByEmployeeId(int ID){
        try{
            return applicationWorkflowDAO.getWorkflowByEmployeeId(ID);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void updateVisaType(int id, String visaType){
        try{
            visaStatusDAO.updateVisaType(id, visaType);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateApplicationWorkflow(ApplicationWorkflow applicationWorkflow){
        try {
            applicationWorkflowDAO.updateApplicationWorkflow(applicationWorkflow);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
