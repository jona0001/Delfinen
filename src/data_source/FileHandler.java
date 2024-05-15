package data_source;

import domain_model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public void saveOneMember(Member member) throws FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream(("members.csv"), true));
        PrintStream competing = new PrintStream(new FileOutputStream(("CompetingMembers.csv"), true));
        out.println(member.toCSV());

        if (member.getMembership().getMembershipType() == MembershipType.ACTIVE_JUNIOR ||
                member.getMembership().getMembershipType() == MembershipType.ACTIVE_SENIOR) {
            CompetingMember competingMember = (CompetingMember) member;
            competing.println(competingMember.toCompetingCSV());
        }
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
                    attributes[0], // name
                    Integer.parseInt(attributes[1]));// age);

            LocalDate cancellationDate = null; //to check whether cancellation date in the file is "null"
            if (!attributes[6].equals("null")) {//if not to check it will be NullPointerException
                cancellationDate = LocalDate.parse(attributes[6]);
            }

            Membership membership = new Membership(
                    Integer.parseInt(attributes[2]),
                    Integer.parseInt(attributes[3]),
                    Boolean.parseBoolean(attributes[4]),
                    LocalDate.parse(attributes[5], DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    cancellationDate,
                    MembershipType.valueOf(attributes[7])
            );
            member.setMembership(membership);
            memberFromCSVArr.add(member);
        }
        sc.close();
        return memberFromCSVArr;
    }

    public ArrayList<CompetingMember> loadCompetingMembers() {
        File membersDB = new File("CompetingMembers.csv");
        ArrayList<CompetingMember> memberFromCSVArr = new ArrayList();
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
            CompetingMember competingMember = new CompetingMember(
                    attributes[0], // name
                    Integer.parseInt(attributes[1]), // age
                    Discipline.valueOf(attributes[2])); // makes string to a discipline type.


            Membership competingMembership = new Membership(
                    MembershipType.valueOf(attributes[3])
            );

            competingMember.setMembership(competingMembership);
            memberFromCSVArr.add(competingMember);
        }
        sc.close();
        return memberFromCSVArr;
    }
}