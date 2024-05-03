package data_source;

import domain_model.Member;
import domain_model.Membership;

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

    public ArrayList<Member> loadMembers() {
        File membersDB = new File("members.csv");
        ArrayList<Member> memberFromCSVArr = new ArrayList();
        Scanner sc;
        try {
            sc = new Scanner(membersDB);
            sc.nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] attributes = line.split(",");
            Member member = new Member(
                    (attributes[0]), // name
                    (Integer.parseInt(attributes[1])));// age);

           memberFromCSVArr.add(member);
        }
        sc.close();
        return memberFromCSVArr;
    }

}