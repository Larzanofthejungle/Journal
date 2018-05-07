package com.example.lars.journal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new ListItemClickListener());
        listView.setOnItemLongClickListener(new ListItemLongClickListener());
        db = EntryDatabase.getInstance(getApplicationContext());
        db.getWritableDatabase();
        EntryAdapter adapter = new EntryAdapter(this, db.selectAll());
        listView.setAdapter(adapter);
    }

    public void newInput(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    }

    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            db.delete(l);
            return true;
        }
    }
}

