package domain_model;

import data_source.FileHandler;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Delfin {

    private ArrayList<Member> members;
    private ArrayList<Team> teams = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();

    public Delfin(ArrayList<Member> members) {
        this.members = members;
    }

    //TODO:add price depending on a membership
    public boolean addMember(String name, int age, String membership, String discipline) throws FileNotFoundException {
        Membership newMembership = new Membership(1, 0, LocalDateTime.now());
        if(membership.equals("active") && age > 18){
            newMembership.setMembershipType(MembershipType.ACTIVE_SENIOR);
        }else if(membership.equals("passive") && age > 18){
            newMembership.setMembershipType(MembershipType.PASSIVE_SENIOR);
        }else if(membership.equals("active") && age < 18){
            newMembership.setMembershipType(MembershipType.ACTIVE_JUNIOR);
        }else{
            newMembership.setMembershipType(MembershipType.PASSIVE_JUNIOR);
        }

        Discipline discipline1 = null;
        switch (discipline.toLowerCase()){
            case "crawl" -> discipline1 = Discipline.CRAWL;
            case "back crawl", "backcrawl" -> discipline1 = Discipline.BACK_CRAWL;
            case "butterfly" -> discipline1 = Discipline.BUTTERFLY;
            case "breast stroke", "breaststroke" -> discipline1 = Discipline.BREAST_STROKE;
        }
        Member newMember;
        if(membership.equalsIgnoreCase("active")){
            newMember = new CompetingMember(age, name, discipline1);
        } else{
            newMember = new Member(name, age);
        }
        newMember.setMembership(newMembership);
        newMembership.setMember(newMember);
        newMembership.setPrice();
        boolean isAdded = members.add(newMember);
        fileHandler.saveOneMember(newMember);
        return isAdded;
    }

    public ArrayList<Member> getAllMembers(){
        return members;
    }


}
