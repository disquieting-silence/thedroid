package dsq.thedroid.db;

public interface Db {
    int version();
    String name();
    Table[] tables();
}
