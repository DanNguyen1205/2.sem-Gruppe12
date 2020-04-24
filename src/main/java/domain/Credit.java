package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class Credit implements Serializable{
    private String finalString;
    private String uniqueID = UUID.randomUUID().toString();
    private HashMap<Person, String> creditMap = new HashMap<Person, String>();

    Credit(HashMap creditMap){
        this.creditMap=creditMap;

    }

    public void add(Person person, String role){
        creditMap.put(person, role);

    }

    public HashMap getCreditMap(){
        return this.creditMap;
    }

    public void setCreditMap(){
        this.creditMap = creditMap;
    }

    @Override
    public String toString() {
        finalString = "";
        creditMap.forEach((key, value) -> {
            finalString += value + ": " + key + "\n";
        });
        return finalString;
    }


}
