package com.example.resister_quiz2;










        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import java.util.HashMap;
        import java.util.Map;

public class resister extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextName;
    private  EditText editTextPass;
    private  EditText editTextAddr;
    private  EditText editTextMobno;
    private Button buttonRegister;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resister);
        context = this;
        editTextEmail = (EditText)findViewById(R.id.etemail);
        editTextName = (EditText)findViewById(R.id.etname);
        editTextPass = (EditText)findViewById(R.id.etpass);
        editTextAddr = (EditText)findViewById(R.id.etaddress);
        editTextMobno = (EditText)findViewById(R.id.etmobno);
        buttonRegister = (Button) findViewById(R.id.btregister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String name  = editTextName.getText().toString();
                String pass  = editTextPass.getText().toString();
                String addr  = editTextAddr.getText().toString();
                String mobno = editTextMobno.getText().toString();
                register(name,email,pass,addr,mobno);
            }
        });



    }

    private void register(final String name, final String email, final String pass, final String addr, final String mobno) {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://pranalinargunde.000webhostapp.com/resister.php",

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, ""+response, Toast.LENGTH_SHORT).show();
                        System.out.println("shiv:"+response);

                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> postData = new HashMap<>();
                postData.put("name",name);
                postData.put("email",email);
                postData.put("mobile",mobno);
                postData.put("pass",pass);
                postData.put("address",addr);
                return postData;
            }
        };
        requestQueue.add(stringRequest);


    }


}

