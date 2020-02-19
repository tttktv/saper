package com.example.labasaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    Button btnstart;
    EditText editText;
    EditText editText2;
    EditText editText3;
    String rw,cl,mn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnstart=(Button)findViewById(R.id.buttonstart);
        btnstart.setOnClickListener(this);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);

        //columns=Integer.parseInt(editText2.getText().toString());
      //  mines=Integer.parseInt(editText3.getText().toString());


    }

    @Override
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.buttonstart:
                rw=editText.getText().toString();
                cl=editText2.getText().toString();
                mn=editText3.getText().toString();
                Intent intent=new Intent(this,ActivityTwo.class);
                intent.putExtra("rw",rw);
                intent.putExtra("cl",cl);
                intent.putExtra("mn",mn);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
