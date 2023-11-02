package com.example.logbook3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        // Retrieve data from the database and populate the personList
        List<User> userList = dbHelper.getDetails();

        // Create an adapter and set it on the RecyclerView
        UserAdapter adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);
    }
}