package domain_model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Result {
    private double result;
    private LocalDateTime date;

    public Result(double result, LocalDateTime date) {
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
        return
                "Result: " + result +
                "\nDate: " + date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
