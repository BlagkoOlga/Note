package com.example.MyNotebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * Created by Olka on 06.10.13.
 */
public class CreateActivity extends SherlockActivity {
    CreateAdapter adapter;
    EditText titleEdit, noteEdit;
    private static final int SAVE_ID = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Sherlock);
        setContentView(R.layout.create);

        getSupportActionBar().setHomeButtonEnabled(true);
        titleEdit = (EditText) findViewById(R.id.et_title);
        noteEdit = (EditText) findViewById(R.id.et_note);
        adapter = new CreateAdapter(this);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,SAVE_ID,0, "Save")
                .setIcon(android.R.drawable.ic_menu_save)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                break;
            case SAVE_ID:
                String titleValue = titleEdit.getText().toString();
                if (titleValue.length==0){
                    Toast.makeText(getApplicationContext(),R.string.error, Toast.LENGTH_SHORT).show;
                }
                String noteValue = noteEdit.getText().toString();
                if (noteValue.length==0){
                    Toast.makeText(getApplicationContext(),R.string.error, Toast.LENGTH_SHORT).show;
                }
                long val = adapter.insertData(titleValue, noteValue);
                finish();
                break;


        }

        return super.onOptionsItemSelected(item);
    }
}
