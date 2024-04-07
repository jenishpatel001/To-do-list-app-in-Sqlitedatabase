package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.TodoAdapter;
import Model.Todolist;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    SQLiteDatabase sql;
    List<Todolist> todolists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getSupportActionBar().hide();


        sql = openOrCreateDatabase( "mydatabase", MODE_PRIVATE, null );
        String q = "create table if not exists person(name varchar(20))";
        recycler = findViewById( R.id.recycler );
        recycler.setLayoutManager( new LinearLayoutManager( this ) );
        sql.execSQL( q );
        displayall();



    }

    public void plus(View view) {
        Intent intent = new Intent( this, NewtaskActivity.class );
        startActivity( intent );
    }

    public void displayall() {
        Cursor cursor = sql.query( "person", null, null, null, null, null, null );
        TodoAdapter adapter = new TodoAdapter( cursor );
        recycler.setAdapter( adapter );
    }
}