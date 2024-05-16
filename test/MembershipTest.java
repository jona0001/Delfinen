import domain_model.Discipline;
import domain_model.Member;
import domain_model.Membership;
import domain_model.MembershipType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTest {

    @Test
    void setPricePassiveSenior() {
        Membership membership = new Membership(MembershipType.PASSIVE_SENIOR);
        membership.setPrice();
        assertEquals(500, membership.getPrice());
    }

    @Test
    void setPricePassiveJunior(){
        Membership membership = new Membership(MembershipType.PASSIVE_JUNIOR);
        membership.setPrice();
        assertEquals(500,membership.getPrice());
    }

    @Test
    void setPriceActiveJunior(){
        Membership membership = new Membership(MembershipType.ACTIVE_JUNIOR);
        membership.setPrice();
        assertEquals(1000,membership.getPrice());
    }
    @Test
    void setPriceActiveSeniorDiscount(){
        Member member = new Member("TestMember", 62);
        Membership membership = new Membership(MembershipType.ACTIVE_SENIOR);
        membership.setMember(member);
        membership.setPrice();
        assertEquals(1200, membership.getPrice());
    }
    @Test
    void setPriceActiveSenior(){
        Member member = new Member("Testmember", 55);
        Membership membership = new Membership(MembershipType.ACTIVE_SENIOR);
        membership.setMember(member);
        membership.setPrice();
        assertEquals(1600,membership.getPrice());
    }
}


