package domain_model;

public class Competition {
    private CompetingMember competingMember;
    private String venue;
    private int ranking;
    private double time;


    public Competition(CompetingMember competingMember, String venue, int ranking, double time) {
        this.competingMember = competingMember;
        this.venue = venue;
        this.ranking = ranking;
        this.time = time;
    }


    public String toCSV() {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(competingMember.toCompetingCSV());
        csvBuilder.append(venue).append(",");
        csvBuilder.append(ranking).append(",");
        csvBuilder.append(time).append(",");
        return csvBuilder.toString();
    }

    public CompetingMember getCompetingMember() {
        return competingMember;
    }

    public String getVenue() {
        return venue;
    }

    public int getRanking() {
        return ranking;
    }

    public double getTime() {
        return time;
    }
}
