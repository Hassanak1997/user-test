package com.example.user.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.ApiClient;
import com.example.user.R;
import com.example.user.Rest;
import com.example.user.RestAdapter;
import com.example.user.SharedPrfManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestListActivity extends AppCompatActivity {

    RestAdapter adapter;
    ProgressBar prg_main;
    TextView txt_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_list);



        RecyclerView rv = findViewById(R.id.main_recycler);
        adapter = new RestAdapter(RestListActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(RestListActivity.this,RecyclerView.VERTICAL,false);
        prg_main = findViewById(R.id.prd_main);
        txt_empty = findViewById(R.id.txt_empty);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);


        set_list();
    }

    private void set_list() {

        ApiClient.getInstance().getApi().
                Api_get_my_rests(new SharedPrfManager(RestListActivity.this).
                getId()).enqueue(new Callback<List<Rest>>() {
            @Override
            public void onResponse(Call<List<Rest>> call, Response<List<Rest>> response) {
                prg_main.setVisibility(View.GONE);
                if (response.isSuccessful() && !response.body().isEmpty()){
                    adapter.setItems(response.body());
                }
                else {
                    txt_empty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Rest>> call, Throwable t) {
                prg_main.setVisibility(View.GONE);
                txt_empty.setVisibility(View.VISIBLE);
                Log.d("dddd", "onFailure: " + t.getMessage());
                Toast.makeText(RestListActivity.this, "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();
            }
        });
//        items = new ArrayList<>();
//        for (int i = 0 ; i < 10 ; i++ ){
//            Rest item = new Rest();
//            item.setRest_status(i%2==0? "تایید شده":"رد شده");
//            item.setDate_rest("1400/11/"+i);
//            item.setDate_created("1400/10/"+(i+5));
//            item.setDec("لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. کتابهای زیادی در شصت و سه درصد گذشته، حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.");
//            items.add(item);
//        }

    }
}