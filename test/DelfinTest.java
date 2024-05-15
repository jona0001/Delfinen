import domain_model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DelfinTest {
    @Test
    public void addMemberToTeam() {
        // Set up test environment
        Delfin delfin = new Delfin(new ArrayList<>());


        Team juniorCrawlTeam = new Team(new Trainer("Jonathan"), Discipline.CRAWL);
        Team seniorCrawlTeam = new Team(new Trainer("Chris"), Discipline.CRAWL);
        CompetingMember memberTest = new CompetingMember("John", 20, Discipline.CRAWL);

        // Invoke the method
        delfin.addMemberToTeam(memberTest);


        int expectedResult = 1;
        int actualResult = delfin.getCompetingMembers().size();

        //assert
        assertEquals(expectedResult, actualResult);


        assertTrue(seniorCrawlTeam.getCompetingMembers().contains(memberTest)); // Ensure member is in senior team
        assertEquals(1, delfin.getCompetingMembers().size());
    }
}


