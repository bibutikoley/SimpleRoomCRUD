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

public class CreateAccountActivity extends AppCompatActivity {

    @BindView(R.id.toolbarBTN)
    ImageButton toolbarBTN;
    @BindView(R.id.et_full_name)
    EditText etFullName;
    @BindView(R.id.et_email_address)
    EditText etEmailAddress;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.login)
    TextView login;

    UserViewModel userViewModel;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        toolbarBTN.setOnClickListener(v -> {
            onBackPressed();
        });

        btnSignup.setOnClickListener(v -> {

            if (checkForm()) {

                userViewModel.insertUser(users);
                Helper.log("Valid");
                Intent intent = new Intent(CreateAccountActivity.this, ViewAllUsersActivity.class);
                startActivity(intent);
                finish();

            } else {
                // Form not valid...
                Helper.log("In Valid Form");
            }

        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }


    private boolean checkForm() {

        String fullName = etFullName.getText().toString().trim();
        String email = etEmailAddress.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();

        if (Helper.validateForm(fullName, email, password, confirmPassword, mobile)) {
            users = new Users(etFullName.getText().toString().trim(), etEmailAddress.getText().toString().trim(), etPassword.getText().toString().trim(), etMobile.getText().toString().trim());
            return true;
        } else {
            if (fullName.isEmpty() && email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty() && mobile.isEmpty()) {
                Helper.showToast(this, "All fields are mandatory..\nPlease fill in all the fields to continue.");
            } else if (fullName.isEmpty()) {
                Helper.showToast(this, "Name cannot be empty.");
            } else if (email.isEmpty()) {
                Helper.showToast(this, "Email cannot be empty.");
            } else if (password.isEmpty()) {
                Helper.showToast(this, "Password cannot be empty.");
            } else if (!password.equals(confirmPassword)) {
                Helper.showToast(this, "Passwords don't match.");
            } else if (mobile.length() != 10) {
                Helper.showToast(this, "Make sure you have entered valid mobile number");
            }
            return false;
        }

    }
}
