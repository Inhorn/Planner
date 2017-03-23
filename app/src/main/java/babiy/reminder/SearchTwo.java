package babiy.reminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SearchTwo extends AppCompatActivity implements View.OnClickListener {

    Button btnOk;
    String DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_two);

        btnOk = (Button) findViewById(R.id.btnSearchOk);
        btnOk.setOnClickListener(this);

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linSearchLayout);

        LayoutInflater ltInflater = getLayoutInflater();

        List<Task> listTask = MainActivity.searchList;




        for (Task ts : listTask) {
            if (MainActivity.DAY_SEARCH.equals("ua")) {
                switch (ts.getDay()){
                    case "Sunday":
                        DAY = "Неділя";
                        break;
                    case "Monday":
                        DAY = "Понеділок";
                        break;
                    case "Tuesday":
                        DAY = "Вівторок";
                        break;
                    case "Wednesday":
                        DAY = "Середа";
                        break;
                    case "Thursday":
                        DAY = "Четвер";
                        break;
                    case "Friday":
                        DAY = "Пятниця";
                        break;
                    case "Saturday":
                        DAY = "Субота";
                        break;
                }
            }else {
                DAY = ts.getDay();
            }

            View item = ltInflater.inflate(R.layout.item_search, linLayout, false);
            TextView tvSearchTask = (TextView) item.findViewById(R.id.tvSearchTask);
            TextView tvSearchDay = (TextView) item.findViewById(R.id.tvSearchDay);
            tvSearchTask.setText(ts.getTask());
            tvSearchDay.setText(DAY);
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            linLayout.addView(item);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }

}

