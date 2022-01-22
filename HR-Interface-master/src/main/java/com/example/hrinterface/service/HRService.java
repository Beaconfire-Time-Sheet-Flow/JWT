package com.example.hrinterface.service;

import com.example.hrinterface.dao.RegistrationTokenDAO;
import com.example.hrinterface.entity.RegistrationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRService {
    @Autowired
    RegistrationTokenDAO registrationTokenDAO;

    public void createToken(RegistrationToken registrationToken){
        try{
            registrationTokenDAO.createToken(registrationToken);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public RegistrationToken findToken(String token){
        try{
            return registrationTokenDAO.findTokenByToken(token);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
