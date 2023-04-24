package journal;

import journal.group.Group;
import journal.teacher.Assistant;
import journal.teacher.Lecturer;

public class JournalExample {
    public static void main(String[] args) {
        final int GROUPS_NUM = 3;
        final int WEEKS_NUM = 16;

        Group[] groups = new Group[GROUPS_NUM];
        for (int i = 0; i < GROUPS_NUM; i++) {
            groups[i] = new Group("GR-" + i, 20);
        }
        Journal journal = new Journal(groups, WEEKS_NUM);

        Lecturer lecturer = new Lecturer("John Smith", journal, groups, WEEKS_NUM);
        Assistant assistant1 = new Assistant("John Assistant", journal, groups, WEEKS_NUM);
        Assistant assistant2 = new Assistant("Bill Assistant", journal, groups, WEEKS_NUM);
        Assistant assistant3 = new Assistant("Nick Assistant", journal, groups, WEEKS_NUM);

        lecturer.start();
        assistant1.start();
        assistant2.start();
        assistant3.start();

        // Wait until all weeks are finished
        try {
            lecturer.join();
            assistant1.join();
            assistant2.join();
            assistant3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        journal.print();
    }
}
