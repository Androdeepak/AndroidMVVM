package deepak.mvvmdemo.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import deepak.mvvmdemo.R;
import deepak.mvvmdemo.databinding.ItemUserBinding;
import deepak.mvvmdemo.model.Users;
import deepak.mvvmdemo.viewmodels.UserListViewModal;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterViewHolder> {
    private List<Users> userList;

    public UserAdapter() {this.userList = Collections.emptyList();}

    @NonNull
    @Override
    public UserAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)
                parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ItemUserBinding itemUserBinding=DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false);
      return new UserAdapterViewHolder(itemUserBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterViewHolder holder, int position) {
        holder.bindUser(userList.get(position));

    }

    @Override
    public int getItemCount() {
        return  userList.size();
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding mItemUserBinding;

        public UserAdapterViewHolder(ItemUserBinding itemUserBinding) {
          super(itemUserBinding.itemPeople);
           this.mItemUserBinding = itemUserBinding;
        }

        void bindUser(Users user){
           if(mItemUserBinding.getUserViewModel() == null){
               UserListViewModal userViewModel = new UserListViewModal();
               userViewModel.setUser(user);
               mItemUserBinding.setUserViewModel(userViewModel);
          }else {
              mItemUserBinding.getUserViewModel().setUser(user);
           }
        }
    }
}

