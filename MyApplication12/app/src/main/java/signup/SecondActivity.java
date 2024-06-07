package signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;

import more.ImageAdapter;

public class SecondActivity extends Activity {
    private Intent  goToThreeActivity,goToFourthActivity,goToFiveActivity,goToSixActivity,goToSevenActivity,goToEightActivity;
    private ImageButton toThree,toFourth,toFive,toSix,toSeven,toEight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView welcomeText = findViewById(R.id.welcome);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            String name = bundle.getString("name");
            welcomeText.setText("欢迎亲爱的选手：" + name);
        } else {
            welcomeText.setText("欢迎亲爱的选手：");
        }

        //跳转信息提交，显性启动
        goToThreeActivity = new Intent(this, ThreeActivity.class);
        toThree = (ImageButton)findViewById(R.id.submit);
        toThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToThreeActivity);
            }
        });

        //查询提交情况，显性启动
        goToFourthActivity = new Intent(this, FourthActivity.class);
        toFourth = (ImageButton)findViewById(R.id.search);
        toFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToFourthActivity);
            }
        });

        //删除已提交内容，显性启动
        goToFiveActivity = new Intent(this, FiveActivity.class);
        toFive = (ImageButton)findViewById(R.id.delete);
        toFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToFiveActivity);
            }
        });

        //去活动预览，显性启动
        goToSixActivity = new Intent(this, SixActivity.class);
        toSix = (ImageButton)findViewById(R.id.oldworks);
        toSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToSixActivity);
            }
        });

        //去导航到地址，显性启动
        goToSevenActivity = new Intent(this, SevenActivity.class);
        toSeven = (ImageButton)findViewById(R.id.run);
        toSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToSevenActivity);
            }
        });

        //去联系主办方电话，显性启动
        goToEightActivity = new Intent(this, EightActivity.class);
        toEight = (ImageButton)findViewById(R.id.call);
        toEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(goToEightActivity);
            }
        });

        ViewPager viewPager = findViewById(R.id.viewPager);// 获取布局中的ViewPager控件
        ImageAdapter adapter = new ImageAdapter(this, new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3}, viewPager);// 创建图片适配器对象，并传入图片资源数组和ViewPager对象
        viewPager.setAdapter(adapter);//// 设置ViewPager的适配器为ImageAdapter

        adapter.startAutoScroll();// 开始自动滚动
    }
}

