package domain_model;


import java.time.LocalDateTime;

public class Member {
    private int age;
    private String name;
    private Membership membership;

    public Member(int age, String name) {
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
}
