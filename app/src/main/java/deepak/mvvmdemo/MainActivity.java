package deepak.mvvmdemo;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import deepak.mvvmdemo.adapters.UserAdapter;
import deepak.mvvmdemo.databinding.ActivityMainBinding;
import deepak.mvvmdemo.model.User;
import deepak.mvvmdemo.model.Users;
import deepak.mvvmdemo.viewmodels.UserListViewModal;
import deepak.mvvmdemo.viewmodels.UserViewModal;
import rx.subscriptions.CompositeSubscription;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private final CompositeSubscription subscriptions = new CompositeSubscription();

    private UserAdapter usersAdapter = new UserAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.listview.setVisibility(View.GONE);

        UserListViewModal userListViewModal = ViewModelProviders.of(this).get(UserListViewModal.class);

        subscriptions.add(userListViewModal.loadUsers().subscribe(this::onResponse, this::onFailure));

    }

    public void onUserClick(String user) {
        UserViewModal userViewModal = ViewModelProviders.of(this).get(UserViewModal.class);
        subscriptions.add(userViewModal.loadUser(user).subscribe(this::onResponseUser, this::onFailure));

    }

    private void onResponseUser(User user) {
        Intent i = new Intent(this, UserDetailActivity.class);
        i.putExtra("user", user);
        startActivity(i);

    }

    private void onFailure(Throwable throwable) {
    }

    private void onResponse(List<Users> users) {
        usersAdapter.setUserList(users);
        activityMainBinding.progressBar.setVisibility(View.GONE);

        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        activityMainBinding.listview.setLayoutManager(llm);
        activityMainBinding.listview.setAdapter(usersAdapter);
        activityMainBinding.listview.setVisibility(View.VISIBLE);
    }


}