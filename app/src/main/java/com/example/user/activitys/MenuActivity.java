package com.example.user.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.R;
import com.example.user.SharedPrfManager;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        AppCompatButton btn_request,btn_myfrees,btn_about,btn_logout;
        btn_request = findViewById(R.id.btn_request);
        btn_myfrees = findViewById(R.id.btn_myfrees);
        btn_about = findViewById(R.id.btn_about);
        btn_logout = findViewById(R.id.btn_logout);

        TextView txt_name,txt_phone;
        txt_name = findViewById(R.id.txt_name);
        txt_phone = findViewById(R.id.txt_phone);

        SharedPrfManager sharedPrfManager = new SharedPrfManager(MenuActivity.this);

        txt_name.setText(sharedPrfManager.getName());
        txt_phone.setText(sharedPrfManager.getPhone());

        btn_about.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this,AboutActivity.class));
        });

        btn_request.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this,SaveRestActivity.class));
        });

        btn_myfrees.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this,RestListActivity.class));
        });

        btn_logout.setOnClickListener(v -> {
            startActivity(new Intent(MenuActivity.this,MainActivity.class));
            Toast.makeText(MenuActivity.this, "با موفقیت خارج شدید", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}