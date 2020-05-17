package data;

public class personToCredit {
    private long id;
    private String role;
    private long productions_fk;
    private long persons_fk;

    public personToCredit(long id, String role, long productions_fk, long persons_fk) {
        this.id = id;
        this.role = role;
        this.productions_fk = productions_fk;
        this.persons_fk = persons_fk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getProductions_fk() {
        return productions_fk;
    }

    public void setProductions_fk(long productions_fk) {
        this.productions_fk = productions_fk;
    }

    public long getPersons_fk() {
        return persons_fk;
    }

    public void setPersons_fk(long persons_fk) {
        this.persons_fk = persons_fk;
    }
}
