package com.example.admin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    EditText [][] currently_allocated_edt,maximum_resources_edt;

    EditText [] total_resources_edt,available_resources_edt;

    Integer [][]  currently_allocated,maximum_resources,remaining_need;

    Integer [] total_resources;

    Integer [] available_resources;

    HashMap<String, Integer> mapping;

    String chosen_process,chosen_resource;
    int num_of_chosen_resource;

    EditText num_of_resource_edt;

    RadioButton pa,pb,pc,ra,rb,rc,rd;

    List<Integer> f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f=new ArrayList<>();

        num_of_resource_edt=findViewById(R.id.needed_resources);

        pa=findViewById(R.id.radioButtonpa);
        pb=findViewById(R.id.radioButtonpb);
        pc=findViewById(R.id.radioButtonpc);

        ra=findViewById(R.id.radioButtonRA);
        rb=findViewById(R.id.radioButtonRB);
        rc=findViewById(R.id.radioButtonRC);
        rd=findViewById(R.id.radioButtonRD);




        mapping= new HashMap<>();

        mapping.put("A", 0);
        mapping.put("B", 1);
        mapping.put("C", 2);
        mapping.put("D", 3);

        mapping.put("P1", 0);
        mapping.put("P2", 1);
        mapping.put("P3", 2);

        currently_allocated_edt=new EditText[5][5];
        maximum_resources_edt=new EditText[5][5];
        total_resources_edt=new EditText[5];
        available_resources_edt=new EditText[5];

        currently_allocated=new Integer[5][5];
        maximum_resources=new Integer[5][5];
        remaining_need=new Integer[5][5];
        total_resources=new Integer[5];
        available_resources=new Integer[5];

        available_resources_edt[0]=findViewById(R.id.available_resourcesA);
        available_resources_edt[1]=findViewById(R.id.available_resourcesB);
        available_resources_edt[2]=findViewById(R.id.available_resourcesC);
        available_resources_edt[3]=findViewById(R.id.available_resourcesD);


        total_resources_edt[0]=findViewById(R.id.total_system_resources_A);
        total_resources_edt[1]=findViewById(R.id.total_system_resources_B);
        total_resources_edt[2]=findViewById(R.id.total_system_resources_C);
        total_resources_edt[3]=findViewById(R.id.total_system_resources_D);


        currently_allocated_edt[0][0]=findViewById(R.id.currently_00);
        currently_allocated_edt[0][1]=findViewById(R.id.currently_01);
        currently_allocated_edt[0][2]=findViewById(R.id.currently_02);
        currently_allocated_edt[0][3]=findViewById(R.id.currently_03);

        currently_allocated_edt[1][0]=findViewById(R.id.currently_10);
        currently_allocated_edt[1][1]=findViewById(R.id.currently_11);
        currently_allocated_edt[1][2]=findViewById(R.id.currently_12);
        currently_allocated_edt[1][3]=findViewById(R.id.currently_13);

        currently_allocated_edt[2][0]=findViewById(R.id.currently_20);
        currently_allocated_edt[2][1]=findViewById(R.id.currently_21);
        currently_allocated_edt[2][2]=findViewById(R.id.currently_22);
        currently_allocated_edt[2][3]=findViewById(R.id.currently_23);


        maximum_resources_edt[0][0]=findViewById(R.id.maximum_00);
        maximum_resources_edt[0][1]=findViewById(R.id.maximum_01);
        maximum_resources_edt[0][2]=findViewById(R.id.maximum_02);
        maximum_resources_edt[0][3]=findViewById(R.id.maximum_03);

        maximum_resources_edt[1][0]=findViewById(R.id.maximum_10);
        maximum_resources_edt[1][1]=findViewById(R.id.maximum_11);
        maximum_resources_edt[1][2]=findViewById(R.id.maximum_12);
        maximum_resources_edt[1][3]=findViewById(R.id.maximum_13);

        maximum_resources_edt[2][0]=findViewById(R.id.maximum_20);
        maximum_resources_edt[2][1]=findViewById(R.id.maximum_21);
        maximum_resources_edt[2][2]=findViewById(R.id.maximum_22);
        maximum_resources_edt[2][3]=findViewById(R.id.maximum_23);

        


    }



    public void read_maximum_resources() {

        boolean check = true;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 4; j++) {
                if (maximum_resources_edt[i][j].getText().toString().trim().isEmpty()) {
                    check = false;
                    break;
                }
            }
        }

        if(check){

            for(int i=0;i<3;i++){
                for(int j=0;j<4;j++)
                    maximum_resources[i][j]=Integer.valueOf( maximum_resources_edt[i][j].getText().toString() );
            }}
    }


    public void read_currently_resources(){

        boolean check=true;

        for(int i=0;i<3;i++){

            for(int j=0;j<4;j++){
                if( currently_allocated_edt[i][j].getText().toString().trim().isEmpty()    ){
                    check=false;
                    break;
                }}}


        if(check){

            for(int i=0;i<3;i++){
                for(int j=0;j<4;j++)
                    currently_allocated[i][j]=Integer.valueOf( currently_allocated_edt[i][j].getText().toString() );
            }}

    }


    public void read_total_resources(){
        boolean check=true;

        for (int i = 0; i < 4; i++) {

            if(total_resources_edt[i].getText().toString().trim().isEmpty()){

                check=false;
                break;
            }
        }

        if(check){

            for (int i = 0; i <4 ; i++)
                total_resources[i]=Integer.valueOf( total_resources_edt[i].getText().toString() );


        }
    }


    public void read_available_resource(){

        {
            boolean check=true;

            for (int i = 0; i < 4; i++) {

                if(available_resources_edt[i].getText().toString().trim().isEmpty()){

                    check=false;
                    break;
                }
            }

            if(check){

                for (int i = 0; i <4 ; i++)
                    available_resources[i]=Integer.valueOf( available_resources_edt[i].getText().toString() );


            }
        }

    }


    public void submit(View view) {
        read_total_resources();
        read_maximum_resources();
        read_available_resource();
        read_currently_resources();

        if(ra.isChecked())
            chosen_resource="A";

        else if(rb.isChecked())
            chosen_resource="B";

        else if(rc.isChecked())
            chosen_resource="C";

        else if(rd.isChecked())
            chosen_resource="D";

        if(pa.isChecked())
            chosen_process="P1";

        else if(pb.isChecked())
            chosen_process="P2";

        else if(pc.isChecked())
            chosen_process="P3";


        num_of_chosen_resource=Integer.valueOf( num_of_resource_edt.getText().toString()  );


        read_request();

        for(int i=0;i<3;i++){

            for(int j=0;j<4;j++)
                remaining_need[i][j]=maximum_resources[i][j]-currently_allocated[i][j];
        }


        if(banker_algorithm())
            Toast.makeText(this, "safe", Toast.LENGTH_LONG).show();

        else
            Toast.makeText(this, "unsafe", Toast.LENGTH_LONG).show();
    }

    public void read_request(){


        int i=mapping.get(chosen_resource);
        available_resources[i]=available_resources[i]-num_of_chosen_resource;

        int j=mapping.get(chosen_process);

        currently_allocated[i][j]=currently_allocated[i][j]+num_of_chosen_resource;


    }




    public boolean banker_algorithm(){
        boolean safe=true;
        boolean flag=true;
        int counter=0;

        for(int i=0;i<3; i++){

            if(!f.contains(i)) {

                for (int j = 0; j < 4; j++) {

                    if (remaining_need[i][j] > available_resources[j]) {
                        flag = false;
                        counter++;
                    }


                }

                if (flag) {

                    for (int t = 0; t < 4; t++) {

                        available_resources[t] = currently_allocated[i][t] + available_resources[t];
                    }
                    f.add(i);
                    banker_algorithm();


                }

            }




        }

        if(counter==3)
            return false;
        return true;

    }









}