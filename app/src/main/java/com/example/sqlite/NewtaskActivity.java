package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Adapter.TodoAdapter;

public class NewtaskActivity extends AppCompatActivity {
    EditText dateandtime;
    EditText ed1;
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_newtask );
        ed1 = findViewById( R.id.ed1 );
        dateandtime = findViewById( R.id.dateandtime );
            sql = openOrCreateDatabase( "mydatabase", MODE_PRIVATE, null );
            String q = "create table if not exists person(name varchar(20))";
            sql.execSQL( q );


        dateandtime.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateandTimedialog( dateandtime );
            }
        } );
    }

    private void showDateandTimedialog(EditText dateandtime) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set( Calendar.YEAR, year );
                calendar.set( Calendar.MONTH, month );
                calendar.set( Calendar.DAY_OF_MONTH, dayOfMonth );

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set( Calendar.HOUR_OF_DAY, hourOfDay );
                        calendar.set( Calendar.MINUTE, minute );

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yy-MM-dd HH:MM" );

                        dateandtime.setText( simpleDateFormat.format( calendar.getTime() ) );
                    }
                };
                new TimePickerDialog( NewtaskActivity.this, timeSetListener, calendar.get( Calendar.HOUR_OF_DAY ), calendar.get( Calendar.MINUTE ), false ).show();


            }
        };
        new DatePickerDialog( NewtaskActivity.this, dateSetListener, calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONTH ), calendar.get( Calendar.DAY_OF_MONTH ) ).show();
    }

    public void calender(View view) {
        showDateandTimedialog( dateandtime );

    }

    public void mic(View view) {
    }

    public void addlist(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );

        builder.setTitle( "New List" );
        builder.setMessage( "" );
        builder.setPositiveButton( "ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        } );
        builder.setNeutralButton( "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        } );
        builder.setIcon( R.drawable.list );

        builder.setCancelable( false );
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void donee(View view) {
        ContentValues values = new ContentValues();
        values.put( "name", ed1.getText().toString() );
        long n = sql.insert( "person", null, values );
        Toast.makeText( this, n + "records inserted.........", Toast.LENGTH_SHORT ).show();
    }
}
