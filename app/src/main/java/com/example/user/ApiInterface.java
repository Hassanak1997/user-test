package com.example.user;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<StatusModel> Api_login(@Field("action") String action,
                                @Field("phone") String phone,
                                @Field("username") String username,
                                @Field("password") String password,
                                @Field("fullname") String fullname
    );

    @FormUrlEncoded
    @POST("request_rest.php")
    Call<StatusModel> Api_save_rest(@Field("description") String description,
                                    @Field("request_time") String request_time,
                                    @Field("rest_time") String rest_time,
                                    @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("show_my_rest.php")
    Call<List<Rest>> Api_get_my_rests(@Field("id") String id
    );


//    @FormUrlEncoded
//    @POST("get_city")
//    Call<List<Province>> Api_getCity(
//            @Field("mikhay") String mikhay
//    );

}
