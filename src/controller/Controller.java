package controller;

import data_source.FileHandler;
import domain_model.Delfin;
import domain_model.Member;

import java.io.FileNotFoundException;

public class Controller {
    private Delfin delfin;
    private FileHandler fileHandler = new FileHandler();

    public Controller() {
        this.delfin = new Delfin(fileHandler.loadMembers());
    }

    public boolean addMember(String name, int age, String membership, String discipline) throws FileNotFoundException {
        return delfin.addMember(name, age, membership, discipline);
    }

    public void loadMembersFromFile() {
        fileHandler.loadMembers();
    }
}
