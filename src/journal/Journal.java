package journal;

import journal.group.Group;
import journal.group.Student;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

public class Journal {
    HashMap<Student, ArrayBlockingQueue<Integer>[]> studentGradesMap = new HashMap<>();

    public Journal(Group[] groups, int weeks) {
        for (Group group : groups) {
            for (Student student : group.getStudents()) {
                var grades = new ArrayBlockingQueue[weeks];
                for (int i = 0; i < weeks; i++) {
                    grades[i] = new ArrayBlockingQueue<Integer>(4);
                }
                studentGradesMap.put(student, grades);
            }
        }
    }

    public void addGrade(Student student, int week, int grade) {
        if (grade < 0 || grade > 100) {
            throw new RuntimeException("Invalid grade");
        }
        studentGradesMap.get(student)[week].add(grade);
    }

    public void print() {
        for (Student student : studentGradesMap.keySet()) {
            var grades = studentGradesMap.get(student);
            System.out.print(student.toString() + " | ");
            for (int i = 0; i < grades.length; i++) {
                System.out.print("WEEK " + i + ": ");
                for (var grade : grades[i]) {
                    System.out.print(grade + " ");
                }
                System.out.print("| ");
            }
            System.out.print('\n');
        }
    }
}
