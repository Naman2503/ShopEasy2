package com.example.naman.shopeasy;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginNow extends AppCompatActivity {


    EditText user, pass;
    Button login, not_reg;
    DatabaseHandler db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_now);


        user = (EditText) findViewById(R.id.eduser);
        pass = (EditText) findViewById(R.id.edpass);
        login = (Button) findViewById(R.id.login);
        not_reg = (Button) findViewById(R.id.not_reg);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                db = new DatabaseHandler(LoginNow.this, null, null, 2);
                String username = user.getText().toString();
                String password = pass.getText().toString();

                String StoredPassword = db.getregister(username);
                if (password.equals(StoredPassword)) {

                    Toast.makeText(getApplicationContext(),"Login Successfull", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginNow.this, MainActivity.class);
                    startActivity(i);

                } else {
                    Toast.makeText(getApplicationContext(), "Username/Password Incorrect", Toast.LENGTH_LONG).show();
                    user.setText("");
                    pass.setText("");
                }


            }
        });

        not_reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });


    }

    /* @Override
                    public boolean onCreateOptionsMenu(Menu menu) {
MenuInflater inflater = getMenuInflater();
inflater.inflate(R.menu.action_settings, menu);
            return super.onCreateOptionsMenu(menu);
           //
     }
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_settings:
                // search action
                Intent i = new Intent(LoginNow.this, RegisterActivity.class);
                startActivity(i);
                return true;
        }
        return false;

    }
}


