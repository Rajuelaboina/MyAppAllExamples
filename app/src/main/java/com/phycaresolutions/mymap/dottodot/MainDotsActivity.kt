package com.phycaresolutions.mymap.dottodot

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainDotsActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var dotsView: PaintView
    lateinit var btnUndo: Button
    lateinit var btnReset: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* setContentView(R.layout.activity_pattern)
        dotsView = findViewById(R.id.dotsView)
        btnUndo = findViewById(R.id.btnUndo)
        btnReset = findViewById(R.id.btnReset)
        setListeners()*/
       // val connectDotsView = ConnectDotsView(applicationContext).setPaint()
    }

    private fun setListeners() {

        dotsView.setOnTouchListener(dotsView)
        btnUndo.setOnClickListener(this)
        btnReset.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btnUndo -> {
                dotsView.undo()
            }
            btnReset -> {
                dotsView.clear()
            }
        }
    }
}