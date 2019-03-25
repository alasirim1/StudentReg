package com.example.studentreg.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.studentreg.R;
import com.example.studentreg.database.StudentDbHelper;
import com.example.studentreg.database.StudentModel;
import com.example.studentreg.view.MainActivity;
import com.example.studentreg.view.UpdateStudentActivity;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context mContext;
    List<StudentModel> studentList;

    public RecyclerAdapter(Context mContext, List<StudentModel> studentList) {
        this.mContext = mContext;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //Getting the design for each student
        View view = LayoutInflater.from(mContext).inflate(R.layout.student_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //Get every student i = position
        final StudentModel student = studentList.get(i);

        //init the view for every student
        viewHolder.name.setText(student.getName());
        viewHolder.course.setText(student.getCourse());
        viewHolder.priority.setText(student.getPriority());

        //set the click event of every student data
        //to update or delete
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, UpdateStudentActivity.class);

                //Set this flag because we open from an adapter
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                //send data to another activity to update or delete
                intent.putExtra("id", student.getId());
                intent.putExtra("name", student.getName());
                intent.putExtra("course", student.getCourse());
                intent.putExtra("priority", student.getPriority());

                //Start the update screen
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView course;
        TextView priority;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Make a reference for the views
            name = itemView.findViewById(R.id.name);
            course = itemView.findViewById(R.id.course);
            priority = itemView.findViewById(R.id.priority);
            linearLayout = itemView.findViewById(R.id.studentLayout);
        }
    }
}
