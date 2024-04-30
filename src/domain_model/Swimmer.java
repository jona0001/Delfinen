package domain_model;

import java.util.ArrayList;

public class Swimmer {
    private int age;
    private String name;
    private Discipline swimmingDiscipline;
    private boolean isCompeting;
    private ArrayList<Result> trainingResults;



    public Swimmer(int age, String name, boolean isCompeting, Discipline swimmingDiscipline) {
        this.age = age;
        this.name = name;
        this.swimmingDiscipline = swimmingDiscipline;
        this.isCompeting = isCompeting;
        this.trainingResults = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Discipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public void setSwimmingDiscipline(Discipline swimmingDiscipline) {
        this.swimmingDiscipline = swimmingDiscipline;
    }

    public boolean isCompeting() {
        return isCompeting;
    }

    public void setCompeting(boolean competing) {
        this.isCompeting = competing;
    }


    public void addTrainingResult(Result trainingResult) {
        trainingResults.add(trainingResult);
    }

    public ArrayList<Result> getTrainingResults(){
        return trainingResults;
    }

}
