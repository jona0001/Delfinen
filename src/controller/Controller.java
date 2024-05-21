package controller;

import data_source.FileHandler;
import domain_model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Delfin delfin;
    private FileHandler fileHandler;

    public Controller() {
        this.fileHandler = new FileHandler();
        this.delfin = new Delfin(fileHandler.loadMembers());
        delfin.setCompetingMembers(fileHandler.loadCompetingMembers());
        delfin.setResults(fileHandler.loadResults());
        delfin.setCompetitions(fileHandler.loadCompetitionResults());
        delfin.pairMembersAndResults();
        delfin.pairMembersAndCompetitionResults();
    }

    public boolean addMember(String name, int age, String membership, int discipline) throws FileNotFoundException {
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
        fileHandler.loadCompetitionResults();
    }

    public Team getTeams(MembershipType membershipType, Discipline discipline) {
        return delfin.getTeams(membershipType, discipline);
    }



    public ArrayList<CompetingMember> getCompetingSwimmers() {
        return delfin.getCompetingMembers();
    }

    public void addTrainingResult(int swimmerNumber, String date, double result) throws FileNotFoundException {
        delfin.addTrainingResult(swimmerNumber, date, result);
    }

    public Result getTrainingResults(int swimmerNumber) {
        return delfin.getTrainingResults(swimmerNumber);
    }

    public List<CompetingMember> showTopSwimmers(MembershipType membershipType, Discipline discipline) {
        return delfin.getTopSwimmers(membershipType, discipline);
    }


    public void setCompetitionEvent(String competingMember, String venue, int ranking, double time) throws FileNotFoundException {
        delfin.addCompetitionEvent(competingMember, venue, ranking, time);
    }

    public ArrayList<Competition> showCompetition() {
        return delfin.getCompetitions();
    }
}