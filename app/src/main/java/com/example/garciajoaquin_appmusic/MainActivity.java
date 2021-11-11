package com.example.garciajoaquin_appmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Share preferences
        SharedPreferences prefs = getSharedPreferences("SharedP", Context.MODE_PRIVATE);
        String email = prefs.getString("email","");



        //Login button created
        Button btnLogin = findViewById(R.id.btnSignIn);
        //Name and password fields created
        EditText txtUserName = findViewById(R.id.txtUserName);
        EditText txtPassword = findViewById(R.id.txtPassword);
        //The textview created
        TextView lblLoginResult = findViewById(R.id.lblLoginResult);
        lblLoginResult.setVisibility(View.INVISIBLE); //Textview invisible

        Intent loginIntent = new Intent(MainActivity.this, MenuActivity.class);
        CheckBox checkBox = findViewById(R.id.CbxRemenber);

        //Shared preferences
        txtUserName.setText(email);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("email", txtUserName.getText().toString());
                editor.putBoolean("login",true);
                editor.commit();

            }
        });



        //If we click on button login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Check if the name and password fields are correct
                if (txtUserName.getText().toString().equals("Alonso") && txtPassword.getText().toString().equals("1234")){

                    lblLoginResult.setText("Successful login");//Set message on the textview
                    Log.i("Test","Successful login");
                    startActivity(loginIntent);

                }else{
                    lblLoginResult.setText("Wrong name or password");//Set message on the textview
                    Log.i("Test","Wrong name or password");
                }
                //Text view turn in visible
                lblLoginResult.setVisibility(View.VISIBLE);
            }
        });

    }
}