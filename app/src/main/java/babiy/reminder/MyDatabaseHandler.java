package babiy.reminder;

import java.util.List;

interface MyDatabaseHandler {
    void addTask(Task task);
    List<Task> getAllTasks(String day);
    int editTask(Task task);
    void deleteTask(Task task);
    void deleteAll();
    List<Task> getSearchTask ();
    void delAllDay (String day);
}