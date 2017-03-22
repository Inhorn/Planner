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
            View item = ltInflater.inflate(R.layout.item_search, linLayout, false);
            TextView tvSearchTask = (TextView) item.findViewById(R.id.tvSearchTask);
            TextView tvSearchDay = (TextView) item.findViewById(R.id.tvSearchDay);
            tvSearchTask.setText(ts.getTask());
            tvSearchDay.setText(ts.getDay());
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            linLayout.addView(item);

        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }

}

