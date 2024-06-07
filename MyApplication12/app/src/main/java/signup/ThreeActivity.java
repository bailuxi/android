package signup;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

import more.DatabaseHelper;

public class ThreeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    DatabaseHelper myDb;
    Spinner astatus;
    EditText name, age, number;
    RadioGroup radioGroup;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        astatus = findViewById(R.id.status);
        String[] intype = new String[]{"企业", "媒体", "学生", "游客"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, intype);
        astatus.setAdapter(adapter);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        radioGroup = findViewById(R.id.gender);
        number = findViewById(R.id.number);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userAge = age.getText().toString();
                RadioButton rb = findViewById(radioGroup.getCheckedRadioButtonId());
                String userGender = rb.getText().toString();
                String userStatus = astatus.getSelectedItem().toString();
                String userNumber = number.getText().toString();

                boolean isInserted = myDb.insertData(userName, userAge, userGender, userStatus, userNumber);

                if (isInserted) {
                    Toast.makeText(ThreeActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ThreeActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
