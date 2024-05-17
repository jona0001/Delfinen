package domain_model;


public class CompetingMember extends Member {
    private Discipline swimmingDiscipline;
    private Result trainingResult;
    private int competingId;

    private Competition competitionResult;

    public CompetingMember(String name, int age, Discipline swimmingDiscipline) {
        super(name, age);
        this.swimmingDiscipline = swimmingDiscipline;
    }

    public Discipline getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public void setCompetingId(int competingId) {
        this.competingId = competingId;
    }

    public int getCompetingId() {
        return competingId;
    }

    public Result getTrainingResult(){
        return trainingResult;
    }

    public void setTrainingResult(Result trainingResult) {
        this.trainingResult = trainingResult;
    }

    public void setCompetitionResult(Competition competition) {
        this.competitionResult = competitionResult;
    }


    public String toCompetingCSV() {
        // Format the movie attributes into CSV format
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(competingId).append(",");
        csvBuilder.append(super.getName()).append(",");
        csvBuilder.append(super.getAge()).append(",");
        csvBuilder.append(swimmingDiscipline.toString()).append(",");
        csvBuilder.append(super.getMembership().getMembershipType().toString()).append(",");
        // Remove the trailing comma and return the CSV string
        return csvBuilder.toString();
    }

    @Override
    public String toString() {
        return
                super.getName() + "\n" +
                "Swimming Discipline: " + swimmingDiscipline + "\n" +
                "Competing Id: " + competingId;
    }
}
