package journal.teacher;

import journal.group.Group;
import journal.Journal;

public class Assistant extends TeacherThread {
    public Assistant(String name, Journal journal, Group[] groups, int weeks) {
        super(name, "assistant", journal, groups, weeks);
    }
}
