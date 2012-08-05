package dsq.thedroid.db;

public interface Table {
    
    String create();
    String name();
    String drop();
    String[] load();
    String[] allColumns();
}
