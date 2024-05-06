package controller;

import data_source.FileHandler;
import domain_model.Delfin;
import domain_model.Member;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Delfin delfin;
    private FileHandler fileHandler = new FileHandler();

    public Controller() {
        this.delfin = new Delfin(fileHandler.loadMembers());
    }

    public boolean addMember(String name, int age, String membership, String discipline) throws FileNotFoundException {
        return delfin.addMember(name, age, membership, discipline);
    }


    public ArrayList<Member> getAllMembers() {
        return delfin.getAllMembers();
    }

    public List<Member> getDebtors(){
        return delfin.getDebtors();
    }
}
