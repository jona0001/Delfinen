package domain_model;

import java.time.LocalDateTime;

public class Membership {
    private int id;
    private int price;
    private boolean isPaid;
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

    public Membership(int id, int price, LocalDateTime registrationDate) {
        this.id = id;
        this.price = price;
        this.registrationDate = registrationDate;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public int getId() {
        return id;
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
                ", membershipType=" + membershipType
                ;
    }
}
