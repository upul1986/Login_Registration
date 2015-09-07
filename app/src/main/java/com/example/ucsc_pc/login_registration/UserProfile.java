package com.example.ucsc_pc.login_registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private Button goToTopics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        goToTopics = (Button) findViewById(R.id.goToTopics);
        goToTopics.setOnClickListener(this);
        goToTopics.setTag("goToTopics");

        textView = (TextView) findViewById(R.id.textViewUserName);

        Intent intent = getIntent();

        String username = intent.getStringExtra(Login.USER_NAME);

        textView.setText("Welcome User "+username);
    }

    @Override
    public void onClick(View v) {
        if(v.getTag()=="goToTopics"){
            Intent intent = new Intent(UserProfile.this, ForumTopics.class);
            //intent.putExtra(USER_NAME, login_pref.getString("username",null));
            startActivity(intent);

        }
    }
}