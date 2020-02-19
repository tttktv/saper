package com.example.labasaper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class ActivityTwo extends Activity implements View.OnClickListener {
    int rows,columns,mines;
    Button[][] btn;
    String [][] mas;

    TableLayout tableLayout;
    TextView textView;
    Button restartbtn;
    Button ngbutton;
    int win=0;
   /* Intent intent=getIntent();
   int rows=Integer.parseInt(intent.getStringExtra("rw"));
   int columns=Integer.parseInt(intent.getStringExtra("cl"));
   int mines=Integer.parseInt(intent.getStringExtra("mn"));
    Button[][] btn = new Button[rows][columns];
    String [][] mas=new String [rows][columns];
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Intent intent=getIntent();
        rows=Integer.parseInt(intent.getStringExtra("rw"));
        columns=Integer.parseInt(intent.getStringExtra("cl"));
        mines=Integer.parseInt(intent.getStringExtra("mn"));
        if(rows<5){
            rows=5;
        }
        if(columns<5){
            columns=5;
        }
        if(columns<5){
            columns=5;
        }
        if(mines==0){
            mines=1;
        }
        if(mines>rows*columns){
            double mn;
            mn=(rows*columns)/2;
            mines=(int)mn;
        }
        btn = new Button[rows][columns];
        mas=new String [rows][columns];


        int i = 0,j=0,n=0;
        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        textView=(TextView) findViewById(R.id.textView);
        restartbtn=(Button) findViewById(R.id.restartbtn);
        restartbtn.setOnClickListener(this);
        ngbutton=(Button) findViewById(R.id.newgamebtn);
        ngbutton.setOnClickListener(this);

        gen();


        //Button[][] btn = new Button[rows][columns];
        for (i = 0; i < rows; ++i) {
            TableRow tableRow = new TableRow(this);
            for (j=0;j<columns;++j) {
                btn[i][j] = new Button(this);
                tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.MATCH_PARENT,1));
                btn[i][j].setText(" ");
                btn[i][j].setId(n++);
                btn[i][j].setOnClickListener(this);
                btn[i][j].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1));

                tableRow.addView(btn[i][j]);
            }
            tableLayout.addView(tableRow);
        }

      //  tw.setText("rows="+Integer.toString(rows)+"columns="+Integer.toString(columns)+"mines="+Integer.toString(mines));//////////////

    }


    @Override
    public  void onClick(View v){

        int d=v.getId();
        for(int k=0;k<rows;k++)
        {
            for(int t=0;t<columns;t++) {
               /* if (d == btn[k][t].getId()) {
                    btn[k][t].setClickable(false);
                    textView.setText(Integer.toString(d) + " " + Integer.toString(k) + " " + Integer.toString(t));
                }*/
                if(d == btn[k][t].getId()&&mas[k][t]=="!"){
                    textView.setText(Integer.toString(d) + " " + Integer.toString(k) + " " + Integer.toString(t)+"win="+Integer.toString(win));
                    btn[k][t].setText("!");
                    badgame();
                }
                if(d == btn[k][t].getId()&&mas[k][t]!=""&&mas[k][t]!="!"){
                    String st=mas[k][t];
                    btn[k][t].setText(st);
                    win++;
                    btn[k][t].setClickable(false);
                    // btn[k][t].setBackgroundColor(Color.GRAY);//?????????????
                    textView.setText(Integer.toString(d) + " " + Integer.toString(k) + " " + Integer.toString(t)+"win="+Integer.toString(win));
                }
                if(d == btn[k][t].getId()&&mas[k][t]==""){
                    win++;
                    btn[k][t].setText("no");
                    btn[k][t].setClickable(false);
                    //btn[k][t].setBackgroundColor(Color.BLACK);
                    textView.setText(Integer.toString(d) + " " + Integer.toString(k) + " " + Integer.toString(t)+"win="+Integer.toString(win));
                }
                if(d == restartbtn.getId()){
                    for(int i=0;i<rows;i++){
                        for(int j=0;j<columns;j++){
                            textView.setText("");
                            btn[i][j].setText("");
                            btn[i][j].setClickable(true);
                            //mas[i][j]="";
                        }
                    }
                    gen();
                    win=0;
                }

                if (d==ngbutton.getId()){
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                }

                if(win==rows*columns-mines){
                    goodgame();
                }
            }
        }
    }

    public void goodgame(){
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                btn[i][j].setClickable(false);
            }
        }
        win=0;
        textView.setText("You won!");
    }


    public void badgame(){
        for(int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                btn[i][j].setClickable(false);
            }
        }
        win=0;
        textView.setText("You lose!");
    }

    public void gen(){
        Random rand = new Random();
        int [][] m=new int [rows][columns];
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++) {
                mas[i][j] ="";
            }
        }
        for (int i=0;i<mines;i++){
            int r=rand.nextInt(rows);
            int c=rand.nextInt(columns);
            mas[r][c]="!";
            textView.append("rows="+Integer.toString(r)+"columns="+Integer.toString(c));
        }
        for (int q=0;q<rows;q++){//слева
            for (int w=1;w<columns;w++){
                if (mas[q][w]!="!"&&mas[q][w-1]=="!"){
                    m[q][w]++;
                    mas[q][w]=m[q][w]+"";
                }
            }
        }
        for (int q=0;q<rows;q++){//справа
            for (int w=0;w<columns-1;w++){
                if (mas[q][w]!="!"&&mas[q][w+1]=="!"){
                    m[q][w]++;
                    mas[q][w]=m[q][w]+"";
                }
            }
        }

        for (int q=1;q<rows;q++){//сверху
            for (int w=0;w<columns;w++){
                if (mas[q][w]!="!"&&mas[q-1][w]=="!"){
                    m[q][w]++;
                    mas[q][w]=m[q][w]+"";
                }
            }
        }

        for (int q=0;q<rows-1;q++){//снизу
            for (int w=0;w<columns;w++){
                if (mas[q][w]!="!"&&mas[q+1][w]=="!"){
                    m[q][w]++;
                    mas[q][w]=m[q][w]+"";
                }
            }
        }

        for (int q=1;q<rows;q++){//слева по диагонали вверху
            for (int w=1;w<columns;w++){
                if (mas[q][w]!="!"&&mas[q-1][w-1]=="!"){
                    m[q][w]++;
                    mas[q][w]=m[q][w]+"";
                }
            }
        }

        for (int q=1;q<rows;q++){//справа по диагонали вверху
            for (int w=0;w<columns-1;w++){
                if (mas[q][w]!="!"&&mas[q-1][w+1]=="!"){
                    m[q][w]++;
                    mas[q][w]=m[q][w]+"";
                }
            }
        }

        for (int q=0;q<rows-1;q++){//слева по диагонали внизу
            for (int w=1;w<columns;w++){
                if (mas[q][w]!="!"&&mas[q+1][w-1]=="!"){
                    m[q][w]++;
                    mas[q][w]=m[q][w]+"";
                }
            }
        }

        for (int q=0;q<rows-1;q++){//справа по диагонали внизу
            for (int w=0;w<columns-1;w++){
                if (mas[q][w]!="!"&&mas[q+1][w+1]=="!"){
                    m[q][w]++;
                    mas[q][w]=m[q][w]+"";
                }
            }
        }

    }

}
