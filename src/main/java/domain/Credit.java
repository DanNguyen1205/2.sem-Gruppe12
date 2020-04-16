package domain;

import java.util.HashMap;
import java.util.UUID;

public class Credit {
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


}
