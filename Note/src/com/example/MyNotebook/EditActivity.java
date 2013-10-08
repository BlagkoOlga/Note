package com.example.MyNotebook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * Created by Olka on 06.10.13.
 */
public class EditActivity extends SherlockActivity {
    CreateAdapter adapter;
    int rowId;
    Cursor c;
    private static final int DELETE_ID = 1;
    EditText title, note;
    Button editSubmit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Sherlock);
        setContentView(R.layout.edit);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        title = (EditText) findViewById(R.id.et_edittitle);
        note = (EditText) findViewById(R.id.et_editnote);
        editSubmit = (Button) findViewById(R.id.btn_update);


        Bundle showData = getIntent().getExtras();
        rowId = showData.getInt("keyid");
        adapter = new CreateAdapter(this);
        c = adapter.queryAll(rowId);

        if (c.moveToFirst()) {
            do {
                title.setText(c.getString(1));
                note.setText(c.getString(2));

            } while (c.moveToNext());
        }

        editSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                adapter.update(rowId, title.getText().toString(),
                        note.getText().toString());
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,DELETE_ID,0, "DELETE")
                .setIcon(android.R.drawable.ic_menu_delete)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case DELETE_ID:
                adapter.deletOneRecord(rowId);
                finish();
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
