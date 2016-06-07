package edu.feicui.mynewsapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.feicui.mynewsapp.R;
import edu.feicui.mynewsapp.utils.Constants;
import edu.feicui.mynewsapp.utils.SPUtils;

public class USERActivity extends AppCompatActivity {

    private TextView mUsername;
    private TextView mUser_point;
    private Button mBtn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
    }

    private void initView() {
        mUsername = (TextView) findViewById(R.id.tv_username);
        mUser_point = (TextView) findViewById(R.id.tv_user_point);
        mBtn_exit = (Button) findViewById(R.id.btn_exit);
        mUsername.setText(SPUtils.getString(getApplicationContext(), Constants.USERNAME));
        mUser_point.setText("0");
        mBtn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(USERActivity.this,LogonScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
