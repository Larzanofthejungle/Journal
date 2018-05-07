package com.example.lars.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {

    TextView entryRowTitle, entryRowTimeStamp, entryRowEntry;

    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor, false);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        entryRowTitle = view.findViewById(R.id.entryRowTitle);
        entryRowTimeStamp = view.findViewById(R.id.entryRowTimeStamp);
        entryRowEntry = view.findViewById(R.id.entryRowEntry);
        entryRowTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
        entryRowTimeStamp.setText(cursor.getString(cursor.getColumnIndex("timestamp")));
        entryRowEntry.setText(cursor.getString(cursor.getColumnIndex("content")));
    }
}
