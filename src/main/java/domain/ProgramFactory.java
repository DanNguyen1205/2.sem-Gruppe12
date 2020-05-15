package domain;

import data.Credit;
import data.PersistenceHandler;
import data.Program;

import java.sql.SQLException;

public class ProgramFactory {
    PersistenceHandler persistenceHandler;

    //No arg
    public ProgramFactory() {
       persistenceHandler  = PersistenceHandler.getInstance();
    }

    public Program createProgram(String name, String releaseDate, String producer)
    {
        Program returnProgram = new Program(name,  releaseDate, producer);
        return returnProgram;
    }

    public void setCreditToProgram(Credit credit, Program program)
    {
        program.setCredits(credit);
    }

    //A method for calling the add program method from PersistanceHandler.
    public void addProgram(Program program) {
        try {
            persistenceHandler.addToDatabase(program);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
