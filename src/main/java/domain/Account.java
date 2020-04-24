package domain;

public class Account{
    String username;
    String password;

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + "\n" + "Password: " + getPassword() + "\n";
    }
}
