package com.example.user.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ApiClient;
import com.example.user.R;
import com.example.user.SharedPrfManager;
import com.example.user.StatusModel;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class SaveRestActivity extends AppCompatActivity {

//    private Date date;
    private TextView id_date;
    private AppCompatButton btn_rest,btn_close;
    private TextView txt_date_picker;
    private EditText ed_description;
    private String rest_date = "null";
    private String request_date = "null";
    private PersianDate pdate;
    private PersianDateFormat pdformater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_rest);

        id_date = findViewById(R.id.id_date);

        btn_rest = findViewById(R.id.btn_rest);
        btn_close = findViewById(R.id.btn_close);
        ed_description = findViewById(R.id.ed_description);

        txt_date_picker = findViewById(R.id.txt_date);
        txt_date_picker.setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(this, R.drawable.ic_date), null);
        txt_date_picker.setOnClickListener(view -> showCalendar());

        ProgressBar prg_main = findViewById(R.id.prd_main);

        pdate = new PersianDate();
        pdformater = new PersianDateFormat("Y/m/d");

        request_date = pdformater.format(pdate);

        btn_close.setOnClickListener(v -> {
            finish();
        });

        btn_rest.setOnClickListener(v -> {
            if (!request_date.equals("null") && !rest_date.equals("null") && !ed_description.getText().toString().isEmpty()){
                prg_main.setVisibility(View.VISIBLE);
                btn_rest.setEnabled(false);
                txt_date_picker.setEnabled(false);
                ApiClient.getInstance().getApi().Api_save_rest(
                        ed_description.getText().toString(),
                        request_date,
                        rest_date,
                        new SharedPrfManager(SaveRestActivity.this).getId()).
                        enqueue(new Callback<StatusModel>() {
                    @Override
                    public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {

                        prg_main.setVisibility(View.GONE);
                        btn_rest.setEnabled(true);
                        txt_date_picker.setEnabled(true);

                        if (response.isSuccessful() && response.body().getStatus().equals("ok")){

                            Toast.makeText(SaveRestActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                        else {

                            Toast.makeText(SaveRestActivity.this, "خطای غیر منتظره", Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<StatusModel> call, Throwable t) {
                        prg_main.setVisibility(View.GONE);
                        btn_rest.setEnabled(true);
                        txt_date_picker.setEnabled(true);

                        Toast.makeText(SaveRestActivity.this, "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else {
                Toast.makeText(SaveRestActivity.this, "لطفا تمامی روز مرخصی و توضیحات مربوطه را کامل کنید", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void showCalendar() {

        PersianDatePickerDialog picker = new PersianDatePickerDialog(this)
                .setPositiveButtonString("تایید")
                .setNegativeButton("انصراف")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1400)
                .setMaxYear(1600)
                .setActionTextColor(Color.GRAY)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {

                        id_date.setText(" زمان انتخاب شده: "+persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
//                        date =persianCalendar.getTime();
                        rest_date = persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay();
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();

    }

}