package com.example.qq;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener  {
	
	
	private EditText eN;
	private EditText eP;
	private Button bt;
	private CheckBox cb;
	boolean cf=false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button) findViewById(R.id.btn);
        bt.setOnClickListener(this);
       eN=(EditText) findViewById(R.id.et_num);
       eP=(EditText) findViewById(R.id.et_p);
        cb=(CheckBox) findViewById(R.id.cB1);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton a0, boolean a1) {
				if(a1){
					Toast.makeText(MainActivity.this, "已经记住账号", 0).show();
				cf=true;
				}else{
					cf=false;
				}
				
			}});
      
        Map<String ,String > userInfo=getUser(this);
        if(userInfo!=null){
        	eN.setText(userInfo.get("uname"));
        	eP.setText(userInfo.get("upwd"));
        }
       
       
        
        
    }

	
	public void onClick(View v) {
		String name=eN.getText().toString().trim();
		String password=eP.getText().toString().trim();
		switch(v.getId()){
		case R.id.btn:
			if(TextUtils.isEmpty(name)){
				Toast.makeText(this, "亲输入qq号", 0).show();
			}
			if(TextUtils.isEmpty(password)){
				Toast.makeText(this, "亲输入密码", 0).show();
			}
			if(cf){
			boolean isB=	saveUser(MainActivity.this,eN.getText().toString().trim(),eP.getText().toString().trim());
			if(isB){
				Toast.makeText(MainActivity.this, "保存成功", 0).show();
			}else{
				Toast.makeText(MainActivity.this,"失败",0).show();
			}
		}
			
				Toast.makeText(this, "登陆成功", 0).show();
				Intent intent=new Intent(this,YLogin.class);
				startActivity(intent);
			
			break;
			
			
		}
		
	}

	
	
	
	public static boolean saveUser(Context context,String n,String p){
		SharedPreferences sp=context.getSharedPreferences("data1",Context.MODE_PRIVATE);
	Editor edit=sp.edit();
	edit.putString("uname", n);
	edit.putString ("up", p);
	edit.commit();
	return true;
	
	
	}
	public static Map<String,String> getUser(Context context){
		SharedPreferences op=context.getSharedPreferences("data1", Context.MODE_PRIVATE);
		String name=op.getString("uname", null);
		String password=op.getString("up", null);
		Map<String ,String > userMap=new HashMap<String,String>();
		userMap.put("uname", name);
		userMap.put("upwd", password);
		return userMap;
	}

 
    
}
