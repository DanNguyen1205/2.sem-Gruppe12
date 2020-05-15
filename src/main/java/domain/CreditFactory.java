package domain;

import data.Credit;
import data.Person;
import javafx.collections.ObservableList;

import java.util.HashMap;

public class CreditFactory {
    public CreditFactory() {
    }

    //Method for creating the credit.
    public Credit createCredit(HashMap<Person, String> creditMap)
    {
        Credit returnCredit = new Credit(creditMap);
        return returnCredit;
    }

    //Method for creating the map to put into the credit.
    public HashMap<Person, String> createCreditMap(ObservableList<Person> observableList)
    {
        HashMap<Person, String> returnMap = new HashMap<Person, String>();

        for(Person e : observableList)
        {
            returnMap.put(e, e.getRole());
        }
        return returnMap;
    }
}
