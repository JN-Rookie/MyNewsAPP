package edu.feicui.mynewsapp.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import edu.feicui.mynewsapp.R;

public class NewsListActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private FragmentMenu      mFragmentMenu;//左菜单栏
    private FragmentMenuRight mFragmentMenuRight;//右菜单栏
    private RelativeLayout    rl_main;//中间主布局
    private RelativeLayout    rl_left_frg;//左菜单栏布局
    private RelativeLayout    rls_right_frg;//右菜单栏布局
    private ImageButton       mButtonHome;//左侧菜单栏弹出按钮
    private ImageButton       mButtonShare;//右侧侧菜单栏弹出按钮
    private boolean First = true;
    private int x1, x2, x3;//定义屏幕X轴坐标
    private RelativeLayout.LayoutParams mLayoutParams;//主布局对象
    private int                         mWidth;//手机屏幕宽度
    private ListView                    mListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initView();
        isFirstRun();

    }

    //    初始化控件，关联ID,设置监听
    private void initView() {
        mButtonHome = (ImageButton) findViewById(R.id.title_home);
        mButtonShare = (ImageButton) findViewById(R.id.title_share);
        rl_left_frg= (RelativeLayout) findViewById(R.id.rl_left_frg);
        rls_right_frg= (RelativeLayout) findViewById(R.id.rl_right_frg);
        rl_main= (RelativeLayout) findViewById(R.id.rl_main);
        mListView= (ListView) findViewById(R.id.lv_list);
        mButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLeftFragment();
            }
        });
        mButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRightFragment();
            }
        });
    }

    //    加载右侧菜单栏
    private void getRightFragment() {
        if (mFragmentMenuRight == null) {
            mFragmentMenuRight = new FragmentMenuRight();
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frg_right, mFragmentMenuRight);//加载fragment布局
        transaction.commit();//提交
        int width = rls_right_frg.getWidth();//右侧菜单栏宽度
        mLayoutParams.leftMargin = -width;//设置左边距相对于屏幕的偏移量
        mLayoutParams.rightMargin = width;//设置右边距相对于屏幕的偏移量
        rl_main.setLayoutParams(mLayoutParams);//重新加载布局
    }

    //    加载左侧菜单栏
    private void getLeftFragment() {
        if (mFragmentMenu == null) {
            mFragmentMenu = new FragmentMenu();
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frg_left, mFragmentMenu);//加载fragment布局
        transaction.commit();//提交
        int width = rl_left_frg.getWidth();//左侧菜单栏宽度
        mLayoutParams.leftMargin = width;//设置左边距相对于屏幕的偏移量
        mLayoutParams.rightMargin = -width;//设置右边距相对于屏幕的偏移量
        rl_main.setLayoutParams(mLayoutParams);//重新加载布局
    }

    //      初始化屏幕偏移量,并移除两侧的菜单栏
    public void initLocation() {
        mLayoutParams.leftMargin = 0;
        mLayoutParams.rightMargin = 0;
        rl_main.setLayoutParams(mLayoutParams);//重新加载布局
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mFragmentMenu != null) {
            transaction.remove(mFragmentMenu);//移除左侧菜单栏
        }
        if (mFragmentMenuRight != null) {
            transaction.remove(mFragmentMenuRight);//移除右侧菜单栏
        }
        transaction.commit();
    }

    //    判断是否第一次加载当前activity，如果是获取屏幕的宽度，并锁定主布局的宽度
    public void isFirstRun() {
        if (First) {
            //获取的手机屏幕的宽度(PX)
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            mWidth = metrics.widthPixels;
            mLayoutParams = (RelativeLayout.LayoutParams) rl_main.getLayoutParams();
            mLayoutParams.width = mWidth;
            First = false;
        }
    }

    //    重写一个点击方法，时间左右两边菜单的滑动
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                x3=rl_main.getLeft();//主布局左侧偏移量
                x1 = (int) event.getX();//获取按下的X轴坐标
                Log.d(TAG, "onTouchEvent: " + x3);
                break;
            case MotionEvent.ACTION_UP:
                x2 = (int) event.getX();//获取松开时的X轴坐标
                Log.d(TAG, "onTouchEvent: " + x2);
                //向右滑
                if ((x2 - x1) > 0) {
                    if ((x2 - x1) > 200&&(x1-x3)<200) {//滑动距离大于200px并且滑动起点相对于主布局左边距的坐标小于200px
                        getLeftFragment();
                    }
                    if ((x2-x1)>200&&(x1-x3)>(mWidth-200))//滑动距离大于200px并且滑动起点距离主布局右边距小于200px
                    {initLocation();}
                }
                //向左滑
                if ((x2 - x1) < 0) {
                    if (Math.abs((x2 - x1)) > 200&&((x1-x3))>(mWidth-200)) {
                        getRightFragment();
                    }
                    if((Math.abs((x2-x1))>200)&&(x1-x3)<200){
                        initLocation();
                    }
                }

                break;
        }

        return super.onTouchEvent(event);
    }
}
