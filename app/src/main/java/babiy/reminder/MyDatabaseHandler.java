package babiy.reminder;

import java.util.List;

/**
 * Created by Iгор on 18.03.2017.
 */

public interface MyDatabaseHandler {
    public void addTask(Task task);
    public List<Task> getAllTasks(String day);
    public int editTask(Task task);
    public void deleteTask(Task task);
    public void deleteAll();
}
