package babiy.reminder;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Friday_Activity extends AppCompatActivity implements View.OnClickListener{

    Button btnCreate;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunday);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        db = new DatabaseHandler(this);

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

        LayoutInflater ltInflater = getLayoutInflater();

        List<Task> listTask = db.getAllTasks("FRIDAY");
        String temp;
        for (Task ts : listTask) {
            temp = ts.getDay();
            if (temp.equals("FRIDAY")) {
                View item = ltInflater.inflate(R.layout.item, linLayout, false);
                TextView tvName = (TextView) item.findViewById(R.id.tvTask);
                tvName.setText(ts.getTask());
                item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                linLayout.addView(item);
            }
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnCreate:
                intent = new Intent(this, Edit_Activity.class);
                startActivityForResult(intent , 1);
                break;


        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null){
            return;
        }
        if (resultCode == RESULT_OK){
                if (requestCode == 1){
                    String task = data.getStringExtra("task");
                    db.addTask(new Task(task ,"FRIDAY"));
                    recreate();

                }
        }

    }

}
