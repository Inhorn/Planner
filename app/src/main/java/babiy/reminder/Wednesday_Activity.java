package babiy.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;

public class Wednesday_Activity extends AppCompatActivity implements View.OnClickListener{

    Button btnCrete;
    DatabaseHandler db;
    ListView lvTasks;
    List<Task> listTask;
    static int ID = 0;

    static final int REQUEST_ADD_TASK = 1;
    static final int REQUEST_EDIT_TASK = 2;

    static final int REQUEST_DELL_ALL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(MainActivity.DAY);

        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.labelWednesday));
        setContentView(R.layout.activity_wednesday);

        btnCrete = (Button) findViewById(R.id.btnCreate);
        btnCrete.setOnClickListener(this);

        db = new DatabaseHandler(this);
        showTasks();
    }

    public void showTasks() {
        listTask = db.getAllTasks(MainActivity.DAY);
        lvTasks = (ListView) findViewById(R.id.lvList);
        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this, R.layout.item, listTask);
        registerForContextMenu(lvTasks);
        lvTasks.setAdapter(adapter);

        lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ID = position;
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Edit_Activity.class);
        startActivityForResult(intent, REQUEST_ADD_TASK);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_day, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        switch (id) {
            case R.id.deleteAllInDay:
                intent = new Intent(this, DeleteAll.class);
                startActivityForResult(intent, REQUEST_DELL_ALL);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }
        if (resultCode == RESULT_OK) {
            String task;
            switch (requestCode) {
                case REQUEST_ADD_TASK:
                    task = data.getStringExtra("task");
                    db.addTask(new Task(task, MainActivity.DAY));
                    showTasks();
                    break;
                case REQUEST_EDIT_TASK:
                    Task newTask = listTask.get(ID);
                    task = data.getStringExtra("task");
                    newTask.setTask(task);
                    db.editTask(newTask);
                    showTasks();
                    break;
                case REQUEST_DELL_ALL:
                    db.delAllDay(MainActivity.DAY);
                    finish();
                    startActivity(getIntent());
                    break;
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemDelete:
                db.deleteTask(listTask.get(ID));
                showTasks();
                break;
            case R.id.itemEdit:
                Intent intent = new Intent(this, Edit_Activity.class);
                startActivityForResult(intent, REQUEST_EDIT_TASK);
                break;
        }

        return super.onContextItemSelected(item);
    }
}