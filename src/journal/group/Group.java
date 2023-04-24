package journal.group;

import java.util.ArrayList;

public class Group {
    private final String name;
    private final ArrayList<Student> students = new ArrayList<>();

    public Group(String name, int groupSize) {
        this.name = name;
        for (int i = 0; i < groupSize; i++) {
            this.students.add(new Student(this, "student #" + i));
        }
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public String getName() {
        return this.name;
    }
}
