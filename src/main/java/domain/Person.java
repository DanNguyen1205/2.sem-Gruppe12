package domain;

import java.util.UUID;

public class Person {
    private String uniqueID = UUID.randomUUID().toString();
    private String name;
    private String email;
    private int phoneNumber;

    public Person(String name, String email, int phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //Get ID
    public String getUniqueID(){
        return this.uniqueID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "uniqueID='" + uniqueID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
