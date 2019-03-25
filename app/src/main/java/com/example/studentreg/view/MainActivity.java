package com.example.studentreg.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.studentreg.R;
import com.example.studentreg.adapter.RecyclerAdapter;
import com.example.studentreg.database.StudentDbHelper;
import com.example.studentreg.database.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //Variables
    StudentDbHelper studentDbHelper;
    List<StudentModel> studentList;

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    //View variables
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDbHelper = new StudentDbHelper(this);

        //init the recycler view
        recyclerView = findViewById(R.id.studentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Get student data
        gettingAllStudentsData();

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddStudentActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        gettingAllStudentsData();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //recyclerAdapter.notifyDataSetChanged();
        gettingAllStudentsData();
    }

    private void gettingAllStudentsData() {

        //Setting up the recyclerView
        recyclerAdapter = new RecyclerAdapter(this, studentList);
        recyclerView.setAdapter(recyclerAdapter);

        studentList = new ArrayList<>();

        //Getting student data.
        studentList = studentDbHelper.getAllStudents();

        Log.i(TAG, "gettingAllStudentsData: " + studentList.size());
    }

}
