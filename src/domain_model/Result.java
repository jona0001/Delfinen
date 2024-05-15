package domain_model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Result {
    private CompetingMember swimmer;
    private int competingMemberId;
    private double result;
    private LocalDateTime date;

    public Result(CompetingMember swimmer, int competingMemberId, double result, LocalDateTime date) {
        this.swimmer = swimmer;
        this.competingMemberId = competingMemberId;
        this.result = result;
        this.date = date;
    }

    public Result(int competingMemberId, double result, LocalDateTime date) {
        this.competingMemberId = competingMemberId;
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

    public String toCSV() {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(competingMemberId).append(",");
        csvBuilder.append(swimmer.toCompetingCSV());
        csvBuilder.append(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))).append(",");
        csvBuilder.append(result).append(",");
        return csvBuilder.toString();
    }
}
