package com.onlyapp.only;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class SelectSectionActivity extends AppCompatActivity {
    final String SAVE_LOAD = "save_load";
    final String SAVE_SELECT = "save_select";
    boolean load = false;
    int selectSection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_section);

        loadSettings();
        if (load) {
            Intent intent = new Intent(SelectSectionActivity.this, MainActivity.class);
            intent.putExtra("selectSection", selectSection);
            startActivity(intent);
        }
    }

    public void onClickMen(View view) {
        load = true;
        selectSection=0;
        saveSettings();

        Intent intent = new Intent(SelectSectionActivity.this, MainActivity.class);
        intent.putExtra("selectSection", selectSection);
        startActivity(intent);

    }

    public void onClickWomen(View view) {
        load = true;
        selectSection=1;
        saveSettings();

        Intent intent = new Intent(SelectSectionActivity.this, MainActivity.class);
        intent.putExtra("selectSection", selectSection);
        startActivity(intent);
    }

    public void saveSettings() {
        SharedPreferences.Editor ed = getSharedPreferences("setting", MODE_PRIVATE).edit();
        ed.putBoolean(SAVE_LOAD, load);
        ed.putInt(SAVE_SELECT, selectSection);
        ed.commit();
    }

    public void loadSettings() {
        load = getSharedPreferences("setting", MODE_PRIVATE).getBoolean(SAVE_LOAD, false);
        selectSection = getSharedPreferences("setting", MODE_PRIVATE).getInt(SAVE_SELECT, 0);
    }

}