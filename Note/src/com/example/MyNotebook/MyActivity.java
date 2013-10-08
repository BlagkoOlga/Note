package com.example.MyNotebook;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;


public class MyActivity extends SherlockActivity{
    CreateAdapter adapter;
    CreateOpenHelper helper;
    private static final int ADD_ID = 1;
    ListView noteList;
    Button createBtn;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setTheme(R.style.Theme_Sherlock);
        setContentView(R.layout.main);

        noteList = (ListView)findViewById(R.id.lv_note);

        adapter = new CreateAdapter(this);

        String[] from = {helper.TITLE, helper.NOTE};
        int[] to = {R.id.tv_title, R.id.tv_note};
        cursor = adapter.queryNote();
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,R.layout.row, cursor, from, to);
        noteList.setAdapter(cursorAdapter);
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                Bundle passdata = new Bundle();
                Cursor listCursor = (Cursor) arg0.getItemAtPosition(arg2);
                int nameId = listCursor.getInt(listCursor
                        .getColumnIndex(helper.KEY_ID));
                passdata.putInt("keyid", nameId);
                Intent passIntent = new Intent(MyActivity.this,
                        EditActivity.class);
                passIntent.putExtras(passdata);
                startActivity(passIntent);

            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,ADD_ID,0, "Edit")
                .setIcon(android.R.drawable.ic_menu_add)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case ADD_ID:
                Intent intent = new Intent(MyActivity.this,CreateActivity.class);
                startActivity(intent);
                break;
            default:

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        cursor.requery();

    }
}
