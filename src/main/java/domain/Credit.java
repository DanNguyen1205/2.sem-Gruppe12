package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class Credit implements Serializable {
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
        return printMap(this.creditMap);
    }

    public static String printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        String finalString = "";

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String tempString;
            tempString = String.format("%s = %s", pair.getKey(), pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            finalString = finalString + tempString;
        }
        return finalString;
    }
}
