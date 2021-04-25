package kovacseni.exam;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Exam {
    private String name;
    private LocalDateTime dateTime;

    public Exam(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBeginTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return getClass() +
                " name='" + name + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

    public abstract int getDurationOfExam();

    public static int getDurationOfIdentification() {
        return 1;
    }

    public abstract ExamType getType();

    public abstract LocalDateTime getBeginTimeOfExam();

    public abstract LocalDateTime getEndTimeOfExam();

    public List<Exam> getExamsInChronologicalOrder(List<Exam> exams) {
        List<Exam> result = new ArrayList<>(exams);
        Collections.sort(result, new Comparator<Exam>() {
            @Override
            public int compare(Exam o1, Exam o2) {
                return o1.getBeginTime().compareTo(o2.getBeginTime());
            }
        });
        return result;
    }

    public abstract Map<String, LocalDateTime> getExamsBySpecialPredicate(List<Exam> exams);

}




//Képesítővizsga
//Képzeljük el, hogy van egy vizsga (Exam), ami egyelőre tényleg csak a képzeletünkben létezik, de játsszunk el a gondolattal, hogy ez a vizsga valamikor,
// valami távoli galaxisban kétféle formában is megvalósulhat: írásbeli- (TheoryTestExam) és projektvizsga (PracticalTestExam).
// Minden ilyen vizsgának lesz neve, kezdési ideje, időtartama, valamint mindegyiken egy órával előbb kell megjelenni a személyazonosítás céljából.
// A következő metódusokat valósítsd meg:
// getBeginTimeOfExam() : azt az időt adja vissza, amikor legelőször jelen kell lenni (az azonosítás kezdetét).
// getEndTimeOfExam() : a vizsga befejezési idejét adja vissza.
// getExamsInChronologicalOrder(List<Exam> exams) : megkapja a megrendezendő vizsgák listáját és kezdési időpont szerinti sorrendbe rendezve adja vissza.
// Map<String, LocalDateTime> getExamsBySpecialPredicate(List<Exam> exams) : ennek a metódusnak kétféle megvalósulása is legyen:
// a TheoryTestExam osztályban adjon vissza egy kollekciót a fejlesztőknek szóló vizsgákból,
// a PracticalTestExam osztályban pedig egy kollekciót a délután megtartandó vizsgákból.
// (Igen, tudatában vagyok, hogy ennek a szétválasztásnak nincs semmi értelme, de ettől még remekül lehet vele gyakorolni pár dolgot. :) )
