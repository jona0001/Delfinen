package domain_model;

import java.time.LocalDateTime;

public class Result {
    private double result;
    private LocalDateTime date;

    public Result(int result, LocalDateTime date) {
        this.result = result;
        this.date = date;
    }

    public double getResult() {
        return result;
    }

    public LocalDateTime getDate() {
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
