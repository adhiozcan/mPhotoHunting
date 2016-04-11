package com.wisnu.photohunting.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wisnu.photohunting.R;

import static android.view.View.*;

public class WelcomeActivity extends AppCompatActivity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.welcome_btn_signin).setOnClickListener(this);
        findViewById(R.id.welcome_btn_signup).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_btn_signin:
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                break;
            case R.id.welcome_btn_signup:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }
    }
}
