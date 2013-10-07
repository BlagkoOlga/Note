package com.example.MyNotebook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Olka on 06.10.13.
 */
public class CreateActivity extends Activity {
    CreateAdapter adapter;
    CreateOpenHelper helper;
    EditText titleEdit, noteEdit;
    Button saveBtn, resetBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);
        titleEdit = (EditText) findViewById(R.id.et_title);
        noteEdit = (EditText) findViewById(R.id.et_note);
        saveBtn = (Button) findViewById(R.id.btn_submit);
        resetBtn = (Button) findViewById(R.id.btn_reset);
        adapter = new CreateAdapter(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String titleValue = titleEdit.getText().toString();
                String noteValue = noteEdit.getText().toString();
                long val = adapter.insertData(titleValue, noteValue);
                finish();
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                titleEdit.setText("");
                noteEdit.setText("");
            }
        });

    }
}
