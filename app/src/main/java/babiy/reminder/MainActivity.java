package babiy.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSunday;
    Button btnMonday;
    Button btnTuesday;
    Button btnWednesday;
    Button btnThursday;
    Button btnFriday;
    Button btnSaturday;

    DatabaseHandler db;

    static String DAY;
    static List<Task> searchList;

    static final int REQUEST_DELL_ALL = 1;
    static final int REQUEST_SEARCH = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnSunday = (Button) findViewById(R.id.btnSunday);
        btnMonday = (Button) findViewById(R.id.btnMonday);
        btnTuesday = (Button) findViewById(R.id.btnTuesday);
        btnWednesday = (Button) findViewById(R.id.btnWednesday);
        btnThursday = (Button) findViewById(R.id.btnThursday);
        btnFriday = (Button) findViewById(R.id.btnFriday);
        btnSaturday = (Button) findViewById(R.id.btnSaturday);

        btnSunday.setOnClickListener(this);
        btnMonday.setOnClickListener(this);
        btnTuesday.setOnClickListener(this);
        btnWednesday.setOnClickListener(this);
        btnThursday.setOnClickListener(this);
        btnFriday.setOnClickListener(this);
        btnSaturday.setOnClickListener(this);

        db = new DatabaseHandler(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnSunday:
                DAY = "SUNDAY";
                intent = new Intent(this, Sunday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnMonday:
                DAY = "MONDAY";
                intent = new Intent(this, Monday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnTuesday:
                DAY = "TUESDAY";
                intent = new Intent(this, Sunday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnWednesday:
                DAY = "WEDNESDAY";
                intent = new Intent(this, Sunday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnThursday:
                DAY = "THURSDAY";
                intent = new Intent(this, Sunday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnFriday:
                DAY = "FRIDAY";
                intent = new Intent(this, Sunday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnSaturday:
                DAY = "SATURDAY";
                intent = new Intent(this, Sunday_Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.action_delAll:
                intent = new Intent(this, DeleteAll.class);
                startActivityForResult(intent, REQUEST_DELL_ALL);
                break;
            case R.id.action_Quit:
                System.exit(0);
                break;
            case R.id.action_Search:
                intent = new Intent(this, Edit_Activity.class);
                startActivityForResult(intent , REQUEST_SEARCH);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null){
            return;
        }
        if (resultCode == RESULT_OK){

            switch (requestCode) {
                case 1:
                    db.deleteAll();
                    finish();
                    startActivity(getIntent());
                    break;
                case REQUEST_SEARCH:
                    searchList = new ArrayList<>();
                    List<Task> temp =  db.getSearchTask();
                    String task = data.getStringExtra("task").toLowerCase();
                    for (Task ts : temp){
                        if (ts.getTask().toLowerCase().contains(task)){
                            searchList.add(ts);
                        }
                    }
                    Intent intent = new Intent(this, SearchTwo.class);
                    startActivity(intent);
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

