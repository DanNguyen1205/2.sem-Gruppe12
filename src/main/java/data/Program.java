package data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

public class Program implements Serializable{
    private long id;
    private String name;
    private Credit credits;
    private String releaseDate;
    private String producer;



    public Program(String name, Credit credits, String releaseDate, String producer){
        this.name = name;
        this.credits = credits;
        this.releaseDate = releaseDate;
        this.producer = producer;
    }

    // constructor for when u make a progrma without its credits.
    public Program(String name, String releaseDate, String producer){
        this.name = name;
        this.releaseDate = releaseDate;
        this.producer = producer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //Getter and setter for name attribute
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    //Getter and setter for credits attribute
    public Credit getCredit(){
        return this.credits;
    }

    public void setCredits(Credit credit){
        this.credits = credit;
    }


    //Getter and setter for releaseData attribute
    public String getReleaseDate(){
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }


    //Getter and setter for producer
    public String getProducer(){
        return this.producer;
    }

    public void setProducer(String producer){
        this.producer = producer;
    }

    @Override
    public String toString() {
        return name;
    }

    public String showCredit()
    {
        String finalString = "";
        return finalString = "Kreditter til " + name + ":" +" \n" + credits;
    }

}
