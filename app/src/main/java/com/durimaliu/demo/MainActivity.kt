package com.durimaliu.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.durimaliu.pinpad.PinPad
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), (View, PinPad) -> Unit {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pinPadView.onItemClickListener = this
    }

    override fun invoke(view: View, pinpad: PinPad) {
        textView.text = String.format("%s%s", view, pinpad.toString())
    }
}
