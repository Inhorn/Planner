package babiy.reminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Edit_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;

    Button btnOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        editText = (EditText) findViewById(R.id.editText);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            Toast.makeText(Edit_Activity.this, "Task is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("task", editText.getText().toString());
        setResult(RESULT_OK, intent);
        Edit_Activity.this.finish();

    }
}
