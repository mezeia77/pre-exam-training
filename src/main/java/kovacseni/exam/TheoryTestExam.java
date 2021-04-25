package kovacseni.exam;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheoryTestExam extends Exam{

    public TheoryTestExam(String name, LocalDateTime dateTime) {
        super(name, dateTime);
    }

    @Override
    public int getDurationOfExam() {
        return 1;
    }

    @Override
    public ExamType getType() {
        return ExamType.THEORY_TEST;
    }

    @Override
    public LocalDateTime getBeginTimeOfExam() {
        return getBeginTime().minusHours(getDurationOfIdentification());
    }

    @Override
    public LocalDateTime getEndTimeOfExam() {
        return getBeginTime().plusHours(getDurationOfExam());
    }

    @Override
    public Map<String, LocalDateTime> getExamsBySpecialPredicate(List<Exam> exams) {
        Map<String, LocalDateTime> result = new HashMap<>();
        for(Exam exam:exams){
            if(exam.getName().contains("fejlesztő")) {
                result.put(exam.getName(), exam.getBeginTime());
            }
        }
        return result;
    }


}
