package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class AddDonReq extends AppCompatActivity {

    TextInputLayout editTextName,editTextPno, editTextEmail, editTextAdd, editTextMem, editTextPin;
    EditText editTextOther;
    CheckBox cFood, cCloth, cMed, cGro, cOth;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_don_req);

        editTextName = findViewById(R.id.name_req);
        editTextPno = findViewById(R.id.ph_req);
        editTextEmail = findViewById(R.id.email_req);
        editTextAdd = findViewById(R.id.add_req);
        editTextMem = findViewById(R.id.mem_req);
        editTextPin = findViewById(R.id.pin_req);
        editTextOther = findViewById(R.id.oth_text_req);

        cFood = findViewById(R.id.food_req);
        cCloth = findViewById(R.id.clothes_req);
        cMed = findViewById(R.id.med_req);
        cGro = findViewById(R.id.groc_req);
        cOth = findViewById(R.id.oth_req);
    }

    public void addItemToSheet(View view) {

        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
        final String name = editTextName.getEditText().getText().toString().trim();
        final String pno = editTextPno.getEditText().getText().toString().trim();
        final String email = editTextEmail.getEditText().getText().toString().trim();
        final String add = editTextAdd.getEditText().getText().toString().trim();
        final String pin = editTextPin.getEditText().getText().toString().trim();
        final String mem = editTextMem.getEditText().getText().toString().trim();
        final String oth = editTextOther.getText().toString().trim();
        String d = "";

        if(cFood.isChecked()) {
            d += cFood.getText().toString() + ",";
        }
        if(cCloth.isChecked()) {
            d += cCloth.getText().toString() + ",";
        }
        if(cMed.isChecked()) {
            d += cMed.getText().toString() + ",";
        }
        if(cGro.isChecked()) {
            d += cGro.getText().toString() + ",";
        }
        if(cOth.isChecked()) {
            d += oth + ",";
        }

        final String don = d;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzUUy3S_BD1MozbSSiwIUIylliwAkBYqgZMDB6Ca3SVG0EKgjM/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(AddDonReq.this,response,Toast.LENGTH_LONG).show();
                        Log.d("response:", response);
                        Log.d("values:", name + "," + pno + "," + email + "," +add + "," + pin + "," + mem + "," + don);
                        Intent intent = new Intent(getApplicationContext(),valuedactivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addItem");
                parmas.put("donNam",name);
                parmas.put("donPh",pno);
                parmas.put("donEmail",email);
                parmas.put("donAdd",add);
                parmas.put("donPin",pin);
                parmas.put("donMem",mem);
                parmas.put("donDon",don);

                return parmas;
            }
        };

        int socketTimeOut = 50000;

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }

}
