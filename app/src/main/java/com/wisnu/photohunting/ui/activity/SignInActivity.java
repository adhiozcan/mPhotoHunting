package com.wisnu.photohunting.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.victor.loading.rotate.RotateLoading;
import com.wisnu.photohunting.R;
import com.wisnu.photohunting.model.User;
import com.wisnu.photohunting.network.Request;
import com.wisnu.photohunting.network.Response;
import com.wisnu.photohunting.savingstate.UserData;
import com.wisnu.photohunting.system.Utils;

import retrofit2.Call;
import retrofit2.Callback;

public class SignInActivity extends AppCompatActivity {
    private RotateLoading rotateLoading;

    private void authenticate(String email, String password) {
        rotateLoading.start();
        Request.User.login(email, password).enqueue(new Callback<Response.User>() {
            @Override
            public void onResponse(Call<Response.User> call, retrofit2.Response<Response.User> response) {
                rotateLoading.stop();
                if (response.isSuccessful()) {
                    User userAuth = response.body().getUserData().get(0);
                    if (userAuth != null) {
                        String userId           = userAuth.getUserId();
                        String userName         = userAuth.getUserName();
                        String userEmail        = userAuth.getUserEmail();
                        String userPasword      = userAuth.getUserPasword();
                        String userPhotoProfile = userAuth.getUserPhotoProfile();
                        String userStatus       = userAuth.getUserStatus();
                        String userStatusDate   = userAuth.getUserStatusDate();
                        String userSince        = userAuth.getUserSince();

                        User user = new User();
                        user.setUserId(userId);
                        user.setUserName(userName);
                        user.setUserEmail(userEmail);
                        user.setUserPasword(userPasword);
                        user.setUserPhotoProfile(userPhotoProfile);
                        user.setUserStatus(userStatus);
                        user.setUserStatusDate(userStatusDate);
                        user.setUserSince(userSince);

                        UserData.getInstance().setUser(user);
                        Utils.showOnConsole("onResponse", "user Id : " + userId);
                        Utils.showOnConsole("onResponse", "userName : " + userName);
                        Utils.showOnConsole("onResponse", "userMail : " + userEmail);

                        startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Utils.showToast(SignInActivity.this, "Gagal menghubungi server, coba beberapa saat lagi");
                    }
                }
            }

            @Override
            public void onFailure(Call<Response.User> call, Throwable t) {
                rotateLoading.stop();
                Utils.showToast(SignInActivity.this, "User dan Password tidak cocok");
            }

        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);

        findViewById(R.id.signin_btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed_email    = (EditText) findViewById(R.id.signin_ed_email);
                EditText ed_password = (EditText) findViewById(R.id.signin_ed_password);

                String email    = ed_email.getText().toString();
                String password = ed_password.getText().toString();

                authenticate(email, password);
            }
        });
    }
}
