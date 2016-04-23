package com.example.aaron.islandharvestroute;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mCreateAccountButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEmailEditText = (EditText) findViewById(R.id.emailRegEditText);
        mUsernameEditText = (EditText) findViewById(R.id.usernameRegEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordRegEditText);
        mCreateAccountButton = (Button) findViewById(R.id.registerAccountButton);

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReg(v);
            }
        });
    }


    public void onReg(View view) {
        String email = mEmailEditText.getText().toString().trim();
        String username = mUsernameEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();

        Toast.makeText(this, "Data grabbed", Toast.LENGTH_SHORT).show();

        String method = "register";
        BackgroundTask bgTask = new BackgroundTask(this);
        bgTask.execute(method, email, username, password);
        finish();

        Toast.makeText(this, "Method finished", Toast.LENGTH_SHORT).show();
    }

}
