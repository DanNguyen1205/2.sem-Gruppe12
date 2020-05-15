package data;

import java.io.Serializable;

public class Person implements Serializable{
    private long id;
    private String name;
    private String email;
    private String role;

    public Person(String name, String email){
        this.name = name;
        this.email = email;
    }

    public Person(String name, String email, String role){
        this.name = name;
        this.email = email;
        this.role = role;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return "Name: " + this.name + ", Role: " + this.role;
    }
}

