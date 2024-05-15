package utility;

import domain_model.CompetingMember;

import java.util.Comparator;

public class CompetingMemberComparator implements Comparator<CompetingMember> {
    @Override
    public int compare(CompetingMember o1, CompetingMember o2) {

        return Double.compare(o1.getTrainingResult().getResult(), o2.getTrainingResult().getResult());
    }
}
