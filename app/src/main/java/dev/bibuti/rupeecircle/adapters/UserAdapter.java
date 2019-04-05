package dev.bibuti.rupeecircle.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dev.bibuti.rupeecircle.Helper;
import dev.bibuti.rupeecircle.R;
import dev.bibuti.rupeecircle.database.models.Users;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Users> usersList;
    private OnUserAdapterClickListener onUserAdapterClickListener;

    public UserAdapter(List<Users> usersList, OnUserAdapterClickListener onUserAdapterClickListener) {
        this.usersList = usersList;
        this.onUserAdapterClickListener = onUserAdapterClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_user_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        Users singleUser = usersList.get(position);

        holder.singleUserNameTV.setText(singleUser.getName());

        holder.singleUserEmailTV.setText(singleUser.getEmail());

        holder.singleUserNameTV.setOnClickListener(v -> {
            if (onUserAdapterClickListener != null) {
                onUserAdapterClickListener.onNormalViewClicked(singleUser);
            } else {
                //Listener on Attached..
                Helper.log("Helper = Listener not attached");
            }
        });

        holder.singleUserIV.setOnClickListener(v -> {
            if (onUserAdapterClickListener != null) {
                onUserAdapterClickListener.onNormalViewClicked(singleUser);
            } else {
                //Listener on Attached..
                Helper.log("Helper = Listener not attached");
            }
        });

        holder.singleUserEmailTV.setOnClickListener(v -> {
            if (onUserAdapterClickListener != null) {
                onUserAdapterClickListener.onNormalViewClicked(singleUser);
            } else {
                //Listener on Attached..
                Helper.log("Helper = Listener not attached");
            }
        });

        holder.singleUserDeleteTV.setOnClickListener(v -> {
            if (onUserAdapterClickListener != null) {
                onUserAdapterClickListener.onDeleteViewClicked(singleUser);
            } else {
                //Listener on Attached..
                Helper.log("Helper = Listener not attached");
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList == null ? 0 : usersList.size();
    }

    public void updateUsers(List<Users> users) {
        usersList = users;
        notifyDataSetChanged();
    }

    public interface OnUserAdapterClickListener {
        void onDeleteViewClicked(Users users);

        void onNormalViewClicked(Users users);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.singleUserIV)
        ImageView singleUserIV;
        @BindView(R.id.singleUserNameTV)
        TextView singleUserNameTV;
        @BindView(R.id.singleUserEmailTV)
        TextView singleUserEmailTV;
        @BindView(R.id.singleUserDeleteTV)
        TextView singleUserDeleteTV;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
