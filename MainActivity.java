package com.example.dbphp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText edName,edMobile,edEmail;
    Button inputindatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        edName =findViewById(R.id.edName);
        edMobile =findViewById(R.id.edMobile);
        edEmail =findViewById(R.id.edEmail);
        inputindatabase=findViewById(R.id.inputindatabase);


// বাটনে setOnClickListener চালু করলাম
        inputindatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                String name = edName.getText().toString();
                String mobile = edMobile.getText().toString();
                String email = edEmail.getText().toString();
                //Input text গুলো string এ ধরলাম

                //https://nubsoft.xyz/data.php?n=shahrear&m=01872000&e=arshahrear30
                //লক্ষ্য করো n এবং m এবং e এগুলো key । আর shahrear , 01872000 , arshahrear30 এগুলো হলো Values ।
                //তাহলে .php এর  পর key=values এভাবে data input দিতে পারি ।
                //এখন এই link এ value গুলো update করলে আরেকটা database এ row create হয়ে data গুলো input হবে ।

                String url = "https://nubsoft.xyz/data.php?n=" +name+ "&m=" +mobile+ "&e=" +email ;

                //object / array request করলে এখানে.Get,url এর পর  একটা null দিতাম . কিন্তু  এটা string তাই Response.Listener নিলাম

                progressBar.setVisibility(View.VISIBLE); //বাটলে click করলে progress bar আসবে এবং load হওয়া দেখাবে

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    //একটা stringRequest দিয়ে url get করলাম এবং একটা Listener নিলাম
                    @Override
                    public void onResponse(String response) {
                        // যখন কাজ হবে তখন progressBar GONE হবে
                        progressBar.setVisibility(View.GONE);
                        //AlertDialog হলো screen এর উপর box message আকারে দেখাবে
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Your Server Response")
                                .setMessage(response)
                                .show();
                    }
                    //.show(); line 1
                    // } line 2
                    //});  line 3
                    // line 3 এ 2nd bracket এর পর new Response.ErrorListener call করবো
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });




if(name.length()>0) {

    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
    requestQueue.add(stringRequest);

    // এখানে name দেওয়া ফরজ করে দিয়েছি তা না হয় setError হবে । আর input  না দিলে RequestQueue কাজ করবে না
}else edName.setError("input your name");

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
