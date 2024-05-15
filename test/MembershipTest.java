import domain_model.Discipline;
import domain_model.Member;
import domain_model.Membership;
import domain_model.MembershipType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MembershipTest {

    @Test
    void setPrice() {
        //setup
        Membership membership = new Membership(2,1000,true,null,null,MembershipType.ACTIVE_SENIOR);
        Member member = new Member("test",22);

        int expectedResult = 1000;
        int actualResult = membership.getPrice();
        // assert
        assertEquals(expectedResult, actualResult);
    }
}