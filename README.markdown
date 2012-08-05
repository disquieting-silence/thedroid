thedroid
=================

`thedroid` is a project designed to serve as a building block for an `Android` application. It is highly experimental, unstable, and poorly designed, but you should become accustomed to that if you are going to use any of these repositories. Presently, it has four major packages:

* contacts
* db
* ui
* util

dsq.thedroid.contacts
----------

`contacts` is used to simplify the browsing and processing of user contacts on their phone. Its public API consists of:


### dsq.thedroid.contacts.BasicContact

A basic `struct` containing public **read-only** fields: `name` and `number`.

### dsq.thedroid.contacts.Contacts (DefaultContacts)

This manages the browsing and processing of *Contacts*. Specifically:

#### browse

    void browse(Activity activity, int reqCode);

Launches a *Contact Picker*, allowing the user to browse through their contacts and select one. It does not return anything because on completion a response will be sent to `activity` directly. It is *asynchronous*. The parameters are:

* *activity*: the activity responsible for managing the request.
* *reqCode*: an `integer` responsible for identifying the request. This is used when processing the result within `activity`. 

e.g. in an **activity**

    
    final Contacts = new DefaultContacts();
    contacts.browse(this, 1);
    ... 

#### process

    BasicContact process(Activity activity, Intent data) throws NoPhoneNumberException;

Retrieves the `BasicContact` information from a returning request. This will typically be used in an `onActivityResult` block where the *request code* will be used to identify the request. The parameters are:

* *activity*: the *activity* responsible for managing the contacts.
* *data*: the data returned by the previous *Contact Picker* request. This will be contained in the response from `browse`.

