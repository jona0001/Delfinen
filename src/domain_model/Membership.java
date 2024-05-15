package domain_model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class Membership {
    //private static int IDs = 0;
    private int id;
    private int price;
    private boolean isPaid;
    private Member member;
    private LocalDate registrationDate;
    private LocalDate cancellationDate;
    private MembershipType membershipType;

    public Membership(int id, int price, boolean isPaid,
                      LocalDate registrationDate, LocalDate cancellationDate, MembershipType membershipType) {
        this.id = id;
        this.price = price;
        this.isPaid = isPaid;
        this.registrationDate = registrationDate;
        this.cancellationDate = cancellationDate;
        this.membershipType = membershipType;



    }
    public Membership( MembershipType membershipType) {
        this.membershipType = membershipType;
    }


    public void setMember(Member member) {
        this.member = member;
    }

    public Membership(LocalDate registrationDate) {
        this.id = ThreadLocalRandom.current().nextInt(1, 999999+1);
        this.registrationDate = registrationDate;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public void setPrice() {
        if (membershipType == MembershipType.ACTIVE_JUNIOR) {
            price = 1000;
        } else if (membershipType == MembershipType.ACTIVE_SENIOR && (member.getAge() > 60)) {
            price = 1600 - (1600 / 100 * 25);
        } else if (membershipType == MembershipType.ACTIVE_SENIOR) {
            price = 1600;
        } else if (membershipType == MembershipType.PASSIVE_JUNIOR || membershipType == MembershipType.PASSIVE_SENIOR) {
            price = 500;
        }
    }

    public int getId() {
        return id++;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }


    @Override
    public String toString() {
        return "Membership" +
                "id=" + id +
                ", price= " + price +
                ", isPaid= " + isPaid +
                ", registrationDate= " + registrationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ", cancellationDate= " + cancellationDate +
                ", membershipType= " + membershipType +
                '}';
    }

    public String toCSV() {
        // Format the movie attributes into CSV format
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(id).append(",");
        csvBuilder.append(price).append(",");
        csvBuilder.append(isPaid).append(",");
        csvBuilder.append(registrationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))).append(",");
        csvBuilder.append(cancellationDate).append(",");
        csvBuilder.append(membershipType);
        // Remove the trailing comma and return the CSV string
        return csvBuilder.toString();
    }
}
