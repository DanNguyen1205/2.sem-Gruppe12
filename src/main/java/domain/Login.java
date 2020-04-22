package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    private ArrayList<Account> accountsList;


    public void makeAccounts() {
        try {
            accountsList = new ArrayList<>();
            //Scanner to read file, to get every account information (username and pass)
            File accountFile = new File("C:\\Users\\nguye\\Documents\\GitHub\\2.sem-Gruppe12\\AccountFiles.txt");
            Scanner fileScanner = new Scanner(accountFile);

            //The loop reads the file and makes account classes.
            while(fileScanner.hasNext())
            {
                String tempString = fileScanner.nextLine();
                String[] tempArray = tempString.split(";");
                Account tempAccount = new Account(tempArray[0], tempArray[1]);
                accountsList.add(tempAccount);

            }
        } catch (FileNotFoundException e) {
            System.out.println("This shit don goofed");
        }

    }

    public ArrayList<Account> getAccountsList() {
        return accountsList;
    }
}
