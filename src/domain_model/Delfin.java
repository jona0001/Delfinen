package domain_model;
import data_source.FileHandler;
import utility.CompetingMemberComparator;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Delfin {

    private ArrayList<Member> members;
    private ArrayList<Result> results;
    private HashMap<String, Team> teams = new HashMap<>();
    private ArrayList<Competition> competitions;
    private FileHandler fileHandler = new FileHandler();
    private ArrayList<CompetingMember> competingMembers;

    public Delfin(ArrayList<Member> members) {
        this.members = members;
        createTeams();
    }

    public void createTeams(){
        Team juniorCrawlTeam = new Team(new Trainer("Kristoffer Kristoffersen"), Discipline.CRAWL);
        Team juniorBackCrawlTeam = new Team(new Trainer("Jonathan Nakskov"), Discipline.BACK_CRAWL);
        Team juniorButterflyTeam = new Team(new Trainer("Luna Szipli"), Discipline.BUTTERFLY);
        Team juniorBreastStrokeTeam = new Team(new Trainer("Viktoria Aaris"), Discipline.BREAST_STROKE);

        Team seniorCrawlTeam = new Team(new Trainer("Kristoffer Kristoffersen"), Discipline.CRAWL);
        Team seniorBackCrawlTeam = new Team(new Trainer("Jonathan Nakskov"), Discipline.BACK_CRAWL);
        Team seniorButterflyTeam = new Team(new Trainer("Luna Szipli"), Discipline.BUTTERFLY);
        Team seniorBreastStrokeTeam = new Team(new Trainer("Viktoria Aaris"), Discipline.BREAST_STROKE);

        teams.put("JuniorCrawlTeam", juniorCrawlTeam);
        teams.put("JuniorBackCrawlTeam", juniorBackCrawlTeam);
        teams.put("JuniorButterflyTeam", juniorButterflyTeam);
        teams.put("JuniorBreastStrokeTeam", juniorBreastStrokeTeam);
        teams.put("SeniorCrawlTeam", seniorCrawlTeam);
        teams.put("SeniorBackCrawlTeam", seniorBackCrawlTeam);
        teams.put("SeniorButterflyTeam", seniorButterflyTeam);
        teams.put("SeniorBreastStrokeTeam", seniorBreastStrokeTeam);
    }

    public void pairMembersAndResults() {
        for (CompetingMember competingMember : competingMembers) {
            for (Result result : results) {
                if (competingMember.getCompetingId() == result.getCompetingMemberId()) {
                    competingMember.setTrainingResult(result);
                }
            }
        }
    }

    public void pairMembersAndCompetitionResults() {
        for (CompetingMember competingMember : competingMembers) {
            for (Competition competition : competitions) {
                if (competingMember.getCompetingId() == competition.getCompetingMember().getCompetingId()) {
                    competingMember.setCompetitionResult(competition);
                }
            }
        }
    }


    public void setCompetingMembers(ArrayList<CompetingMember> competingMembers) {
        this.competingMembers = competingMembers;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }


    public ArrayList<CompetingMember> getCompetingMembers() {
        return competingMembers;
    }

    public boolean addMember(String name, int age, String membership, int disciplineInput) throws FileNotFoundException {
        Membership newMembership = createNewMembership(membership, age);
        Discipline discipline = null;
        switch (disciplineInput) {
            case 1 -> discipline = Discipline.BUTTERFLY;
            case 2 -> discipline = Discipline.CRAWL;
            case 3 -> discipline = Discipline.BREAST_STROKE;
            case 4 -> discipline = Discipline.BACK_CRAWL;
        }
        Member newMember;
        if(membership.equalsIgnoreCase("active")){
            newMember = new CompetingMember(name, age, discipline);
        } else {
            newMember = new Member(name, age);
        }
        // Call the addMemberToSystem method to handle the addition
        return addMemberToSystem(newMember, newMembership);
    }

    public Membership createNewMembership(String membership, int age){
        Membership newMembership = new Membership(LocalDate.now());
        if(membership.equalsIgnoreCase("active") && age > 18){
            newMembership.setMembershipType(MembershipType.ACTIVE_SENIOR);
        } else if(membership.equalsIgnoreCase("passive") && age > 18){
            newMembership.setMembershipType(MembershipType.PASSIVE_SENIOR);
        } else if(membership.equalsIgnoreCase("active") && age < 18){
            newMembership.setMembershipType(MembershipType.ACTIVE_JUNIOR);
        } else {
            newMembership.setMembershipType(MembershipType.PASSIVE_JUNIOR);
        }
        return newMembership;
    }

    private boolean addMemberToSystem(Member newMember, Membership newMembership) throws FileNotFoundException {
        newMember.setMembership(newMembership);
        newMembership.setMember(newMember);
        newMembership.setPrice();
        boolean isAdded = members.add(newMember);
        if (newMember instanceof CompetingMember) {
            competingMembers.add((CompetingMember) newMember);
            ((CompetingMember) newMember).setCompetingId(competingMembers.size()-1);
            addMemberToTeam((CompetingMember) newMember);
        }
        fileHandler.saveOneMember(newMember);
        return isAdded;
    }

    public void addMemberToTeam(CompetingMember competingMember) {
        MembershipType membershipType = competingMember.getMembership().getMembershipType();
        Discipline swimmingDiscipline = competingMember.getSwimmingDiscipline();

        switch (membershipType) {
            case ACTIVE_JUNIOR:
                switch (swimmingDiscipline) {
                    case BACK_CRAWL ->
                            teams.get("JuniorBackCrawlTeam").addCompetingMembers(competingMember);
                    case CRAWL ->
                            teams.get("JuniorCrawlTeam").addCompetingMembers(competingMember);
                    case BUTTERFLY ->
                            teams.get("JuniorButterflyTeam").addCompetingMembers(competingMember);
                    case BREAST_STROKE ->
                            teams.get("JuniorBreastStrokeTeam").addCompetingMembers(competingMember);
                }

            case ACTIVE_SENIOR:
                switch (swimmingDiscipline) {
                    case BACK_CRAWL ->
                            teams.get("SeniorBackCrawlTeam").addCompetingMembers(competingMember);
                    case BREAST_STROKE ->
                            teams.get("SeniorBreastStrokeTeam").addCompetingMembers(competingMember);
                    case BUTTERFLY ->
                            teams.get("SeniorButterflyTeam").addCompetingMembers(competingMember);
                    case CRAWL ->
                            teams.get("SeniorCrawlTeam").addCompetingMembers(competingMember);
                }
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

    public Team getTeams(MembershipType membershipType, Discipline discipline ) {

        Team teamToReturn = null;
        switch (membershipType) {
            case ACTIVE_JUNIOR:
                switch (discipline) {
                    case BACK_CRAWL ->
                            teamToReturn = teams.get("JuniorBackCrawlTeam");
                    case CRAWL ->
                            teamToReturn = teams.get("JuniorCrawlTeam");
                    case BUTTERFLY ->
                            teamToReturn = teams.get("JuniorButterflyTeam");
                    case BREAST_STROKE ->
                            teamToReturn = teams.get("JuniorBreastStrokeTeam");
                }
            case ACTIVE_SENIOR:
                switch (discipline) {
                    case BACK_CRAWL ->
                            teamToReturn = teams.get("SeniorBackCrawlTeam");
                    case BREAST_STROKE ->
                            teamToReturn = teams.get("SeniorBreastStrokeTeam");
                    case BUTTERFLY ->
                            teamToReturn = teams.get("SeniorButterflyTeam");
                    case CRAWL ->
                            teamToReturn = teams.get("SeniorCrawlTeam");
                }
        }
        return teamToReturn;
    }

    public List<CompetingMember> getTopSwimmers(MembershipType membershipType, Discipline discipline){
        List<CompetingMember> membersByAgeAndDiscipline = getTeams(membershipType, discipline).getCompetingMembers();
        List<CompetingMember> topSwimmers = new ArrayList<>();
        List<CompetingMember> topFiveSwimmers = new ArrayList<>();
        for(CompetingMember swimmer : membersByAgeAndDiscipline){
            if(swimmer.getTrainingResult()!=null){
                topSwimmers.add(swimmer);
            }
        }
        topSwimmers.sort(new CompetingMemberComparator());
        if(topSwimmers.size()>5){
            for(int i=0; i<5; i++){
                topFiveSwimmers.add(topSwimmers.get(i));
            }
            return topFiveSwimmers;
        }else{
            return topSwimmers;
        }
    }

    public void addTrainingResult(int swimmerNumber, String date, double result) throws FileNotFoundException {

        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        Result resultToSave = new Result(competingMembers.get(swimmerNumber), swimmerNumber, result, localDateTime);
        competingMembers.get(swimmerNumber).setTrainingResult(resultToSave);
        fileHandler.saveTrainingResult(resultToSave);

    }

    public Result getTrainingResults(int swimmerNumber) {
        for(Result result : results){
            if(result.getCompetingMemberId() == swimmerNumber){
                return result;
            }
        }
        return null;
    }

    public void addCompetitionEvent(String competingMember, String venue, int ranking, double time) throws FileNotFoundException {
        CompetingMember competingMember1 = competingMembers.get(Integer.parseInt(competingMember));
        Competition competitionToSave = new Competition(competingMember1, venue,ranking,time);
        fileHandler.saveCompetingResult(competitionToSave);
    }

    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(ArrayList<Competition> competitions) {
        this.competitions = competitions;
    }

}



