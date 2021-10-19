package com.example.basicbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class allDetails extends AppCompatActivity {
    TextView displayall;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);
        displayall=findViewById(R.id.displayall);
        db=new DBHelper(this);
        Cursor res=db.getallData();
        StringBuffer buffer=new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Account_id : "+res.getString(0)+"\n");
            buffer.append("Name : "+res.getString(1)+"\n");
            buffer.append("Mail_id : "+res.getString(2)+"\n");
            buffer.append("Balance : "+res.getString(3)+"\n");
            buffer.append("Address : "+res.getString(4)+"\n");
            buffer.append("Phone no: "+res.getString(5)+"\n");
            buffer.append("---------------------------------------------\n");
            displayall.setText(buffer);
        }

    }
}