package domain_model;


import java.time.LocalDateTime;
import java.time.format.FormatStyle;

public class Member {
    private int age;
    private String name;
    private Membership membership;

    public Member(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
    public String toCSV() {
        // Format the movie attributes into CSV format
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(name).append(",");
        csvBuilder.append(age).append(",");
        csvBuilder.append(membership.toCSV()).append(",");

        // Remove the trailing comma and return the CSV string
        return csvBuilder.toString();
    }

    @Override
    public String toString() {
        return "Member information:" +
                "\n Name = " + name +
                "\n Age = " + age +
                "\n Membership ID = " + membership.getId() +
                "\n Price = " + membership.getPrice() + "kr" +
                "\n isPaid = " + membership.isPaid() +
                "\n Registration Date = " + membership.getRegistrationDate().toLocalDate() +
                "\n Cancellation Date = " + membership.getCancellationDate() +
                "\n Membership Type = " + membership.getMembershipType();
    }
}
