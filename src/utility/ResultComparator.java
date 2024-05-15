package utility;

import domain_model.Result;

import java.util.Comparator;

public class ResultComparator implements Comparator<Result> {
    @Override
    public int compare(Result o1, Result o2) {
        return Double.compare(o1.getResult(), o2.getResult());
    }
}
