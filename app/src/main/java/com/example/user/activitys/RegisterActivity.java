package com.example.user.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.ApiClient;
import com.example.user.R;
import com.example.user.StatusModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        AppCompatButton btn_close = findViewById(R.id.btn_close);
        AppCompatButton btn_register = findViewById(R.id.btn_register);
        EditText ed_username = findViewById(R.id.ed_username);
        EditText ed_password = findViewById(R.id.ed_password);
        EditText ed_phone = findViewById(R.id.ed_phone);
        EditText ed_name = findViewById(R.id.ed_name);
        ProgressBar prg_main = findViewById(R.id.prd_main);

        btn_close.setOnClickListener(v -> finish());

        btn_register.setOnClickListener(v -> {

            if (!ed_username.getText().toString().isEmpty() &&
                    !ed_password.getText().toString().isEmpty() &&
                    !ed_name.getText().toString().isEmpty() &&
                    !ed_phone.getText().toString().isEmpty()){
                prg_main.setVisibility(View.VISIBLE);
                btn_register.setEnabled(false);
                btn_close.setEnabled(false);
                ApiClient.getInstance().getApi().Api_login("register",ed_phone.getText().toString(),
                        ed_username.getText().toString(),
                        ed_password.getText().toString(),
                        ed_name.getText().toString()).enqueue(new Callback<StatusModel>() {
                    @Override
                    public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                        prg_main.setVisibility(View.GONE);
                        btn_register.setEnabled(true);
                        btn_close.setEnabled(true);
                        if (response.isSuccessful() && response.body().getStatus().equals("ok")){
                            Toast.makeText(RegisterActivity.this, "ثبت نام با موفقیت انجام شد\n لطفا وارد شوید", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "اطلاعات غیر منتظره", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusModel> call, Throwable t) {
                        prg_main.setVisibility(View.GONE);
                        btn_register.setEnabled(true);
                        btn_close.setEnabled(true);
                        Toast.makeText(RegisterActivity.this, "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else {
                Toast.makeText(RegisterActivity.this, "لطفا تمامی فیلد ها را وارد کنید", Toast.LENGTH_SHORT).show();
            }

        });

    }
}