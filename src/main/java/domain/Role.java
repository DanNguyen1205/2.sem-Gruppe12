package domain;

public class Role {
    private String name;
    private String description;


    public Role(String name, String description) {
        this.name = name;
        this.description = description;

    }


    //Getter and setter for name attribute
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    //Getter and setter for description attribute
    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;

    }
}
