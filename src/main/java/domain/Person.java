package domain;

import java.io.Serializable;
import java.util.UUID;

public class Person implements Serializable{
    private String uniqueID = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String phoneNumber;



    private String role;

    public Person(String name, String email, String phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //Get ID
    public String getUniqueID(){
        return this.uniqueID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Role: " + getRole() + " Name: " + getName();
    }
}

