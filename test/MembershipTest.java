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
        Membership membership = new Membership(MembershipType.ACTIVE_SENIOR);
        Member member = new Member("TestMember", 25);
        if (membership.getMembershipType().equals(MembershipType.ACTIVE_SENIOR) && member.getAge() > 60) {
            membership.setPrice();
            assertEquals(1200, membership.getPrice());
        }
    }
    @Test
    void setPriceActiveSenior(){
        Membership membership = new Membership(MembershipType.ACTIVE_SENIOR);
        membership.setPrice();
        assertEquals(1600,membership.getPrice());
    }
}


