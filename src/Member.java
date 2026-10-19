package librarymanagement;

public class Member {
    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }

    public int getBorrowLimit() { return 2; }
    public int getBorrowDurationDays() { return 14; }

    @Override
    public String toString() {
        return memberId + " | " + name + " | " + getClass().getSimpleName();
    }
}
