package com.example.studentreg.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.studentreg.R;
import com.example.studentreg.database.StudentDbHelper;

public class AddStudentActivity extends AppCompatActivity {

    private static final String TAG = "AddStudentActivity";

    //Variables
    String studentPriority;
    StudentDbHelper studentDbHelper;
    long rowId = -1;

    //View variables
    Button submitButton;
    EditText nameEditText, courseEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //Object of the database methods helper
        studentDbHelper = new StudentDbHelper(this);

        //get the reference of the views
        nameEditText = findViewById(R.id.addStudentName);
        courseEditText = findViewById(R.id.addStudentCourse);

        submitButton = findViewById(R.id.addStudentButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Log.i(TAG, "onClick: "
                        + nameEditText.getText() +
                        courseEditText.getText() +
                        studentPriority
                );*/

                //Call the function to insert the student data
                long rowId = studentDbHelper.insertToStudentDatabase(
                        nameEditText.getText().toString(),
                        courseEditText.getText().toString(),
                        studentPriority
                );

                //if the returned row != -1 that means it is inserted.
                if (rowId > 0) {
                    Toast.makeText(AddStudentActivity.this, "Inserted", Toast.LENGTH_SHORT).show();

                    finish();
                } else
                    Toast.makeText(AddStudentActivity.this, "Error", Toast.LENGTH_SHORT).show();

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
}
