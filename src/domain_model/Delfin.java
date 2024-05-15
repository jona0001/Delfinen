package domain_model;
import data_source.FileHandler;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Delfin {

    private ArrayList<Member> members;
    private ArrayList<Team> teams = new ArrayList<>();

    Team juniorCrawlTeam = new Team(new Trainer("Kristoffer Kristoffersen"), Discipline.CRAWL);
    Team juniorBackCrawlTeam = new Team(new Trainer("Jonathan Nakskov"), Discipline.BACK_CRAWL);
    Team juniorButterflyTeam = new Team(new Trainer("Luna Szipli"), Discipline.BUTTERFLY);
    Team juniorBreastStrokeTeam = new Team(new Trainer("Viktoria Aaris"), Discipline.BREAST_STROKE);

    Team seniorCrawlTeam = new Team(new Trainer("Kristoffer Kristoffersen"), Discipline.CRAWL);
    Team seniorBackCrawlTeam = new Team(new Trainer("Jonathan Nakskov"), Discipline.BACK_CRAWL);
    Team seniorButterflyTeam = new Team(new Trainer("Luna Szipli"), Discipline.BUTTERFLY);
    Team seniorBreastStrokeTeam = new Team(new Trainer("Viktoria Aaris"), Discipline.BREAST_STROKE);

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

    //TODO:refactor this so our method arent so big and our program rely so much about on a single method/function.
    public boolean addMember(String name, int age, String membership, Discipline discipline) throws FileNotFoundException {
        Membership newMembership = new Membership(LocalDate.now());
        if(membership.equals("active") && age > 18){
            newMembership.setMembershipType(MembershipType.ACTIVE_SENIOR);
        }else if(membership.equals("passive") && age > 18){
            newMembership.setMembershipType(MembershipType.PASSIVE_SENIOR);
        }else if(membership.equals("active") && age < 18){
            newMembership.setMembershipType(MembershipType.ACTIVE_JUNIOR);
        }else{
            newMembership.setMembershipType(MembershipType.PASSIVE_JUNIOR);
        }

        Member newMember;
        if(membership.equalsIgnoreCase("active")){
            newMember = new CompetingMember(name, age, discipline);
            //teams.get(0).addCompetingMembers((CompetingMember) newMember);
        } else{
            newMember = new Member(name, age);
        }
        newMember.setMembership(newMembership);
        newMembership.setMember(newMember);
        newMembership.setPrice();
        boolean isAdded = members.add(newMember);
        if(newMember instanceof CompetingMember){
            competingMembers.add((CompetingMember) newMember);
            addMemberToTeam((CompetingMember) newMember);
        }
        fileHandler.saveOneMember(newMember);
        return isAdded;
    }


//TODO need to refactor this giant pile of if statements, we can use HashMaps or something else.
    public void addMemberToTeam(CompetingMember competingMember){
        if(competingMember.getMembership().getMembershipType() == MembershipType.ACTIVE_JUNIOR &&
        competingMember.getSwimmingDiscipline() == Discipline.BACK_CRAWL){
            juniorBackCrawlTeam.addCompetingMembers(competingMember);
        }else if(competingMember.getMembership().getMembershipType() == MembershipType.ACTIVE_JUNIOR &&
                competingMember.getSwimmingDiscipline() == Discipline.CRAWL){
            juniorCrawlTeam.addCompetingMembers(competingMember);
        }else if(competingMember.getMembership().getMembershipType() == MembershipType.ACTIVE_JUNIOR &&
                competingMember.getSwimmingDiscipline() == Discipline.BUTTERFLY){
            juniorButterflyTeam.addCompetingMembers(competingMember);
        } else if (competingMember.getMembership().getMembershipType() == MembershipType.ACTIVE_JUNIOR &&
                competingMember.getSwimmingDiscipline() == Discipline.BREAST_STROKE){
            juniorBreastStrokeTeam.addCompetingMembers(competingMember);
        }else if (competingMember.getMembership().getMembershipType() == MembershipType.ACTIVE_SENIOR &&
                competingMember.getSwimmingDiscipline() == Discipline.BACK_CRAWL) {
            seniorBackCrawlTeam.addCompetingMembers(competingMember);
        }else if (competingMember.getMembership().getMembershipType() == MembershipType.ACTIVE_SENIOR &&
                competingMember.getSwimmingDiscipline() == Discipline.BREAST_STROKE) {
            seniorBreastStrokeTeam.addCompetingMembers(competingMember);
        }else if (competingMember.getMembership().getMembershipType() == MembershipType.ACTIVE_SENIOR &&
                competingMember.getSwimmingDiscipline() == Discipline.BUTTERFLY) {
            seniorButterflyTeam.addCompetingMembers(competingMember);
        }else if (competingMember.getMembership().getMembershipType() == MembershipType.ACTIVE_SENIOR &&
                competingMember.getSwimmingDiscipline() == Discipline.CRAWL) {
            seniorCrawlTeam.addCompetingMembers(competingMember);
        }
    }

    public ArrayList<Member> getAllMembers(){
        return members;
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

    public List<CompetingMember> getMembersByAgeAndDiscipline(MembershipType membershiptype, Discipline discipline ) {
        List<CompetingMember> sortCompetingMember = new ArrayList<>();
        List<CompetingMember> sortCompetingMemberDiscipline = new ArrayList<>();

        for (CompetingMember member : competingMembers) {
            if (member.getMembership().getMembershipType().equals(membershiptype)) {
                sortCompetingMember.add(member);
            }
        }

        for (CompetingMember member : sortCompetingMember) {
            if (member.getSwimmingDiscipline().equals(discipline)) {
                sortCompetingMemberDiscipline.add(member);
            }
        }
        return sortCompetingMemberDiscipline;
    }

    public boolean addTrainingResult(int swimmerNumber, String date, double result) throws FileNotFoundException {

        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        Result resultToSave = new Result(competingMembers.get(swimmerNumber), swimmerNumber, result, localDateTime);
        boolean isAdded = competingMembers.get(swimmerNumber).addTrainingResult(resultToSave);
        fileHandler.saveTrainingResult(resultToSave);
        return isAdded;
    }


    public List<Result> getTrainingResults(int swimmerNumber) {
        competingMembers.get(swimmerNumber).setTrainingResults(fileHandler.loadResults(swimmerNumber));
        return competingMembers.get(swimmerNumber).getTrainingResults();
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

