package deepak.mvvmdemo.viewmodels;


import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

import deepak.mvvmdemo.api.RetrofitSingleton;
import deepak.mvvmdemo.model.User;
import deepak.mvvmdemo.model.Users;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserViewModal extends ViewModel {


    public Observable<User> loadUser(String url) {
        return RetrofitSingleton.getInstance().provideClient()
                .getUserObservable(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

