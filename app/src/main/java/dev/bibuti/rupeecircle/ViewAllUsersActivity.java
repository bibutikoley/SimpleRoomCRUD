package dev.bibuti.rupeecircle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.Collections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dev.bibuti.rupeecircle.adapters.UserAdapter;
import dev.bibuti.rupeecircle.database.models.Users;
import dev.bibuti.rupeecircle.database.viewmodels.UserViewModel;

public class ViewAllUsersActivity extends AppCompatActivity implements UserAdapter.OnUserAdapterClickListener {

    @BindView(R.id.usersRV)
    RecyclerView usersRV;
    @BindView(R.id.emptyLayout)
    LinearLayout emptyLayout;
    @BindView(R.id.toolbarBTN)
    ImageButton toolbarBTN;

    UserViewModel userViewModel;

    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);
        ButterKnife.bind(this);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userAdapter = new UserAdapter(Collections.emptyList(), this);

        usersRV.setHasFixedSize(true);
        usersRV.setLayoutManager(new LinearLayoutManager(this));
        usersRV.setAdapter(userAdapter);

        userViewModel.getAllUsers().observe(this, users -> {
            userAdapter.updateUsers(users);
            if (users.size() == 0) {
                emptyLayout.setVisibility(View.VISIBLE);
                usersRV.setVisibility(View.INVISIBLE);
            } else {
                emptyLayout.setVisibility(View.INVISIBLE);
                usersRV.setVisibility(View.VISIBLE);
            }
        });

        toolbarBTN.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onDeleteViewClicked(Users users) {
        userViewModel.deleteUser(users);
    }

    @Override
    public void onNormalViewClicked(Users users) {
        Intent intent = new Intent(ViewAllUsersActivity.this, DataActivity.class);
        intent.putExtra("user_key", users);
        startActivity(intent);
    }
}
