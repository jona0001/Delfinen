package ui;
import domain_model.*;
import data_source.*;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    //private Controller controller;
    private int menuChoice = 0;

    public UserInterface() {
        scanner = new Scanner(System.in).useLocale(Locale.ENGLISH);
        scanner.useDelimiter("\n");
        //controller = new Controller();
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

    public void addNewMember(){
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter age:");
        int age = scanner.nextInt();
        scanner.nextLine();

    }


}