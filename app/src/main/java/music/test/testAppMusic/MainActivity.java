package music.test.testAppMusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
        String password = prefs.getString("password","");

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


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("email", txtUserName.getText().toString());
                editor.putString("password",txtPassword.getText().toString());
                editor.commit();
            }
        });
        txtUserName.setText(email); //<--Va antes
        txtPassword.setText(password);

        //If we click on button login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Check if the name and password fields are correct
                if (txtUserName.getText().toString().equals("test") && txtPassword.getText().toString().equals("1234")){

                    lblLoginResult.setText("Successful login");//Set message on the textview
                    startActivity(loginIntent);

                }else{
                    lblLoginResult.setText("Wrong name or password");//Set message on the textview
                }
                //Text view turn in visible
                lblLoginResult.setVisibility(View.VISIBLE);
            }
        });

    }
}