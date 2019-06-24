package com.example.resister_quiz2;





import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.Map;
        import java.util.ArrayList;

public class quiz2 extends Activity {
    private TextView textViewQuestion;
    private RadioButton radioButtonOption1;
    private RadioButton radioButtonOption2;
    private RadioButton radioButtonOption3;
    private RadioButton radioButtonOption4;
    private Button buttonNext;
    private Button buttonPrevious;
    //initialise nhi kl doghana ... ith dim dist ... initialise kl asel tr vegl color ast
    private String question[];
    private String option1[];
    private String option2[];
    private String option3[];
    private String option4[];
    private int[] correctAns;
    private int[] idRadioButton={R.id.rboption1,R.id.rboption2,R.id.rboption3,R.id.rboption4};
    private int currentQuestion=0;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        context=this;


        textViewQuestion = (TextView) findViewById(R.id.tvquestion1);
        radioButtonOption1 = (RadioButton) findViewById(R.id.rboption1);
        radioButtonOption2 = (RadioButton) findViewById(R.id.rboption2);
        radioButtonOption3 = (RadioButton) findViewById(R.id.rboption3);
        radioButtonOption4 = (RadioButton) findViewById(R.id.rboption4);

        buttonPrevious = (Button)findViewById(R.id.btprevious);
        buttonNext = (Button)findViewById(R.id.btnext);

        buttonPrevious.setVisibility(View.GONE);
        loaddata();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPrevious.setVisibility(View.VISIBLE);
                currentQuestion++;
                if(currentQuestion == question.length - 1)
                    buttonNext.setVisibility(View.GONE);
                displayView();
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonNext.setVisibility(View.VISIBLE);
                currentQuestion--;
                if(currentQuestion == 0)
                    buttonPrevious.setVisibility(View.GONE);
                displayView();
            }
        });
        radioButtonOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(v);


            }
        });


        radioButtonOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(v);


            }
        });
        radioButtonOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(v);


            }


        });
        radioButtonOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAns(v);

            }
        });

    }

    private void loaddata() {
        String url = "http://pranalinargunde.000webhostapp.com/two_d_array.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequestLoadData = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {


                    @Override
                    public  void onResponse(String response) {
                        try {
                            Log.i(String.valueOf(1), "onResponse: " + response);
                            Toast.makeText(context, "" + response, Toast.LENGTH_LONG).show();
                            JSONObject jsobj = new JSONObject(response);
                            JSONArray jsarr = jsobj.getJSONArray("quiz");

                            int size = jsarr.length();
                            question = new String[size];
                            correctAns = new int[size];
                            option1 = new String[size];
                            option2 = new String[size];
                            option3 = new String[size];
                            option4 = new String[size];


                            for (int i = 0; i < size; i++) {
                                JSONObject jsonObject=jsarr.getJSONObject(i);

                                question[i] = "Q."+(i+1)+" "+jsonObject.getString("question");
                                correctAns[i] = Integer.parseInt(jsonObject.getString("answer"));
                                option1[i] = jsonObject.getString("option1");
                                option2[i] = jsonObject.getString("option2");

                                option3[i] = jsonObject.getString("option3");
                                option4[i] = jsonObject.getString("option4");
                            }



                            displayView();
                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }








                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }
        )

        {
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                return super.getParams();
            }
        };
        requestQueue.add(stringRequestLoadData);
    }



    private void displayView() {
        textViewQuestion.setText(question[currentQuestion]);
        radioButtonOption1.setText(option1[currentQuestion]);
        radioButtonOption2.setText(option2[currentQuestion]);
        radioButtonOption3.setText(option3[currentQuestion]);
        radioButtonOption4.setText(option4[currentQuestion]);
    }
    public  void checkAns(View v)
    {
        int clickedId = v.getId();
        if(clickedId == idRadioButton[correctAns[currentQuestion] -1])

        {

            Toast.makeText(context, "correct ans", Toast.LENGTH_SHORT).show();        }
        else
        {
            Toast.makeText(context, "wrong ans", Toast.LENGTH_SHORT).show();        }
    }

}


