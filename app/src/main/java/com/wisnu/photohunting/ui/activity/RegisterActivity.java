package com.wisnu.photohunting.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wisnu.photohunting.R;
import com.wisnu.photohunting.network.Request;
import com.wisnu.photohunting.network.Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class RegisterActivity extends AppCompatActivity {
    private EditText edName;
    private EditText edEmail;
    private EditText edPassword;
    private EditText edCoPassword;

    private void onSuccess() {
        Toast.makeText(getBaseContext(), "Register Successfully, Congratulations :)", Toast.LENGTH_SHORT).show();
    }

    private void onFailed() {
        Toast.makeText(getBaseContext(), "Register Failed, Periksa kembali data Anda", Toast.LENGTH_SHORT).show();
    }

    Button.OnClickListener mSubmitClickListener() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        };
    }

    private void submit() {
        String nama       = edName.getText().toString();
        String email      = edEmail.getText().toString();
        String password   = edPassword.getText().toString();
        String copassword = edCoPassword.getText().toString();

        Request.User.register(nama, email, password, copassword).
                enqueue(new Callback<Response.Basic>() {
                    @Override
                    public void onResponse(Call<Response.Basic> call, retrofit2.Response<Response.Basic> response) {
                        if (response.isSuccessful())
                            if (response.body().getStatus() != null) onSuccess();
                            else onFailed();

                        startActivity(new Intent(RegisterActivity.this, SignInActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Response.Basic> call, Throwable t) {
                        onFailed();
                    }
                });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edName = (EditText) findViewById(R.id.register_ed_name);
        edEmail = (EditText) findViewById(R.id.register_ed_email);
        edPassword = (EditText) findViewById(R.id.register_ed_password);
        edCoPassword = (EditText) findViewById(R.id.register_ed_confirm);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(mSubmitClickListener());
    }
}
