package ui;
import controller.Controller;
import domain_model.*;
import data_source.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Controller controller;
    private int menuChoice = 0;

    public UserInterface() {
        scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);
        controller = new Controller();
    }

    public void start() throws FileNotFoundException {
        //loadMovieFromFile();
        int sentinel = 9;
        while (menuChoice != sentinel) {
            myMenuText();
            switch (menuChoice) {
                case 1 -> addNewMember();
//                case 2 -> printMovieCollection();
//                case 3 -> searchInMovieCollections();
//                case 4 -> editMovie();
//                case 5 -> deleteMovie();
//                case 6 -> sortMovies();
            }
        }
    }

    public void myMenuText() {
        System.out.println("***** Menu *****");
        System.out.println("1. Add new member");
        System.out.println("2: Add new training result");
        System.out.println("3: See who did not pay");
        System.out.println("4: Show all members");
        System.out.println("5: Show upcoming revenue");
        System.out.println("6: Show training results by discipline");
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


//        while((!discipline.equals("crawl"))
//        && (!discipline.equals("back crawl"))
//        && (!discipline.equals("butterfly"))
//        && (!discipline.equals("breast stroke"))){

    }



}
