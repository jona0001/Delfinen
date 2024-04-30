package domain_model;

import java.util.Calendar;
import java.util.Date;

public class Result {
    private int result;
    private Calendar date;

    public Result(int result, Calendar date) {
        this.result = result;
        this.date = date;
    }

    public int getResult() {
        return result;
    }

    public Calendar getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", date=" + date +
                '}';
    }
}
