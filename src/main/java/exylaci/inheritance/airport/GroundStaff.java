package exylaci.inheritance.airport;

public class GroundStaff extends Person {

    private String job;

    public GroundStaff(String name, int age, String job) {
        super(name, age);
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    @Override
    public boolean isTravel() {
        return false;
    }
}
