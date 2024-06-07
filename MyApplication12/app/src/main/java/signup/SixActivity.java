package signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SixActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


        Intent intent = getIntent();
        ListView lvdata = (ListView) findViewById(R.id.lvdata);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); //创建存储数据的列表

        Map<String, Object> map = new HashMap<String, Object>();// 添加每个图片资源和文本内容到列表中
        map.put("img", R.drawable.pa1);
        map.put("text","7天“特战精英”成长-提升领导力+激发潜能+团队协作军事夏令营（北京）");
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("img", R.drawable.pa2);
        map.put("text","10天“先锋勇士”强化-磨炼意志+强健体魄+成长蜕变军事夏令营（北京）");
        list.add(map);
        map=new HashMap<String,Object>();
        map.put("img", R.drawable.pa3);
        map.put("text","10天“先锋勇士”强化-磨炼意志+强健体魄+成长蜕变军事夏令营（北京）");
        list.add(map);
        map=new HashMap<String,Object>();
        map.put("img", R.drawable.pa4);
        map.put("text","28天“虎狼之师”少年特种兵-挑战极限+独立自主+心智成长军事夏令营（北京）");
        list.add(map);
        map=new HashMap<String,Object>();
        map.put("img", R.drawable.pa5);
        map.put("text","14天“最强兵王”集训-军事技能+野外拉练+格斗技巧军事夏令营（天津）");
        list.add(map);
        map=new HashMap<String,Object>();
        map.put("img", R.drawable.pa6);
        map.put("text","14天“枭狼战队”魔鬼-增强体质+挑战极限+提升自信军事夏令营（天津）");
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("img", R.drawable.pa7);
        map.put("text","21天“蛟龙将校”利剑领袖-霹雳行动+校园反恐+安全课堂军事夏令营（上海）");
        list.add(map);

        map=new HashMap<String,Object>();
        map.put("img", R.drawable.pa8);
        map.put("text","28天“虎狼之师”少年特种兵-越野摩托体验+泥潭特训军事夏令营（上海）");
        list.add(map);
        SimpleAdapter simpleAdapter=new SimpleAdapter(SixActivity.this,list,R.layout.item,new String[]{"img","text"},new int[]{R.id.img,R.id.totext});//创建SimpleAdapter对象，并设置数据源和布局
        lvdata.setAdapter(simpleAdapter);// 设置ListView的适配器为SimpleAdapter
    }
}
