package data;

import data.Person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Credit{
    private String finalString;
    HashMap<Person, String> creditMap = new HashMap<>();

    public Credit(HashMap creditMap){
        this.creditMap=creditMap;

    }

    public Credit(){
    }

    public void add(Person person, String role){
        creditMap.put(person, role);

    }

    public HashMap<Person, String> getCreditMap(){
        return this.creditMap;
    }

    public void setCreditMap(HashMap<Person, String> tempMap){
        this.creditMap = creditMap;
    }

    @Override
    public String toString() {
        String returnString = "";
        for(Map.Entry<Person, String> entry : creditMap.entrySet())
        {
            returnString += "Person: " + entry.getKey().getName() + ", Role: " + entry.getValue() + "\n";
        }
        return returnString;
    }


}
