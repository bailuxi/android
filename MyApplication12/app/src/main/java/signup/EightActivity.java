package signup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class EightActivity extends Activity {
    private Intent goToBrowser,goToSecondActivity,goToCall,goToCall2,goToCall3;
    private Button toSecond, toBrowser,makeCall,makeCall2,makeCall3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        //打开浏览器
        goToBrowser = new Intent(Intent.ACTION_VIEW);
        goToBrowser.setData(Uri.parse("https://www.xialingying.cc/snjs/index.html"));
        toBrowser = (Button)findViewById(R.id.guanwang);
        toBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(goToBrowser);}
        });
        //拨打第一个电话
        goToCall = new Intent(Intent.ACTION_DIAL);
        goToCall.setData(Uri.parse("tel:4006880688"));
        makeCall = (Button)findViewById(R.id.call1);
        makeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToCall);
            }
        });

        //拨打第二个电话
        goToCall2 = new Intent(Intent.ACTION_DIAL);
        goToCall2.setData(Uri.parse("tel:6002323932"));
        makeCall2 = (Button)findViewById(R.id.call2);
        makeCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToCall2);
            }
        });
        //拨打第三个电话
        goToCall3 = new Intent(Intent.ACTION_DIAL);
        goToCall3.setData(Uri.parse("tel:8020236812"));
        makeCall3 = (Button)findViewById(R.id.call3);
        makeCall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToCall3);
            }
        });

        //返回主页，隐性启动
        goToSecondActivity = new Intent("android.intent.action.Second");
        toSecond = (Button)findViewById(R.id.returnmain);
        toSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "zzy";
                goToSecondActivity.putExtra("name", name);
                startActivity(goToSecondActivity);
            }
        });
    }
}