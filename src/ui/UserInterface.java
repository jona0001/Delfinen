package ui;
import controller.Controller;
import domain_model.*;
import data_source.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

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
        int sentinel = 9;
        while (menuChoice != sentinel) {
            myMenuText();
            switch (menuChoice) {
                case 1 -> addNewMember();
                case 2 -> printMembers();
                case 3 -> getDebtors();
                case 4 -> getFutureRevenue();
                case 5 -> getCompetingSwimmers();
                case 6 -> showMembersDisciplin();
            }
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
        System.out.println("6: Show members by disciplin");
        System.out.println("7. Register training results for a member");
        System.out.println("9: Exit");
        System.out.println("*****************");
        try {
            menuChoice = scanner.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println("not working, try again");
        }

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

        String discipline ="";

        if (membershipType.equalsIgnoreCase("Active")){
            System.out.println("Enter discipline");
            discipline = scanner.next();

        }
        boolean isAdded = controller.addMember(name, age, membershipType, discipline);
        if(isAdded){
            System.out.println("The new member was added.");
        }
    }
    public void isCompeting(){
        System.out.println("Enter discipline");
        Scanner scannerTwo = new Scanner(System.in);
        String discipline = scannerTwo.next();
        Discipline discipline1 = null;
        switch (discipline.toLowerCase()){
            case "crawl" -> discipline1 = Discipline.CRAWL;
            case "back crawl", "backcrawl" -> discipline1 = Discipline.BACK_CRAWL;
            case "butterfly" -> discipline1 = Discipline.BUTTERFLY;
            case "breast stroke", "breaststroke" -> discipline1 = Discipline.BREAST_STROKE;
        }
    }

    public void getDebtors(){
        System.out.println("Debtors:");
        for (int i = 0; i < controller.getDebtors().size(); i++) {
            System.out.printf("%d. ", i);
            System.out.println();
            System.out.println(controller.getDebtors().get(i));
        }
    }

    public void getFutureRevenue(){
        int upcomingRevenue = controller.getUpcomingRevenue();
        System.out.println("The swimming club is expected to get: " + upcomingRevenue + "kr.");
        System.out.println("The subscriptions which are expected to be cancelled are not counted.");
    }

    public void getCompetingSwimmers(){
        System.out.println("Competing members:");
        for (int i = 0; i < controller.getCompetingSwimmers().size(); i++) {
            System.out.printf("%d. ", i);
            System.out.println();
            System.out.println(controller.getCompetingSwimmers().get(i));
        }
    }

    public void showMembersDisciplin() {
    }

}
