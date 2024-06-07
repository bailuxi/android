package signup;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import more.DatabaseHelper;

public class FourthActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    TextView displayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        myDb = new DatabaseHelper(this);
        displayData = findViewById(R.id.display_data);

        Cursor res = myDb.getAllData();// 获取所有数据的游标对象
        if (res.getCount() == 0) {
            displayData.setText("暂无数据");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {// 遍历,将每一行字段拼接
            buffer.append("参赛序号 :").append(res.getString(0)).append("\n");
            buffer.append("姓名 :").append(res.getString(1)).append("\n");
            buffer.append("年龄 :").append(res.getString(2)).append("\n");
            buffer.append("性别 :").append(res.getString(3)).append("\n");
            buffer.append("身份 :").append(res.getString(4)).append("\n");
            buffer.append("手机号 :").append(res.getString(5)).append("\n\n");
        }

        displayData.setText(buffer.toString());//在TextView中数据显示
    }
}
