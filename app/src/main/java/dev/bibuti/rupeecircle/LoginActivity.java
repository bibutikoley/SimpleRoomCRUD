package dev.bibuti.rupeecircle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import dev.bibuti.rupeecircle.database.models.Users;
import dev.bibuti.rupeecircle.database.viewmodels.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbarBTN)
    ImageButton toolbarBTN;
    @BindView(R.id.et_email_address)
    EditText etEmailAddress;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.sign_up)
    TextView signUp;

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        toolbarBTN.setOnClickListener(v -> {
            onBackPressed();
        });

        btnLogin.setOnClickListener(v -> {

            if (checkForm()) {
                Users users = userViewModel.login(etEmailAddress.getText().toString().trim(), etPassword.getText().toString().trim());
                if (users != null) {
                    Intent intent = new Intent(LoginActivity.this, ViewAllUsersActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Helper.showToast(this, "Incorrect Email or Password\n\nIf you don't have an account,\nPlease consider creating one.");
                }
            } else {
                // Form not valid...
                if ((etEmailAddress.getText().toString().trim().isEmpty()) && (etPassword.getText().toString().trim().isEmpty())) {
                    Helper.showToast(this, "All fields are mandatory..\nPlease fill in all the fields to continue.");
                } else if (etEmailAddress.getText().toString().trim().isEmpty()) {
                    Helper.showToast(this, "Please enter your email id.");
                } else if (etPassword.getText().toString().trim().isEmpty()) {
                    Helper.showToast(this, "Please enter you password.");
                }
            }

        });

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        });

    }


    private boolean checkForm() {
        String email = etEmailAddress.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        return Helper.validateForm(email, password);

    }
}
