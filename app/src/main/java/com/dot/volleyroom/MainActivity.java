package com.dot.volleyroom;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    private Spinner spinner;

    MyAppDatabase myAppDatabase;

    private ArrayList<String> userNmae;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNmae = new ArrayList<>();

        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"userdb").allowMainThreadQueries().build();

        putData();
    }

    private void putData(){
        List<User> users =myAppDatabase.myDao().getUsers();
        for(int i = 0; i < users.size(); i++){
            userNmae.add(users.get(i).getName());
        }
        spinner.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, userNmae));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}