e.g. in an **activity**

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == 1 && resultCode == Activity.RESULT_OK) {
            try {
                BasicContact contact = contacts.process(this, data);
                ...
            } catch (NoPhoneNumberException exc) { .... }

### dsq.thedroid.contacts.NoPhoneNumberException

An **Exception** thrown when the specified contact cannot be found by the content resolution process. Essentially, it's a failure case.

dsq.thedroid.db
----------

`db` is mostly self-explanatory. Its primary purpose is to provide a simple convenience layer for basic database management. It is very much a work in progress. The 'stable' API is:

### dsq.thedroid.db.Db

    int version(); // current version of the database
    String name(); // the name of the database
    Table[] tables(); // the array of Table instances consisting the database


### dsq.thedroid.db.DbAccessRead (DefaultDbAccessRead)

    Cursor query(SQLiteDatabase db, String sql, String[] bindings);
    Cursor fetch(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereArgs);
    Cursor fetchById(SQLiteDatabase db, String tableName, String[] columns, long id);
    Cursor fetchAll(SQLiteDatabase db, String tableName, String[] columns);
    boolean contains(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereValues);

### dsq.thedroid.db.DbAccessWrite (DefaultDbAccessWrite)

    long create(SQLiteDatabase db, String tableName, ContentValues values);
    boolean update(SQLiteDatabase db, String tableName, long id, ContentValues values);
    boolean updateAll(SQLiteDatabase db, String tableName, ContentValues values);
    boolean delete(SQLiteDatabase db, String tableName, String column, String value);
    boolean deleteById(SQLiteDatabase db, String tableName, long id);

### dsq.thedroid.db.DbAccess (DefaultDbAccess)

    Cursor query(SQLiteDatabase db, String sql, String[] bindings);
    Cursor fetch(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereArgs);
    Cursor fetchById(SQLiteDatabase db, String tableName, String[] columns, long id);
    Cursor fetchAll(SQLiteDatabase db, String tableName, String[] columns);
    boolean contains(SQLiteDatabase db, String tableName, String[] columns, String whereClause, String[] whereValues);
    long create(SQLiteDatabase db, String tableName, ContentValues values);
    boolean update(SQLiteDatabase db, String tableName, long id, ContentValues values);
    boolean updateAll(SQLiteDatabase db, String tableName, ContentValues values);
    boolean delete(SQLiteDatabase db, String tableName, String column, String value);
    boolean deleteById(SQLiteDatabase db, String tableName, long id);

### dsq.thedroid.db.DbAdapter  

A `DbAdapter` is essentially used to minimise the amount of details that need to be passed in to the above `DbAccess` interfaces. Essentially, it's a state-ful version of the stateless interfaces above.

    boolean deleteById(long id);
    Cursor fetchAll();
    Cursor fetchById(long id) throws SQLException;


### dsq.thedroid.db.DbHelper (DefaultDbHelper)

The `DefaultDbHelper` implementation of the `DbHelper` interface provides the general upgrade and creation processes used the Android system.

    void onCreate(SQLiteDatabase sqLiteDatabase, Table[] tables);
    void onUpgrade(SQLiteDatabase sqLiteDatabase, Table[] tables, int oldVersion, int newVersion);

### dsq.thedroid.db.Table

    String create(); // SQL statement for creation
    String name();
    String drop(); // SQL statement for dropping
    String[] load(); // SQL statements for loading initial data
    String[] allColumns();

### dsq.thedroid.db.DbLifecycle (DefaultDbLifecycle)

`DbLifecycle` needs to be state-ful.

    SQLiteDatabase open(Context context, Db settings) throws SQLException;
    void close();

### dsq.thedroid.db.DbUtils

    public static String getColumn(Cursor cursor, String column); // retrieve the value from a cursor by column name

dsq.thedroid.ui
-------------------

The `ui` package consists of small units designed to facilitate constructing generic user interface components quickly. It is not especially useful at this stage, and its brief attempts at adding swipe support are underdeveloped.

### dsq.thedroid.ui.ComponentIndex

A strongly-typed read-only *integer* value called value. It is used to represent the IDs that are automatically generated from layout resources.

### dsq.thedroid.ui.Buttons (DefaultButtons)

Wires up the default functionality for *cancel* and *confirm* buttons. It is not especially flexible.

    void cancel(int id, Activity activity);
    void confirm(int id, Activity activity, View.OnClickListener action);


### dsq.thedroid.ui.Lists (DefaultLists)

Provides basically refreshing utilities for list views. The `rowUi` field refers to the ID generated by the list row layout resource.

    void refresh(ListActivity activity, Cursor cursor, ListView view, ComponentIndex rowUi, ListMapping mapping, SimpleCursorAdapter.ViewBinder binder);
    void refreshById(ListActivity activity, DbAdapter adapter, int id, ListView view, ComponentIndex rowUi, ListMapping mapping, SimpleCursorAdapter.ViewBinder binder);
    void refreshAll(ListActivity activity, DbAdapter adapter, ListView view, ComponentIndex rowUi, ListMapping mapping, SimpleCursorAdapter.ViewBinder binder);

### dsq.thedroid.ui.ListMapping

    String[] source(); // array of column names in the order their components are listed
    ComponentIndex[] dest(); // array of layout resource indices in the order their column names are listed

### dsq.thedroid.ui.Menus (DefaultMenus)

Generate the desired menu using a resource ID.

    boolean options(Activity activity, Menu menu, int id);
    void context(Activity activity, Menu menu, int id);

### dsq.thedroid.ui.TextInserter (DefaultTextInserter)

Attempt to insert some text into an **Editable** view at the current cursor location. 

    void tryInsert(View view, String text);


dsq.thedroid.util
-------------------

It is always a danger making a `util` package because of the lack of categorisation it encourages, and this is definitely the case here. This package currently contains `Cursor` and `Intent` convenience functions.

### dsq.thedroid.util.CursorOperation

The `CursorOperation` interface provides a means for separating the handling of the cursor from the function on the cursor. Essentially, a `CursorOperation` is just a first-class function, but the support for that in Java is obviously minimal. The function that must be implemented is:

    void go(long id, Cursor cursor);

### dsq.thedroid.util.IdCursor (DefaultIdCursor)

The `DefaultIdCursor` implementation provides a common use case for cursor manipulation. It uses a `DbAdapter` and an `id` to find the `Cursor` instance, and then gets the activity to manage the cursor while performing the operation. The method signature is:

    void go(DbAdapter adapter, Activity activity, long id, CursorOperation operation);

### dsq.thedroid.util.StateExtractor (DefaultStateExtractor)

In Android applications, data can be passed through in many forms. The goal of the `StateExtractor` is to provide a convenient approach to extract the desired state from a collection of possible sources (`Bundle`, `Intent`), using the correct precedence. There is both a `strict` (cannot be null) and an `extract` method.

    Serializable extract(String tag, Bundle bundle, Intent intent);
    Serializable strict(String tag, Bundle bundle, Intent intent);
