package signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import more.DatabaseHelper;

public class FiveActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText editTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        dbHelper = new DatabaseHelper(this);
        editTextId = findViewById(R.id.deletetext);

        Button partialDeleteButton = findViewById(R.id.partdelete);
        Button deleteAllButton = findViewById(R.id.alldelete);
        Button backButton = findViewById(R.id.returnsecond);

        partialDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = editTextId.getText().toString();
                if (!idStr.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idStr);
                        dbHelper.deleteDataById(id);
                        Toast.makeText(FiveActivity.this, "数据已按ID删除", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(FiveActivity.this, "请输入有效的ID", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FiveActivity.this, "请输入ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAllData();
                dbHelper.resetIdCounter();
                Toast.makeText(FiveActivity.this, "所有数据已删除", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}