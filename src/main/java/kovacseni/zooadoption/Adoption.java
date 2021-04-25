package kovacseni.zooadoption;

import java.time.LocalDate;

public class Adoption {

    private String animal;
    private LocalDate adoptionDate;

    public Adoption(String animal, LocalDate adoptionDate) {
        this.animal = animal;
        this.adoptionDate = adoptionDate;
    }

    public String getAnimal() {
        return animal;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    @Override
    public String toString() {
        return "Adoption{" +
                "animal='" + animal + '\'' +
                ", adoptionDate=" + adoptionDate +
                '}';
    }
}
