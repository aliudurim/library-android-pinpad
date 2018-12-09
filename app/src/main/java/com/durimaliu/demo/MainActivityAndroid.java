package com.durimaliu.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.durimaliu.pinpad.PinPad;
import com.durimaliu.pinpad.PinPadView;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class MainActivityAndroid extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PinPadView pinPadView = findViewById(R.id.pinPadView);
        final TextView textView = findViewById(R.id.textView);

        pinPadView.setOnItemClickListener(new Function2<View, PinPad, Unit>() {
            @Override
            public Unit invoke(View view, PinPad pinPad) {
                textView.setText(String.format("%s%s", view, pinPad.toString()));
                return null;
            }
        });
    }
}
