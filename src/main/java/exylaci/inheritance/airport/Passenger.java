package exylaci.inheritance.airport;

public class Passenger extends Person{

    private String seat;

    public Passenger(String name, int age, String seat) {
        super(name, age);
        this.seat = seat;
    }

    public String getSeat() {
        return seat;
    }

    @Override
    public boolean isTravel() {
        return true;
    }
}
