package com.example.examples

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examples.R

class DisplayActivity : AppCompatActivity() {

    private var mFName: String? = null
    private var mMName: String? = null
    private var mLName: String? = null
    private var mFNameText: TextView? = null
    private var mMNameText: TextView? = null
    private var mLNameText: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val receivedIntent = intent

        mFName = receivedIntent.getStringExtra("ET_FN")
        mMName = receivedIntent.getStringExtra("ET_MN")
        mLName = receivedIntent.getStringExtra("ET_LN")

        mFNameText = findViewById(R.id.fn)
        mMNameText = findViewById(R.id.mn)
        mLNameText = findViewById(R.id.ln)

        mFNameText!!.text = mFName
        mMNameText!!.text = mMName
        mLNameText!!.text = mLName


    }
    override fun onSaveInstanceState(out: Bundle) {

        super.onSaveInstanceState(out)

        mFName = mFNameText!!.text.toString()
        mMName = mMNameText!!.text.toString()
        mLName = mLNameText!!.text.toString()

        out.putString("ET_FN", mFName)
        out.putString("ET_MN", mMName)
        out.putString("ET_LN", mLName)
    }
    override fun onRestoreInstanceState(saved: Bundle) {

        super.onRestoreInstanceState(saved)

        mFNameText!!.text = saved.getString("ET_FN")
        mMNameText!!.text = saved.getString("ET_MN")
        mLNameText!!.text = saved.getString("ET_LN")
    }
}