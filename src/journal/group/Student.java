package journal.group;

public class Student {
    private final Group group;
    private final String name;

    public Student(Group group, String name) {
        this.group = group;
        this.name = name;
    }

    public String toString() {
        return this.group.getName() + " " + this.name;
    }
}
