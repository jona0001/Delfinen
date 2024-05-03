package data_source;

import domain_model.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

public void saveOneMember(Member member) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream(("members.csv"), true));
        out.println(member.toCSV());
    }
    /*
    public ArrayList<Member> loadMoviesFromFile() {
        File moviesDB = new File("movies.csv");
        ArrayList<Member> moviesFromCSVArr = new ArrayList();
        Scanner sc;
        try {
            sc = new Scanner(moviesDB);
            sc.nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(",");
            Member movie = new Member(
                    (attributes[0]), // title
                    (attributes[1]), // director
                    (Integer.parseInt(attributes[2])), // year created
                    (Integer.parseInt(attributes[3])), // isInColor
                    (Boolean.parseBoolean(attributes[4])), // length
                    (attributes[5])); // genre
            moviesFromCSVArr.add(movie);
        }
        sc.close();
        return moviesFromCSVArr;
    }

     */
}