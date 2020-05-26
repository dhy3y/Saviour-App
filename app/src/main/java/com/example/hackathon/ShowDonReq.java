package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowDonReq extends AppCompatActivity {

    ListView listView;
    ListAdapter adapter;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_don_req);
        
        listView = findViewById(R.id.lv_items);

        getItems();
        
    }

    private void getItems() {

        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbzUUy3S_BD1MozbSSiwIUIylliwAkBYqgZMDB6Ca3SVG0EKgjM/exec?action=getItems",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void parseItems(String jsonResposnce) {

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("items");


            for (int i = 0; i < jarray.length(); i++) {

                JSONObject jo = jarray.getJSONObject(i);

                String itemId = jo.getString("itemId");
                String itemName = jo.getString("itemName");
                String itemPno = jo.getString("itemPno");
                String itemPin = jo.getString("itemPin");
                String itemMem = jo.getString("itemMem");
                String itemdon = jo.getString("itemdon");


                HashMap<String, String> item = new HashMap<>();
                item.put("itemId", itemId);
                item.put("itemName", itemName);
                item.put("itemPno",itemPno);
                item.put("itemPin",itemPin);
                item.put("itemMem",itemMem);
                item.put("itemdon",itemdon);

                list.add(item);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        adapter = new SimpleAdapter(this,list,R.layout.list_row,
                new String[]{"itemId","itemName","itemPno","itemPin","itemMem","itemdon"},new int[]{R.id.get_id,R.id.get_name,R.id.get_pno, R.id.get_pin, R.id.get_num, R.id.get_don});


        listView.setAdapter(adapter);
        loading.dismiss();
    }
}
