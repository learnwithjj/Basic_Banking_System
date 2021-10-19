package com.example.basicbankingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class transfer extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView welcometext,cardview;
    DBHelper db;
    EditText enteramount;
    Spinner spinner1;
    private Cursor res1;
    private int valuesender;
    PopupWindow popupWindow;
    Button closep,pay,showall;
     private MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        welcometext = findViewById(R.id.welcometext);
        enteramount=findViewById(R.id.enteramount);
        cardview=findViewById(R.id.cardview);
        pay=findViewById(R.id.pay);
        player=MediaPlayer.create(this,R.raw.paymentsound);
        showall=findViewById(R.id.showall);


        String item1 = getIntent().getStringExtra("item");
        welcometext.setText("Welcome "+item1+"!!");
        db = new DBHelper(this);
        db.insertData( 121,"user1", "user1@gmail.com", 100000.10,"4789652310"," B 27 1st Floor, Madhuban Delhi Delhi");
        db.insertData( 122,"user2", "user2@gmail.com", 200000.2,"5896241307", "Mambalam High Road, T NagarChennai Tamil Nadu");
        db.insertData(123,"user3", "user3@gmail.com", 300000.30,"7412589630","Dudheshwar Road, Opp Rustom Mill, Shahibaug Ahmedabad Gujarat");
        db.insertData( 124,"user4", "user4@gmail.com", 400000.40,"8415203697","1007, Faiz Road, Karol Bagh Delhi Delhi");
        db.insertData( 125,"user5", "user5@gmail.com", 500000.50,"8549621738","49/a, Near Fellowship School, Tanker Villa, August Kranti Marg, August Kranti Mumbai Maharashtra");
        db.insertData( 126,"user6", "user6@gmail.com", 600000.60,"4815263978","D-1, Naaz Building, D Bhadkamkar Rd, Girgaon Mumbai   Maharashtra");
        db.insertData( 127,"user7", "user7@gmail.com", 700000.70,"1526394875","49/a, Near Fellowship School, Tanker Villa, August Kranti Marg, August Kranti Mumbai  Maharashtra");
        db.insertData( 128,"user8", "user8@gmail.com", 800000.80,"0215264859","D-1, Naaz Building, D Bhadkamkar Rd, Girgaon Mumbai Maharashtra");
        db.insertData( 129,"user9", "user9@gmail.com", 900000.70,"7845122356","Ambika Nagar No 2, Road No 29, Nr Panchmeshwar Temple, Thane (w) Mumbai Maharashtra");
        db.insertData( 120,"user10", "user10@gmail.com", 1000000.70,"1548926537","  Sumeru Cplx Opp Sadhna School, Paldi Ahmedabad Gujara");
        valuesender=search(item1);
        display(valuesender);
        spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);
        List<String> users = new ArrayList<String>();
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(transfer.this, android.R.layout.simple_spinner_dropdown_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        res1=db.getBalance(valuesender);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enteramount.getText().toString().isEmpty())
                {
                    return;
                }
                String amt=enteramount.getText().toString();
                float amount = Float.parseFloat(enteramount.getText().toString());
                String item2= spinner1.getSelectedItem().toString();
                int valuereceiever=search(item2);
                Cursor res2=db.getBalance(valuereceiever);
                float balancereceiever= Float.parseFloat(res2.getString(res2.getColumnIndex("balance")));
                float balancesender=Float.parseFloat(res1.getString(res1.getColumnIndex("balance")));
                float remainingsender=balancesender-amount;
                float remainingreceiver=balancereceiever+amount;
                db.updateBalance(valuesender,remainingsender);
                db.updateBalance(valuereceiever,remainingreceiver);

                Toast.makeText(transfer.this, "Money sent to "+ item2, Toast.LENGTH_SHORT).show();
                paid();
                display(valuesender);
            }
        });
        showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(transfer.this,com.example.basicbankingsystem.allDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public int search(String item)
    {
        int value=0;
        switch(item)
        {
            case "user1":value=121;break;
            case "user2":value=122;break;
            case "user3":value=123;break;
            case "user4":value=124;break;
            case "user5":value=125;break;
            case "user6":value=126;break;
            case "user7":value=127;break;
            case "user8":value=128;break;
            case "user9":value=129;break;
            case "user10":value=130;break;
        }
        return value;
    }

    public void paid()
    {
                player.start();
                LayoutInflater inflater =(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupview=inflater.inflate(R.layout.popup1,null);
                closep=popupview.findViewById(R.id.closep);
                popupWindow=new PopupWindow(popupview, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(popupview, Gravity.CENTER,0,0);
                popupWindow.setTouchable(true);
                closep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        player.stop();
                    }
                });

    }
    public void display(int accountid)
    {
       Cursor res=db.getData(accountid);
       String name=res.getString(1);
       String mail=res.getString(2);
       float balance=res.getFloat(3);
       String phoneno=res.getString(4);
       String address=res.getString(5);
       cardview.setText("Account id : " + accountid + "\nName : " + name + "\nMail id : " + mail + "\nBalance : " + balance+"\nPhone no : "+phoneno+"\nAddress : "+address);
    }

    public void updatetransaction()
    {


    }
}