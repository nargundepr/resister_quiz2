package com.example.resister_quiz2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextpass;
    private Button buttonLogin;
    private String email;
    private String pass;
    private Context context;

    private TextView textviewresister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;

        editTextEmail = (EditText) findViewById(R.id.etemail);
        editTextpass = (EditText) findViewById(R.id.etpass);
        buttonLogin = (Button) findViewById(R.id.btlogin);
        textviewresister=(TextView)findViewById(R.id.tvresister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              email = editTextEmail.getText().toString();
                 pass = editTextpass.getText().toString();
                if(!email.isEmpty() || !pass.isEmpty())
{  logintopage();
}  else{
                    editTextEmail.setError("insert email");
                    editTextpass.setError("insert password");

                }

            }
        });

        textviewresister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =  new Intent (login.this,resister.class);
                startActivity(intent);
            }
        });
    }
      private void logintopage()
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://pranalinargunde.000webhostapp.com/login.php",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    /*try
                    {
                        JSONObject jsonObject=new JSONObject(response);
                        String success = jsonObject.getString("success");
                        JSONArray jsonArray=jsonObject.getJSONArray("login");


                        if(success.equals(1))
                        {
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject obj=jsonArray.getJSONObject(i);
                                String email=obj.getString("email");
                                Toast.makeText(login.this, "login succesfully", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                    catch(JSONException e)
                    {
                        e.printStackTrace();
                        Toast.makeText(login.this, "something is wrong", Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(login.this, "logged suucessfully", Toast.LENGTH_SHORT).show();*/
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        Toast.makeText(context, ""+jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                        final String s = jsonObject.getString("success");

                                if(s.equals("Log in successful"))
                                {
                                    Intent intent=new Intent(login.this,quiz2.class);
                                    startActivity(intent);
                                }


                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(login.this, "something is wrong ", Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                protected Map<String,String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("pass",pass);
                    return params;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);




        }



}
