package com.example.lars.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputActivity extends AppCompatActivity {

    TextView inputTitle, inputContent;
    EntryDatabase db;
    JournalEntry inputEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        db = EntryDatabase.getInstance(getApplicationContext());
        inputEntry = new JournalEntry(0,null,null,null,null);
    }

    public void moodClick(View view) {
        inputEntry.setMood(String.valueOf(view.getContentDescription()));
    }

    public void addEntry(View view) {
        inputTitle = findViewById(R.id.inputTitle);
        inputContent = findViewById(R.id.inputContent);
        inputEntry.setTitle(String.valueOf(inputTitle.getText()));
        inputEntry.setContent(String.valueOf(inputContent.getText()));
        inputEntry.setTimestamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        db.insert(inputEntry);
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
