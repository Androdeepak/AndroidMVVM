package deepak.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import deepak.mvvmdemo.databinding.ActivityUserDetailBinding;


public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityUserDetailBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail);
        viewDataBinding.setUser(getIntent().getParcelableExtra("user"));
    }
}
