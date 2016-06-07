package edu.feicui.mynewsapp.ui;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.feicui.mynewsapp.R;
import edu.feicui.mynewsapp.utils.Constants;
import edu.feicui.mynewsapp.utils.MD5utils;
import edu.feicui.mynewsapp.utils.SPUtils;

public class LogonScreenActivity extends AppCompatActivity {
    private EditText mEdt_name;
    private EditText mEdt_psw;
    private Button mBtn_login;
    private Button mBtn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon_screen);
        initView();
    }

    private void initView() {
        mEdt_name = (EditText) findViewById(R.id.edt_name);
        mEdt_psw = (EditText) findViewById(R.id.edt_psw);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_register = (Button) findViewById(R.id.btn_psw);
        mBtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetupDialog();
            }
        });
        mBtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEnterDialog();
                Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showEnterDialog() {

//                校验文本框内容
                String name=mEdt_name.getText().toString().trim();
                String pwd=mEdt_psw.getText().toString().trim();
                pwd= MD5utils.encode(pwd);
//                是否为空
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                String savename=SPUtils.getString(getApplicationContext(), Constants.USERNAME);
                String savepwd=SPUtils.getString(getApplicationContext(),Constants.USER_PWD);
//                是否相同
                if(!name.equals(savename)){
                    Toast.makeText(getApplicationContext(),"用户名不存在",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pwd.equals(savepwd)){
                    Toast.makeText(getApplicationContext(),"输入密码有误",Toast.LENGTH_SHORT).show();
                    return;
                }
//                保存edittext里的内容
                SPUtils.putString(getApplicationContext(),Constants.USERNAME,name);
                SPUtils.putString(getApplicationContext(), Constants.USER_PWD,pwd);
                Intent intent=new Intent(LogonScreenActivity.this,USERActivity.class);
                startActivity(intent);
                finish();
    }

    private void showSetupDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View view=View.inflate(getApplicationContext(),R.layout.item_login,null);
        final EditText etname= (EditText) view.findViewById(R.id.edt_username);
        final EditText etPwd= (EditText) view.findViewById(R.id.edt_userpwd);
        final EditText etconfirmPwd= (EditText) view.findViewById(R.id.edt_userpwd1);

        Button btn_cancle= (Button) view.findViewById(R.id.btn_cancle);
        Button btn_submit= (Button) view.findViewById(R.id.btn_submit);
        builder.setView(view);
        final AlertDialog dialog=builder.show();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                校验文本框内容
                String name=etname.getText().toString().trim();
                String pwd=etPwd.getText().toString().trim();
                String confirmPwd=etconfirmPwd.getText().toString().trim();

//                是否为空
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(),"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pwd)||TextUtils.isEmpty(confirmPwd)){
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
//                是否相同
                if(!pwd.equals(confirmPwd)){
                    Toast.makeText(getApplicationContext(),"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                String savename=SPUtils.getString(getApplicationContext(),Constants.USERNAME);
                if (name.equals(savename)){
                    Toast.makeText(getApplicationContext(),"用户名已存在",Toast.LENGTH_SHORT).show();
                    return;
                }
                pwd=MD5utils.encode(pwd);
//                保存edittext里的内容
                SPUtils.putString(getApplication(),Constants.USERNAME,name);
                SPUtils.putString(getApplicationContext(), Constants.USER_PWD,pwd);
                dialog.dismiss();
                Toast.makeText(LogonScreenActivity.this,"注册成功",Toast.LENGTH_LONG).show();
            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

}
