package edu.ualr.cpsc4399.bjparikh.studentattendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etCpassword = (EditText) findViewById(R.id.etCpassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        Button btRegister = (Button) findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass1 = etPassword.getText().toString();
                String pass2 = etCpassword.getText().toString();
                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();

                if(name.matches("") || username.matches("") || email.matches("") ||
                        pass1.matches("") || pass2.matches("")){
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {

                    if(!pass1.equals(pass2)){
                        //popup msg
                        Toast.makeText(RegisterActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    } else {
                        if(databaseHelper.unameExists(username)){
                            Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            Credentials credentials = new Credentials();
                            credentials.setName(name);
                            credentials.setPassw(pass1);
                            credentials.setEmail(email);
                            credentials.setUname(username);

                            databaseHelper.insertCredentials(credentials);
                            Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            RegisterActivity.this.startActivity(loginIntent);
                        }

                    }
                }


            }
        });

    }
}
