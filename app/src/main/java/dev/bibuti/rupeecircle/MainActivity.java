package dev.bibuti.rupeecircle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Trying something new.
        //Another line added.
        Log.d(TAG, "onCreate: Just to test workflow");

        Button btn_login = findViewById(R.id.btn_login);
        Button btn_create_new_account = findViewById(R.id.btn_create_new_account);

        btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        btn_create_new_account.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });

    }
}
