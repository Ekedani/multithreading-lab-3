package journal.teacher;

import journal.group.Group;
import journal.Journal;
import journal.group.Student;

import java.util.Random;

public abstract class TeacherThread extends Thread {
    private final String name;
    private final String status;

    private final Journal journal;
    private final Group[] groups;
    private final int weeks;

    private final Random gradeGenerator = new Random();

    public TeacherThread(String name, String status, Journal journal, Group[] groups, int weeks) {
        this.name = name;
        this.status = status;
        this.journal = journal;
        this.groups = groups;
        this.weeks = weeks;
    }

    @Override
    public void run() {
        // Teacher works N weeks
        for (int i = 0; i < weeks; i++) {
            // Teacher grades students each week
            for (Group group : groups) {
                for (Student student : group.getStudents()) {
                    journal.addGrade(student, i, gradeGenerator.nextInt(101));
                }
            }
        }
    }
}
