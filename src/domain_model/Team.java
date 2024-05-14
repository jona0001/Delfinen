package domain_model;

import java.util.ArrayList;

public class Team {
    private ArrayList<CompetingMember> competingMembers = new ArrayList<>();
    private Trainer trainer;
    private Discipline discipline;


    public Team(Trainer trainer, Discipline discipline) {
        this.trainer = trainer;
        this.discipline = discipline;
    }



    public void addCompetingMembers(CompetingMember member){
        competingMembers.add(member);
    }
}
