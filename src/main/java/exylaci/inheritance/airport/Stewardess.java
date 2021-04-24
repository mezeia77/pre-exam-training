package exylaci.inheritance.airport;

public class Stewardess extends Person{

    private Position position;

    public Stewardess(String name, Position position) {
        super(name);
        this.position = position;
    }

    public Stewardess(String name, int age, Position position) {
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
