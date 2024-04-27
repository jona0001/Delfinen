public class Swimmer {
    private int age;
    private double date;
    private String name;
    private String swimming_discipline;
    private String competition_category;
    private String training_results;
    private String competition_results;


    public Swimmer(int age, double date, String name, String swimming_discipline,
                   String competition_category, String training_results, String competition_results) {
        this.age = age;
        this.date = date;
        this.name = name;
        this.swimming_discipline = swimming_discipline;
        this.competition_category = competition_category;
        this.training_results = training_results;
        this.competition_results = competition_results;
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

    public String getSwimming_discipline() {
        return swimming_discipline;
    }

    public void setSwimming_discipline(String swimming_discipline) {
        this.swimming_discipline = swimming_discipline;
    }

    public String getCompetition_category() {
        return competition_category;
    }

    public void setCompetition_category(String competition_category) {
        this.competition_category = competition_category;
    }

    public String getTraining_results() {
        return training_results;
    }

    public void setTraining_results(String training_results) {
        this.training_results = training_results;
    }

    public String getCompetition_results() {
        return competition_results;
    }

    public void setCompetition_results(String competition_results) {
        this.competition_results = competition_results;
    }
}
