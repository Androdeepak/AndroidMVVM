package deepak.mvvmdemo.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import deepak.mvvmdemo.MainActivity;
import deepak.mvvmdemo.api.RetrofitSingleton;
import deepak.mvvmdemo.model.Users;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserListViewModal extends ViewModel  {


    private String userName;
    private String type;
    private String id;
    private String avatarUrl;
    private String userUrl;


    public UserListViewModal() {

    }

    public String getUserName() {
        return userName;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUserUrl() {
        return userUrl;
    }


    @BindingAdapter("avatarUrl")
    public static void setImageUrl(ImageView imageView, String avatarUrl){
        Glide.with(imageView.getContext()).load(avatarUrl).into(imageView);
    }






    public void onItemClick(View v){
        ((MainActivity)v.getContext()).onUserClick(userUrl);
    }


    public void setUser(Users users) {
        this.userName = users.getUserName();
        this.type = users.getType();
        this.id = String.valueOf(users.getId());
        this.avatarUrl = users.getAvatarUrl();
        this.userUrl=users.getUrl();
    }




    public Observable<ArrayList<Users>> loadUsers() {
        return RetrofitSingleton.getInstance().provideClient()
                .getUsersObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
