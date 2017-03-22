package babiy.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


class DatabaseHandler extends SQLiteOpenHelper implements MyDatabaseHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myTasks";
    private static final String TABLE_TASKS = "task";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DAY = "day";


    DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_DAY + " TEXT" + ")";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        onCreate(db);
    }

    @Override
    public void addTask(Task task) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getTask());
        values.put(KEY_DAY, task.getDay());
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }


    @Override
    public List<Task> getAllTasks(String day) {
        List<Task> taskList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " WHERE " + KEY_DAY + " = " + " '" + day + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setTask(cursor.getString(1));
                task.setDay(cursor.getString(2));
                taskList.add(task);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return taskList;
    }

    @Override
    public List<Task> getSearchTask() {
        List<Task> taskList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setTask(cursor.getString(1));
                task.setDay(cursor.getString(2));
                taskList.add(task);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return taskList;
    }

    @Override
    public int editTask(Task task, String oldTask) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getTask());
        values.put(KEY_DAY, task.getDay());

        return db.update(TABLE_TASKS, values, KEY_NAME + " = ?" + " and " + KEY_DAY + " = ?",
                new String[]{oldTask, task.getDay()});
    }

    @Override
    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_NAME + " = ?" + " and " + KEY_DAY + " = ?", new String[]{task.getTask(),task.getDay()});
        db.close();

    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, null, null);
        db.close();
    }
}
