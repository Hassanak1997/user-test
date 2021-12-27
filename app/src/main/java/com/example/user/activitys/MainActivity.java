package com.example.user.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ApiClient;
import com.example.user.R;
import com.example.user.SharedPrfManager;
import com.example.user.StatusModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ed_username = findViewById(R.id.ed_username);
        EditText ed_password = findViewById(R.id.ed_password);
        TextView txt_register = findViewById(R.id.txt_register);
        ProgressBar prg_main = findViewById(R.id.prd_main);
        AppCompatButton btn_enter = findViewById(R.id.btn_enter);

        btn_enter.setOnClickListener(v -> {
//            if (ed_username.getText().toString().equals(USERNAME) && ed_password.getText().toString().equals(PASSWORD)){
//                startActivity(new Intent(MainActivity.this,MenuActivity.class));
//            }
//            else
//                Toast.makeText(this, "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();

            if (!ed_username.getText().toString().isEmpty() && !ed_password.getText().toString().isEmpty()){
                prg_main.setVisibility(View.VISIBLE);
                btn_enter.setEnabled(false);
                ApiClient.getInstance().getApi().Api_login("login",
                        "",
                        ed_username.getText().toString(),
                        ed_password.getText().toString(),
                        "").enqueue(new Callback<StatusModel>() {
                    @Override
                    public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                        prg_main.setVisibility(View.GONE);
                        btn_enter.setEnabled(true);
                        Log.d("testttt", "onResponse: " + response.body().getFullname());
                        if(response.isSuccessful() && response.body().getStatus().equals("ok")){
                            Toast.makeText(MainActivity.this, "با موفقیت وارد شدید", Toast.LENGTH_SHORT).show();

                            new SharedPrfManager(MainActivity.this).
                                    saveUser(response.body().getFullname(),response.body().getPhone(),response.body().getId());
                            startActivity(new Intent(MainActivity.this,MenuActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "نام کاربری یا رمز عبور اشتباه است", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusModel> call, Throwable t) {
                        Log.d("testttt", "onFailure: " + t.getMessage());
                        Toast.makeText(MainActivity.this, "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();
                        prg_main.setVisibility(View.GONE);
                        btn_enter.setEnabled(true);
                    }
                });
            }
            else {
                Toast.makeText(MainActivity.this, "نام کاربری یا رمز عبور نامعتبر است", Toast.LENGTH_SHORT).show();
            }

        });

        txt_register.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,RegisterActivity.class));
        });

    }
}