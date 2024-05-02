package domain_model;

import java.util.ArrayList;

public class CompetingMember extends Member {
    private Discipline swimmingDiscipline;
    private ArrayList<Result> trainingResults;

    public CompetingMember(int age, String name, Discipline swimmingDiscipline) {
        super(age, name);
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
}
