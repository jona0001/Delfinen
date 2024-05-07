package controller;

import data_source.FileHandler;
import domain_model.CompetingMember;
import domain_model.Delfin;
import domain_model.Member;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private Delfin delfin;
    private FileHandler fileHandler = new FileHandler();

    public Controller() {
        this.delfin = new Delfin(fileHandler.loadMembers());
        delfin.setCompetingMembers(fileHandler.loadCompetingMembers());
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

    public int getUpcomingRevenue(){
        return delfin.getUpcomingRevenue();
    }

    public ArrayList<CompetingMember> getCompetingSwimmers() {
        return delfin.getCompetingMembers();
    }
    /*
    public List <Member>getCompetingSwimmers(){
        return delfin.getCompetingSwimmers();
    }

     */
}
