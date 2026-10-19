package librarymanagement;

public class Member {
    private String id, name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public int getLimit() { return 2; }
    public int getDays() { return 14; }
}
