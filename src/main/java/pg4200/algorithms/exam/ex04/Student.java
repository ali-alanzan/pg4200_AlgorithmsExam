package pg4200.algorithms.exam.ex04;

import java.util.Map;

public class Student {
    public Student() {
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getExamPoints() {
        return examPoints;
    }

    public void setExamPoints(Map<String, Double> examPoints) {
        this.examPoints = examPoints;
    }

    public int student_id;
    public String name;
    Map<String, Double> examPoints;
}