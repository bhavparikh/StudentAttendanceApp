package edu.ualr.cpsc4399.bjparikh.studentattendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        Button btLogin = (Button) findViewById(R.id.etLogin);
        TextView tvRegisterHere = (TextView) findViewById(R.id.tvRegisterHere);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                String password1 = databaseHelper.searchPass(username);
                if(password.equals(password1)){
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    Toast temp = Toast.makeText(MainActivity.this, "Username and Password don't match",
                            Toast.LENGTH_SHORT);
                    temp.show();
                    etUsername.setText(null);
                    etPassword.setText(null);
                }
            }
        });

        tvRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });
    }
}
