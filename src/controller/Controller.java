package controller;

import domain_model.Delfin;
import domain_model.Member;

import java.io.FileNotFoundException;

public class Controller {
    private Delfin delfin = new Delfin();


    public boolean addMember(String name, int age, String membership, String discipline) throws FileNotFoundException {
        return delfin.addMember(name, age, membership, discipline);
    }

}
