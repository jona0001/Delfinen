package domain_model;
import data_source.FileHandler;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Delfin {

    private ArrayList<Member> members;
    private ArrayList<Team> teams = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();
    private ArrayList<CompetingMember> competingMembers;

    public Delfin(ArrayList<Member> members) {
        this.members = members;
    }

    public void setCompetingMembers(ArrayList<CompetingMember> competingMembers) {
        this.competingMembers = competingMembers;
    }

    public ArrayList<CompetingMember> getCompetingMembers() {
        return competingMembers;
    }

    //TODO:add price depending on a membership
    public boolean addMember(String name, int age, String membership, String discipline) throws FileNotFoundException {
        Membership newMembership = new Membership(LocalDateTime.now());
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
            newMember = new CompetingMember(name, age, discipline1);
        } else{
            newMember = new Member(name, age);
        }
        newMember.setMembership(newMembership);
        newMembership.setMember(newMember);
        newMembership.setPrice();
        boolean isAdded = members.add(newMember);
        if(newMember instanceof CompetingMember) competingMembers.add((CompetingMember) newMember);
        fileHandler.saveOneMember(newMember);
        return isAdded;
    }

    public ArrayList<Member> getAllMembers(){
        return members;
    }

    public ArrayList<CompetingMember> loadCompeting(){
        ArrayList<CompetingMember> competingMembers = fileHandler.loadCompetingMembers();
        return competingMembers;
    }

    public List<Member> getDebtors(){
        List<Member> debtors = new ArrayList<>();
        for(Member member : members){
            if(!member.getMembership().isPaid()){
                debtors.add(member);
            }
        }
        return debtors;
    }

    public int getUpcomingRevenue(){
        List<Membership> memberships = getMemberships();
        int revenue = 0;
        for(Membership membership : memberships){
            if(membership.getCancellationDate()==null){
                revenue += membership.getPrice();
            }
        };
        return revenue;
    }

    public List<Membership> getMemberships(){
        List<Membership> memberships = new ArrayList<>();
        for(Member member : members){
            memberships.add(member.getMembership());
        }
        return memberships;
    }

    public List<CompetingMember> getSortedMembershiptype(MembershipType membershiptype ) {
        List<CompetingMember> sortCompetingMemember = new ArrayList<>();
        for(CompetingMember member : competingMembers) {
            if (member.getMembership().getMembershipType().equals(membershiptype)) {
                sortCompetingMemember.add(member);
            }
        }
        return sortCompetingMemember;
    }
    public List<CompetingMember> getMembersByDisciplin(Discipline disciplinType) {
        List<Discipline> sortDisciplin = new ArrayList<>();
        for(Discipline type : )
    }
}

    /*
    public List<Member> getCompetingSwimmers() {
        List<Member> competingSwimmers = new ArrayList<>();
        for (Member member : members) {


            }
        }
        return competingSwimmers;
    }

     */

