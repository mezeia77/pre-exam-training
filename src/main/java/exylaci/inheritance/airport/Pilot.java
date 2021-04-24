package exylaci.inheritance.airport;

public class Pilot extends Person{

    private Position position;

    public Pilot(String name, int age, Position position) {
        super(name, age);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isTravel() {
        return true;
    }
}
