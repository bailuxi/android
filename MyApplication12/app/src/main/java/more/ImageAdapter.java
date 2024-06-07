package more;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private int[] images;
    private Handler handler; // 用于实现自动滚动
    private Runnable update; // 更新ViewPager的当前页面
    private int delay = 1000; // 滚动延迟时间
    private ViewPager viewPager;

    // 接收上下文对象、图片资源数组和ViewPager对象作为参数
    public ImageAdapter(Context context, int[] images, ViewPager viewPager) {
        this.context = context;
        this.images = images;
        this.viewPager = viewPager;
        handler = new Handler(Looper.getMainLooper()); // 创建Handler对象在主线程中执行
        update = new Runnable() {
            public void run() {
                int currentPage = ImageAdapter.this.viewPager.getCurrentItem(); // 获取当前页索引
                int totalPages = getCount();
                // 如果当前页是最后一页，则跳转到第一页，否则跳转到下一页
                if (currentPage == totalPages - 1) {
                    ImageAdapter.this.viewPager.setCurrentItem(0);
                } else {
                    ImageAdapter.this.viewPager.setCurrentItem(currentPage + 1);
                }
            }
        };
    }

    // 获取页面数量
    @Override
    public int getCount() {
        return images.length;
    }

    // 判断当前视图是否为对象生成的视图
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 实例化指定位置的页面并将其添加到容器中
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 获取布局填充器
        View itemView = inflater.inflate(R.layout.picitem, container, false); // 填充布局文件

        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]); // 设置图片资源

        container.addView(itemView); // 将填充后的布局添加到容器中

        return itemView;
    }

    // 销毁指定位置的页面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object); // 从容器中移除页面
    }

    // 启动自动滚动
    public void startAutoScroll() {
        // 使用Handler实现定时任务，定时更新ViewPager的当前页面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                update.run();
                handler.postDelayed(this, delay); // 延迟指定时间后再次执行
            }
        }, delay);
    }
}
