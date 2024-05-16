package ui;

import controller.Controller;
import domain_model.*;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.List;

public class UserInterface {
    private Scanner scanner;
    private Controller controller;
    private int menuChoice = 0;

    public UserInterface() {
        scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);
        controller = new Controller();
    }

    public void start() throws FileNotFoundException {
        controller.loadFromFile();
        int sentinel = 10;
        while (menuChoice != sentinel) {
            myMenuText();
            switch (menuChoice) {
                case 1 -> addNewMember();
                case 2 -> printMembers();
                case 3 -> getDebtors();
                case 4 -> getFutureRevenue();
                case 5 -> getCompetingSwimmers();
                case 6 -> showTeams();
                case 7 -> registerTrainingResults();
                case 8 -> showTrainingResults();
                case 9 -> showTopSwimmers();
            }
        }
    }

    private void showTopSwimmers() {
        System.out.println("top swimmers:");
        HashMap<MembershipType, Discipline> mapWithUserInput = sortDisciplineAndType();
        for ( Map.Entry<MembershipType, Discipline> entry : mapWithUserInput.entrySet()) {
            List<CompetingMember> topSwimmers = controller.showTopSwimmers(entry.getKey(), entry.getValue());
            printCompetingMembers(topSwimmers);
        }
    }


    private void printMembers() {
        System.out.println("All members:");
        for (int i = 0; i < controller.getAllMembers().size(); i++) {
            System.out.printf("%d. ", i);
            System.out.println();
            System.out.println(controller.getAllMembers().get(i));
        }
    }

    public void myMenuText() {
        System.out.println("***** Menu *****");
        System.out.println("1. Add a new member to the club");
        System.out.println("2: See the list of members");
        System.out.println("3: See who did not pay");
        System.out.println("4: Show upcoming revenue for Delfinen");
        System.out.println("5: See a list of all competing members");
        System.out.println("6: Show teams");
        System.out.println("7. Register training results for a competing swimmer");
        System.out.println("8. See the training results of a competing swimmer");
        System.out.println("9. See top swimmers");
        System.out.println("10: Exit");
        System.out.println("*****************");
        try {
            menuChoice = scanner.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println("not working, try again");
        }

    }

    private void registerTrainingResults() throws FileNotFoundException {
        getCompetingSwimmers();
        System.out.println("Enter the number of the swimmer to add their training results:");
        int swimmerNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the date of training in the format day-month-year hours:minutes");
        boolean isDoneWithDate = false;
        String date = "";
        while(!isDoneWithDate){
            try{
                date = scanner.nextLine();
                System.out.println("Enter the training result:");
                double result = scanner.nextDouble();
                controller.addTrainingResult(swimmerNumber, date, result);
                System.out.println("The result was successfully added!");
                isDoneWithDate = true;
            }catch (DateTimeParseException ex){
                System.out.println("Please enter the date in the format day-month-year hours:minutes");
                scanner.nextLine();
            }
        }
    }

    private void showTrainingResults() {
        getCompetingSwimmers();
        System.out.println("Enter the number of the swimmer to see their training results:");
        int swimmerNumber = scanner.nextInt();
        scanner.nextLine();
        Result trainingResult = controller.getTrainingResults(swimmerNumber);
        System.out.println(trainingResult);

    }

    public void addNewMember() throws FileNotFoundException {
        System.out.println("Enter name:");
        String name = scanner.next();

        System.out.println("Enter age:");
        boolean isDoneWithAge = false;
        int age = 0;
        while (!isDoneWithAge) {
            try {
                age = scanner.nextInt();
                isDoneWithAge = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter age as a number:");
                scanner.nextLine();
            }
        }
        String competing = scanner.nextLine();
        boolean isCompeting = false;
        if (competing.equalsIgnoreCase("yes")) {
            isCompeting = true;
        }

        // We could add this function in the step where we ask about member age and do a if<18, set to Junior etc..
        System.out.println("Enter desired membership type ");
        String membershipType = scanner.next();


        Discipline discipline = null;
        if (membershipType.equalsIgnoreCase("Active")) {
            System.out.println("Enter Discipline, choose one:");
            System.out.println("1. Butterfly");
            System.out.println("2. Crawl");
            System.out.println("3. Breast stroke");
            System.out.println("4. Back Crawl");
            int disciplineInput = scanner.nextInt();

            switch (disciplineInput) {
                case 1 -> discipline = Discipline.BUTTERFLY;
                case 2 -> discipline = Discipline.CRAWL;
                case 3 -> discipline = Discipline.BREAST_STROKE;
                case 4 -> discipline = Discipline.BACK_CRAWL;
            }
        }
        boolean isAdded = controller.addMember(name, age, membershipType, discipline);
        if (isAdded) {
            System.out.println("The new member was added.");
        }
    }


    public void getDebtors() {
        System.out.println("Debtors:");
        for (int i = 0; i < controller.getDebtors().size(); i++) {
            System.out.printf("%d. ", i);
            System.out.println();
            System.out.println(controller.getDebtors().get(i));
        }
    }

    public void getFutureRevenue() {
        int upcomingRevenue = controller.getUpcomingRevenue();
        System.out.println("The swimming club is expected to get: " + upcomingRevenue + "kr.");
        System.out.println("The subscriptions which are expected to be cancelled are not counted.");
    }

    public void getCompetingSwimmers() {
        List<CompetingMember> competingMembers = controller.getCompetingSwimmers();
        System.out.println("Competing members:");
        printCompetingMembers(competingMembers);
    }

    public void printCompetingMembers(List<CompetingMember> competingMembers) {
        System.out.println();
        if (competingMembers.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (int i = 0; i < competingMembers.size(); i++) {
                System.out.printf("%d. ", i);
                System.out.printf("Swimmer name: %s\nTeam: ", competingMembers.get(i).getName());
                if (competingMembers.get(i).getMembership().getMembershipType() == MembershipType.ACTIVE_JUNIOR) {
                    System.out.println("Juniors");
                } else {
                    System.out.println("Seniors");
                }
                System.out.printf("Discipline:%s", competingMembers.get(i).getSwimmingDiscipline() + "\n");
                System.out.println("===================");
            }
        }
    }


    public HashMap<MembershipType, Discipline> sortDisciplineAndType() {
        MembershipType membershipType = null;
        System.out.println("Choose category: \n1. Seniors\n2. Juniors");
        int membershipInput = scanner.nextInt();
        switch (membershipInput) {
            case 1 -> membershipType = MembershipType.ACTIVE_SENIOR;
            case 2 -> membershipType = MembershipType.ACTIVE_JUNIOR;
            // kald ny method i delfin igennem controller. Sæt parameter som memebership.active senior
        }
        System.out.println("Choose team by discipline:");
        System.out.println("1. Butterfly team");
        System.out.println("2. Crawl team");
        System.out.println("3. Breast stroke team");
        System.out.println("4. Back Crawl team");
        int disciplineInput = scanner.nextInt();
        Discipline discipline = null;
        switch (disciplineInput) {
            case 1 -> discipline = Discipline.BUTTERFLY;
            case 2 -> discipline = Discipline.CRAWL;
            case 3 -> discipline = Discipline.BREAST_STROKE;
            case 4 -> discipline = Discipline.BACK_CRAWL;
        }
        HashMap<MembershipType, Discipline> mapWithUserInput = new HashMap<>();
        mapWithUserInput.put(membershipType, discipline);
        return mapWithUserInput;
    }

    public void showTeams(){
        HashMap<MembershipType, Discipline> mapWithUserInput = sortDisciplineAndType();
        for ( Map.Entry<MembershipType, Discipline> entry : mapWithUserInput.entrySet()) {
            List<CompetingMember> team = controller.sortMembers(entry.getKey(), entry.getValue());
            printCompetingMembers(team);
        }

    }

}
