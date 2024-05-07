package domain_model;

import java.util.ArrayList;

public class CompetingMember extends Member {
    private Discipline swimmingDiscipline;
    private ArrayList<Result> trainingResults;

    public CompetingMember(String name, int age, Discipline swimmingDiscipline) {
        super(name, age);
        this.swimmingDiscipline = swimmingDiscipline;
        trainingResults = new ArrayList<>();
    }

    public Discipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public void setSwimmingDiscipline(Discipline swimmingDiscipline) {
        this.swimmingDiscipline = swimmingDiscipline;
    }

    public void addTrainingResult(Result trainingResult) {
        trainingResults.add(trainingResult);
    }

    public ArrayList<Result> getTrainingResults(){
        return trainingResults;
    }


    public String toCompetingCSV() {
        // Format the movie attributes into CSV format
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(super.getName()).append(",");
        csvBuilder.append(super.getAge()).append(",");
        csvBuilder.append(swimmingDiscipline.toString()).append(",");
        csvBuilder.append(super.getMembership().getMembershipType().toString()).append(",");
        // Remove the trailing comma and return the CSV string
        return csvBuilder.toString();
    }
}
