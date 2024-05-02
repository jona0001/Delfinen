package domain_model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Delfin {

    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();

    //TODO:add price depending on a membership
    public boolean addMember(String name, int age, String membership){
        Membership newMembership = new Membership(1, 500, LocalDateTime.now());
        if(membership.equals("active") && age > 60){
            newMembership.setMembershipType(MembershipType.ACTIVE_SENIOR);
        }else if(membership.equals("passive") && age > 60){
            newMembership.setMembershipType(MembershipType.PASSIVE_SENIOR);
        }else if(membership.equals("active") && age < 60){
            newMembership.setMembershipType(MembershipType.ACTIVE_JUNIOR);
        }else{
            newMembership.setMembershipType(MembershipType.PASSIVE_JUNIOR);
        }
        Member newMember = new Member(age, name);
        newMember.setMembership(newMembership);
        boolean isAdded = members.add(newMember);
        return isAdded;
    }


}
