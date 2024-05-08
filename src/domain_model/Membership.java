package domain_model;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class Membership {
    //private static int IDs = 0;
    private int id = 0;
    private int price;
    private boolean isPaid;
    private Member member;
    private LocalDateTime registrationDate;
    private LocalDateTime cancellationDate;
    private MembershipType membershipType;

    public Membership(int id, int price, boolean isPaid,
                      LocalDateTime registrationDate, LocalDateTime cancellationDate, MembershipType membershipType) {
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

    public Membership(LocalDateTime registrationDate) {
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public LocalDateTime getCancellationDate() {
        return cancellationDate;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    @Override
    public String toString() {
        return "Membership" +
                "id=" + id +
                ", price=" + price +
                ", isPaid=" + isPaid +
                ", registrationDate=" + registrationDate +
                ", cancellationDate=" + cancellationDate +
                ", membershipType=" + membershipType;
    }

    public String toCSV() {
        // Format the movie attributes into CSV format
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(id).append(",");
        csvBuilder.append(price).append(",");
        csvBuilder.append(isPaid).append(",");
        csvBuilder.append(registrationDate).append(",");
        csvBuilder.append(cancellationDate).append(",");
        csvBuilder.append(membershipType);
        // Remove the trailing comma and return the CSV string
        return csvBuilder.toString();
    }



}
