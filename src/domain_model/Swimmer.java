package domain_model;

public class Swimmer {
    private int age;
    private String name;
    private Discipline swimmingDiscipline;
    private boolean isCompeting;
    private Result trainingResults;



    public Swimmer(int age, double date, String name, Discipline swimmingDiscipline,
                   boolean isCompeting, Result trainingResults) {
        this.age = age;
        this.name = name;
        this.swimmingDiscipline = swimmingDiscipline;
        this.isCompeting = isCompeting;
        this.trainingResults = trainingResults;
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

    public Result getTrainingResults() {
        return trainingResults;
    }

    public void setTrainingResults(Result trainingResults) {
        this.trainingResults = trainingResults;
    }


}
