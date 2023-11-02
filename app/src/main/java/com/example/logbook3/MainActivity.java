package com.example.logbook3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button savebtn = (Button) findViewById(R.id.btn_save);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails();
            }
        });

        Button displaybtn = (Button) findViewById(R.id.btn_display);
        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(i);
            }
        });
    }

    private void saveDetails() {
        if (!validateInputs()) {
            return;
        }
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        EditText nameTxt = findViewById(R.id.edt_name);
        EditText dobTxt = findViewById(R.id.edt_dob);
        EditText emailTxt = findViewById(R.id.edt_email);
        RadioGroup avatarGroup = findViewById(R.id.radioGroup);

        int selectedAvatar = avatarGroup.getCheckedRadioButtonId();
        int avatar;

        if (selectedAvatar == -1) {
            avatar = 0;
        } else {
            avatar = Integer.parseInt(findViewById(selectedAvatar).getTag().toString());
        }
        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();

        long personId = dbHelper.insertDetails(name, dob, email, avatar);
        Toast.makeText(this, "Person has been created with id: " + personId, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, DetailsActivity.class);
        startActivity(i);

    }

    private boolean validateInputs() {
        EditText nameTxt = findViewById(R.id.edt_name);
        EditText dobTxt = findViewById(R.id.edt_dob);
        EditText emailTxt = findViewById(R.id.edt_email);

        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();

        if (name.isEmpty()) {
            nameTxt.setError("Name is required");
            nameTxt.requestFocus();
            return false;
        }

        if (dob.isEmpty()) {
            dobTxt.setError("Date of Birth is required");
            dobTxt.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            emailTxt.setError("Email is required");
            emailTxt.requestFocus();
            return false;
        }

        return true;
    }
}