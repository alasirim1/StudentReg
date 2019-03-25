package com.example.studentreg.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.studentreg.R;
import com.example.studentreg.database.StudentDbHelper;
import com.example.studentreg.database.StudentModel;

public class UpdateStudentActivity extends AppCompatActivity {

    private static final String TAG = "UpdateStudentActivity";

    //Variables
    String studentPriority;
    StudentDbHelper studentDbHelper;
    StudentModel student;

    //View variables
    Button deleteButton, updateButton;
    EditText textName, textCourse;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        //Object of the database methods helper
        studentDbHelper = new StudentDbHelper(this);

        //init the data of the selected student
        student = new StudentModel(
                getIntent().getIntExtra("id", 0),
                getIntent().getStringExtra("name"),
                getIntent().getStringExtra("course"),
                getIntent().getStringExtra("priority"));

        //View references
        textName = findViewById(R.id.updateStudentName);
        textCourse = findViewById(R.id.updateStudentCourse);
        radioGroup = findViewById(R.id.radioGroup);

        //init the data
        initView();


        deleteButton = findViewById(R.id.deleteStudentButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Delete the student from the list.
                studentDbHelper.deleteStudent(student);

                finish();
            }
        });


        updateButton = findViewById(R.id.updateStudentButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Update the student data.
                //Setting the object with the new data
                StudentModel studentModel = new StudentModel(
                        student.getId(),
                        textName.getText().toString(),
                        textCourse.getText().toString(),
                        studentPriority
                );

                long x = studentDbHelper.updateStudent(studentModel);

                if (x > 0) {
                    Toast.makeText(UpdateStudentActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(UpdateStudentActivity.this, "Error", Toast.LENGTH_SHORT).show();


            }
        });
    }


    //Function to get the selected priority of the student
    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {

            case R.id.firstYear:
                if (checked)
                    studentPriority = "1st Year";
                break;

            case R.id.secondYear:
                if (checked)
                    studentPriority = "2nd Year";
                break;

            case R.id.thirdYear:
                if (checked)
                    studentPriority = "3rd Year";
                break;

            case R.id.fourthYear:
                if (checked)
                    studentPriority = "4th Year";
                break;

            case R.id.graduated:
                if (checked)
                    studentPriority = "Graduated";
                break;
        }
    }


    private void initView() {

        //Set the student name
        textName.setText(student.getName());

        //Set the student course
        textCourse.setText(student.getCourse());

        Log.i(TAG, "initView: " + student.getPriority());

        //Set the student priority
        if (student.getPriority().equals("1st Year")) {

            //Set the student priority
            radioGroup.check(R.id.firstYear);
            studentPriority = "1st Year";

        } else if (student.getPriority().equals("2nd Year")) {

            radioGroup.check(R.id.secondYear);
            studentPriority = "2nd Year";

        } else if (student.getPriority().equals("3rd Year")) {

            radioGroup.check(R.id.thirdYear);
            studentPriority = "3rd Year";

        } else if (student.getPriority().equals("4th Year")) {

            radioGroup.check(R.id.fourthYear);
            studentPriority = "4th Year";

        } else {

            radioGroup.check(R.id.graduated);
            studentPriority = "Graduated";

        }
    }
}
