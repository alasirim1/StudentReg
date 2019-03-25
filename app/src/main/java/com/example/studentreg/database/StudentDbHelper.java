package com.example.studentreg.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentDbHelper extends SQLiteOpenHelper {

    //Variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Students.db";

    //Query to create the table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentListTableInfo.StudentTable.TABLE_NAME + " (" +
                    StudentListTableInfo.StudentTable._ID + " INTEGER PRIMARY KEY," +
                    StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_NAME + " TEXT," +
                    StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_PRIORITY + " TEXT," +
                    StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_COURSE + " TEXT)";

    //Query to delete [Drop all data in the database].
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentListTableInfo.StudentTable.TABLE_NAME;

    //Constructor
    public StudentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //To create the table
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Upgrading the table
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


    /*
     ** All database methods
     */
    public long insertToStudentDatabase(String name, String course, String priority) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_NAME, name);
        values.put(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_COURSE, course);
        values.put(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_PRIORITY, priority);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(StudentListTableInfo.StudentTable.TABLE_NAME, null, values);

        return newRowId;
    }

    public long updateStudent(StudentModel studentModel) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_NAME, studentModel.getName());
        values.put(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_COURSE, studentModel.getCourse());
        values.put(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_PRIORITY, studentModel.getPriority());

        long newRowUpdateID = db.update(StudentListTableInfo.StudentTable.TABLE_NAME
                , values
                , StudentListTableInfo.StudentTable._ID + " =?",
                new String[]{String.valueOf(studentModel.getId())});

        return newRowUpdateID;
    }

//    public StudentModel getStudent(long id) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        //Selection Query
//        Cursor cursor = db.query(StudentListTableInfo.StudentTable.TABLE_NAME,
//
//                new String[]{StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_NAME,
//                        StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_COURSE,
//                        StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_PRIORITY},
//
//                StudentListTableInfo.StudentTable._ID + "=?",
//
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        //Get the data from the first
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        //Make a student object to show
//        StudentModel studentModel = new StudentModel(
//                cursor.getInt(cursor.getColumnIndex(StudentListTableInfo.StudentTable._ID)),
//                cursor.getString(cursor.getColumnIndex(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_NAME)),
//                cursor.getString(cursor.getColumnIndex(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_COURSE)),
//                cursor.getString(cursor.getColumnIndex(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_PRIORITY)));
//
//        cursor.close();
//
//        return studentModel;
//    }


    public List<StudentModel> getAllStudents() {

        List<StudentModel> list = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + StudentListTableInfo.StudentTable.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {

                StudentModel student = new StudentModel();
                student.setId(cursor.getInt(cursor.getColumnIndex(StudentListTableInfo.StudentTable._ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_NAME)));
                student.setCourse(cursor.getString(cursor.getColumnIndex(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_COURSE)));
                student.setPriority(cursor.getString(cursor.getColumnIndex(StudentListTableInfo.StudentTable.COLUMN_NAME_STUDENT_PRIORITY)));

                //Add every single student in the list to use.
                list.add(student);

            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        return list;
    }


    public void deleteStudent(StudentModel studentModel) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        //Delete selected student
        db.delete(StudentListTableInfo.StudentTable.TABLE_NAME,
                StudentListTableInfo.StudentTable._ID + "=?",
                new String[]{String.valueOf(studentModel.getId())});

        db.close();
    }
}
