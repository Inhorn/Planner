package babiy.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSunday;
    Button btnMonday;
    Button btnTuesday;
    Button btnWednesday;
    Button btnThursday;
    Button btnFriday;
    Button btnSaturday;

    DatabaseHandler db;

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

        db = new DatabaseHandler(this); // раз створти чи кожного разу створювати
        // як вибрати тількт один стовбець   запсувати раз і оновляти чи працювати напряму з БД

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnSunday:
                intent = new Intent(this, Sunday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnMonday:
                intent = new Intent(this, Monday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnTuesday:
                intent = new Intent(this, Tuesday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnWednesday:
                intent = new Intent(this, Wednesday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnThursday:
                intent = new Intent(this, Tuesday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnFriday:
                intent = new Intent(this, Friday_Activity.class);
                startActivity(intent);
                break;
            case R.id.btnSaturday:
                intent = new Intent(this, Saturday_Activity.class);
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

        switch (id){
            case R.id.action_delAll:
                db.deleteAll();
                break;
            case R.id.action_Quit:
                finish();
        }


        return super.onOptionsItemSelected(item);
    }
}

