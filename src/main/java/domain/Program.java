package domain;

import java.util.HashMap;
import java.util.UUID;

public class Program {
    private String uniqueID = UUID.randomUUID().toString();
    private String name;
    private Credit credits;
    private String releaseDate;
    private String producer;



    Program(String uniqueID, String name, Credit credits, String releaseDate, String producer){
        this.uniqueID = uniqueID;
        this.name = name;
        this.credits = credits;
        this.releaseDate = releaseDate;
        this.producer = producer;
    }


    //Getter and setter for uniqueID
    public String getUniqueID(){
        return this.uniqueID;
    }

    public void setUniqueID(){
        this.uniqueID = uniqueID;
    }


    //Getter and setter for name attribute
    public String getName(){
        return this.name;
    }

    public void setName(){
        this.name = name;
    }

    //Getter and setter for credits attribute
    public HashMap getCredits(){
        return this.credits.getCreditMap();
    }

    public void setCredits(){
        this.credits = credits;
    }


    //Getter and setter for releaseData attribute
    public String getReleaseDate(){
        return this.releaseDate;
    }

    public void setReleaseDate(){
        this.releaseDate = releaseDate;
    }


    //Getter and setter for producer
    public String getProducer(){
        return this.producer;
    }

    public void setProducer(){
        this.producer = producer;
    }




}
