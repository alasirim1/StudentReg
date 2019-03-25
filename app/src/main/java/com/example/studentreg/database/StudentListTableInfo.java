package com.example.studentreg.database;

import android.provider.BaseColumns;

//This is a class that defines the table rows.
public class StudentListTableInfo {

    private StudentListTableInfo() {}

    /*We use this class to define the student table
    * Also make it static to use anywhere in the SQLite helper class.
    * */
    public static class StudentTable implements BaseColumns {

        //Here the table columns
        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME_STUDENT_NAME = "name";
        public static final String COLUMN_NAME_STUDENT_COURSE = "course";
        public static final String COLUMN_NAME_STUDENT_PRIORITY = "priority";

    }
}
