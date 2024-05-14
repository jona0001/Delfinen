package controller;

import data_source.FileHandler;
import domain_model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private Delfin delfin;
    private FileHandler fileHandler;

    public Controller() {
        this.fileHandler = new FileHandler();
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

    public void loadFromFile(){
        fileHandler.loadCompetingMembers();
        fileHandler.loadMembers();
    }

    public List<CompetingMember> sortMembers(MembershipType membershipType, Discipline discipline) {
        return delfin.getSortedMembershiptype(membershipType, discipline);
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
