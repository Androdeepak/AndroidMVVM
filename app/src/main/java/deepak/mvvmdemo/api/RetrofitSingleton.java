package deepak.mvvmdemo.api;

import java.util.ArrayList;

import deepak.mvvmdemo.model.User;
import deepak.mvvmdemo.model.Users;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public class RetrofitSingleton {

    private static volatile RetrofitSingleton instance = null;
    private UserApi client;

    private RetrofitSingleton() {
        Retrofit retrofit =
                new Retrofit.Builder().baseUrl("https://api.github.com")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create()).build();
        client = retrofit.create(UserApi.class);
    }

    public static RetrofitSingleton getInstance() {
        if (instance == null) {
            synchronized(RetrofitSingleton.class) {
                if (instance == null) {
                    instance = new RetrofitSingleton();
                }
            }
        }
        return instance;
    }

    public UserApi provideClient() {
        return client;
    }

    public interface UserApi {
        @SuppressWarnings("unused")
        @GET("/users")
        Call<ArrayList<Users>> getUsers();
        
        @GET("/users")
        Observable<ArrayList<Users>> getUsersObservable();


        @GET
        Observable<User> getUserObservable(@Url String url);



    }
}
