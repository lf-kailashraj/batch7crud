package com.lftechnology.batch7crud.validator;
import java.util.Map;
/**
 * Created by sagarmatha on 1/29/16.
 */
public class FormValidator { // NOSONAR
    private Map em;

    public FormValidator(Map<String, String> errorMessege) {
        em=errorMessege;
    }

    public boolean validateInput(Map<String, String> data) {
        em.clear();
        if(fnameCheck(data.get("firstName")) || lnameCheck(data.get("lastName")) // NOSONAR
                || ageCheck(data.get("age")) || addressCheck(data.get("address")))
            return false;
        else
            return true;
        }
    private boolean fnameCheck(String firstName){
        if(firstName == null || firstName ==""){
            em.put("fname","first name cannot be empty");
            return true;
        } else{
            return false;
        }
    }
    private boolean lnameCheck(String lastName){
        if(lastName == null || lastName ==""){
            em.put("lname","last name cannot be empty");
            return true;
        } else{
            return false;
        }
    }
    private boolean ageCheck(String age){
        if(age == null || age ==""){
            em.put("age", "age cannot be null");
            return true;
        } else {
            int ageData = Integer.parseInt(age);
            if (ageData <= 2 || ageData > 30) {
                em.put("age", "invalid age range");
                return true;
            } else {
                return false;
            }
        }
    }
    private boolean addressCheck(String address){
        if(address == null || address ==""){
            em.put("address","address cannot be empty");
            return true;
        } else{
            return false;
        }
    }
}