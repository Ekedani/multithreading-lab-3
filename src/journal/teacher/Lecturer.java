package journal.teacher;

import journal.group.Group;
import journal.Journal;

public class Lecturer extends TeacherThread {
    public Lecturer(String name, Journal journal, Group[] groups, int weeks) {
        super(name, "lecturer", journal, groups, weeks);
    }
}
