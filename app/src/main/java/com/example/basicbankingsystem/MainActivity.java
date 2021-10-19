package com.example.basicbankingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView showtext;
    Spinner spinner;
    Button close;
    PopupWindow popupWindow;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> users=new ArrayList<String>();
        users.add("");
        users.add("user1");
        users.add("user2");
        users.add("user3");
        users.add("user4");
        users.add("user5");
        users.add("user6");
        users.add("user7");
        users.add("user8");
        users.add("user9");
        users.add("user10");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void contactus(View view) {

        LayoutInflater inflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupview=inflater.inflate(R.layout.popup,null);
        showtext=popupview.findViewById(R.id.showtext);
        close=popupview.findViewById(R.id.close);
        showtext.setText("Contact no : "+ "+91-489785651" +"\n");
        popupWindow=new PopupWindow(popupview,500,300);
        popupWindow.showAtLocation(popupview, Gravity.CENTER,0,0);
        popupWindow.setTouchable(true);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


    }
    public void locations(View view) {

        LayoutInflater inflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupview=inflater.inflate(R.layout.popup,null);
        showtext=popupview.findViewById(R.id.showtext);
        close=popupview.findViewById(R.id.close);
        showtext.setText("Locate us at " + " London SW1A 1AA, UK");
        popupWindow=new PopupWindow(popupview, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(popupview, Gravity.CENTER,0,0);
        popupWindow.setTouchable(true);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();
            if(item.equals(""))
            {
                return;
            }
            else
            {
                Toast.makeText(this, ""+item, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,com.example.basicbankingsystem.transfer.class);
                intent.putExtra("item",item);
                startActivity(intent);
            }
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}