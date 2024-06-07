package signup;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.myapplication.R;
import java.io.IOException;
import java.io.OutputStream;


public class SevenActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_WRITE_STORAGE = 100; // 请求写入存储权限的请求码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 检查是否有写入存储权限
                if (ContextCompat.checkSelfPermission(SevenActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // 如果没有权限，向用户请求权限
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_STORAGE);
                    }
                } else {
                    // 如果已有权限，保存图片到相册
                    saveImageToGallery();
                }
            }
        });
    }

    // 处理权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 如果用户授予了存储权限，保存图片到相册
                saveImageToGallery();
            } else {
                // 如果用户拒绝了存储权限，显示提示信息
                Toast.makeText(this, "需要存储权限来保存图片", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 保存图片到相册
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void saveImageToGallery() {
        // 从资源文件中解码位图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.save);

        // 创建插入图像的内容值对象
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "我的图片");
        values.put(MediaStore.Images.Media.DESCRIPTION, "地图");

        Uri uri = null;
        try {
            // 向外部存储的图片媒体库插入图像
            uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (uri != null) {
                // 获取输出流并将位图压缩为JPEG格式写入流中
                OutputStream outputStream = getContentResolver().openOutputStream(uri);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.close();

                // 通知媒体库扫描新添加的文件
                MediaScannerConnection.scanFile(this, new String[]{uri.getPath()}, null, null);

                Toast.makeText(this, "图片已保存到相册", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "保存图片时出错", Toast.LENGTH_SHORT).show();
        }
    }
}