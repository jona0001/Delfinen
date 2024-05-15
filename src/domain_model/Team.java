package domain_model;

import java.util.ArrayList;

public class Team {
    private ArrayList<CompetingMember> competingMembers = new ArrayList<>();
    private Trainer trainer;
    private Discipline discipline;
    private ArrayList<Result> trainingResults;

    public Team(Trainer trainer, Discipline discipline) {
        this.trainer = trainer;
        this.discipline = discipline;
    }
    public void addCompetingMembers(CompetingMember member){
        competingMembers.add(member);
    }
    public ArrayList<CompetingMember> getCompetingMembers() {
        return competingMembers;
    }
    public boolean addTrainingResult(Result trainingResult) {
        return trainingResults.add(trainingResult);
    }

    public ArrayList<Result> getTrainingResults(){
        return trainingResults;
    }

    public void setTrainingResults(ArrayList<Result> trainingResults) {
        this.trainingResults = trainingResults;
    }
}
