package librarymanagement;

public class PremiumMember extends Member {
    public PremiumMember(String id, String name) {
        super(id, name);
    }

    @Override
    public int getLimit() { return 5; }

    @Override
    public int getDays() { return 30; }
}
