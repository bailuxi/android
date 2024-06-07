package signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText accountEdit,passwordEdit;
    private Button login,register;
    private CheckBox rememberPass,agreePass;
    private Toolbar toolbar;
    private Intent goToSecondActivity;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        pref=getSharedPreferences("data",MODE_PRIVATE);
        accountEdit=(EditText)findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password) ;
        rememberPass=(CheckBox)findViewById(R.id.remember);
        agreePass=(CheckBox)findViewById(R.id.agree);
        login=(Button)findViewById(R.id.loginButton);
        register=(Button)findViewById(R.id.registerButton);

        goToSecondActivity = new Intent(this, SecondActivity.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!agreePass.isChecked()) {
                    Toast.makeText(MainActivity.this, "请先同意协议", Toast.LENGTH_SHORT).show();}
                else {
                    if (accountEdit.getText().toString().equals("zzy") && passwordEdit.getText().toString().equals("123")) {
                        if(rememberPass.isChecked()){// 如果用户名和密码匹配并且复选框被选中，保存数据到SharedPreferences中
                            editor = pref.edit();
                            editor.putString("account", accountEdit.getText().toString());
                            editor.putString("password", passwordEdit.getText().toString());
                            editor.apply();
                        }
                        else{}
                        // 创建Intent，并传递数据到SecondActivity
                        String name = accountEdit.getText().toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        goToSecondActivity.putExtras(bundle);
                        // 启动SecondActivity
                        startActivity(goToSecondActivity);
                    }
                    else {
                        // 如果用户名和密码不匹配或者复选框未被选中，显示错误信息
                        Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "请先联系主办方账号密码", Toast.LENGTH_SHORT).show();}});

        //当Activity启动时判断有没有已保存的数据，如果有就显示在界面中
        String account=pref.getString("account","");
        String password=pref.getString("password","");
        if(!account.equals("") && !password.equals("")){
            accountEdit.setText(account);
            passwordEdit.setText(password);
        }
    }
}
