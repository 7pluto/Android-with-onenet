package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class ColorPickerActivity extends AppCompatActivity {

    private ColorPickerView colorDisk=null;
    private TextView tv_cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        tv_cd=findViewById(R.id.tv_info);
        colorDisk=findViewById(R.id.colorDisk);
        colorDisk.setOnColorBackListener(new ColorPickerView.OnColorBackListener() {
            @Override
            public void onColorBack(int a, int r, int g, int b) {
                tv_cd.setText("R：" + r + "\nG：" + g + "\nB：" + b + "\n" + colorDisk.getColorStr());
                tv_cd.setTextColor(Color.argb(a, r, g, b));
            }
        });
    }
}