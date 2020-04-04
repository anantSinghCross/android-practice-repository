// This project is mainly a starter for learning Room Database for android
// Project is a 'to-do list' app to demonstrate use use of RoomDB

package com.crossbox.roomdbtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.StatusHints;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView rcView;
    private TasksAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        rcView = findViewById(R.id.recyclerView);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
        getTasks();
    }

    private void getTasks(){
        class GetTasks extends AsyncTask<Void,Void, List<Task>>{

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDataAccessObject()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(final List<Task> taskList) {
                super.onPostExecute(taskList);
                adapter = new TasksAdapter(taskList);
                rcView.setAdapter(adapter);
                adapter.setMyOnItemClickListener(new TasksAdapter.MyOnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Task task = taskList.get(position);
                        Intent intent = new Intent(MainActivity.this, UpdateTaskActivity.class);
                        intent.putExtra("task",task);
                        startActivity(intent);
//                        Toast.makeText(MainActivity.this, task.getTask(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        GetTasks getTasks = new GetTasks();
        getTasks.execute();
    }
}
