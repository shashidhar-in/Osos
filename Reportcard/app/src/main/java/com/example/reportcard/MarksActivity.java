package com.example.reportcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MarksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        ArrayList<Marks> score=new ArrayList<Marks>();

        score.add(new Marks("telugu","98"));
        score.add(new Marks("hindi","89"));
        score.add(new Marks("English","90"));
        score.add(new Marks("Maths","60"));
        score.add(new Marks("science","100"));
        score.add(new Marks("social","90"));

        M_adapter adapter=new M_adapter(this,score);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);



    }
}