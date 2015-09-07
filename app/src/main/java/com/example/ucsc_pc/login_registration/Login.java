package com.example.ucsc_pc.login_registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Login extends AppCompatActivity implements View.OnClickListener{

    public static final String USER_NAME = "USER_NAME";

    public static final String PASSWORD = "PASSWORD";

    private static final String LOGIN_URL = "http://efalearner.co.nf/efa_login/login.php";

    private EditText editTextUserName;
    private EditText editTextPassword;

    private Button buttonLogin;
    private Button buttonNewAccount;

    SharedPreferences login_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_pref = getApplicationContext().getSharedPreferences("LoginCounter", MODE_PRIVATE);

        int count = login_pref.getInt("count", 0);

        if(count>0) {

            Intent intent = new Intent(Login.this, UserProfile.class);
            intent.putExtra(USER_NAME, login_pref.getString("username",null));
            startActivity(intent);

        }else {

            editTextUserName = (EditText) findViewById(R.id.username);
            editTextPassword = (EditText) findViewById(R.id.password);

            buttonLogin = (Button) findViewById(R.id.buttonUserLogin);
            buttonNewAccount = (Button) findViewById(R.id.buttonCreateNewAccount);

            buttonLogin.setOnClickListener(this);
            buttonNewAccount.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            login();
        }

        if(v == buttonNewAccount){
            Intent intent = new Intent(Login.this,Register.class);
            //intent.putExtra(USER_NAME,username);
            startActivity(intent);
        }
    }

    private void login(){
        String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        userLogin(username, password);
    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){

                    SharedPreferences.Editor editor = login_pref.edit();
                    editor.putString("username", username);
                    editor.putInt("count", 1);
                    editor.commit();

                    Intent intent = new Intent(Login.this,UserProfile.class);
                    intent.putExtra(USER_NAME,username);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();
                String result = ruc.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }

}