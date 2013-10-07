package com.example.MyNotebook;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Olka on 06.10.13.
 */
public class EditActivity extends Activity {
    CreateAdapter adapter;
    CreateOpenHelper openHelper;
    int rowId;
    Cursor c;
    String titleValue, noteValue;
    EditText title, note;
    Button editSubmit, btnDelete;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        title = (EditText) findViewById(R.id.et_edittitle);
        note = (EditText) findViewById(R.id.et_editnote);
        editSubmit = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

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
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                adapter.deletOneRecord(rowId);
                finish();
            }
        });
    }
}
