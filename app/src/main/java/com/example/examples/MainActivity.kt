package com.example.examples

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.EditText
import android.widget.Toast
import android.widget.Button


    class MainActivity : AppCompatActivity(), View.OnClickListener {
        private var mFName: String? = null
        private var mMName: String? = null
        private var mLName: String? = null

        private var mFirstNameText: TextView? = null
        private var mMiddleNameText: TextView? = null
        private var mLastNameText: TextView? = null

        private var mButtonSubmitFn: Button? = null
        private var mButtonSubmitMn: Button? = null
        private var mButtonSubmitLn: Button? = null
        private var mButtonSubmitFinal: Button? = null
        private var mButtonCam: Button? = null
        private var mEtFName: EditText? = null
        private var mEtMName: EditText? = null
        private var mEtLName: EditText? = null

        private var mPic: ImageView? = null

        override fun onCreate(saved: Bundle?) {
            super.onCreate(saved)
            setContentView(R.layout.activity_main)

            //button
            mButtonSubmitFn = findViewById(R.id.button_submit_fn)
            mButtonSubmitMn = findViewById(R.id.button_submit_mn)
            mButtonSubmitLn = findViewById(R.id.button_submit_ln)
            mButtonSubmitFinal = findViewById(R.id.button_submit_final)
            mButtonCam = findViewById(R.id.button_pic)


            mButtonSubmitFn!!.setOnClickListener(this)
            mButtonSubmitMn!!.setOnClickListener(this)
            mButtonSubmitLn!!.setOnClickListener(this)
            mButtonCam!!.setOnClickListener(this)
            mButtonSubmitFinal!!.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            when (view?.id) {
                R.id.button_submit_fn ->{
                    mEtFName = findViewById(R.id.et_name_fn)
                    mFName = mEtFName!!.text.toString()

                }
                R.id.button_submit_mn -> {
                    mEtMName = findViewById(R.id.et_name_mn)
                    mMName = mEtMName!!.text.toString()
                }
                R.id.button_submit_ln -> {
                    mEtLName = findViewById(R.id.et_name_ln)
                    mLName = mEtLName!!.text.toString()
                }
                R.id.button_submit_final -> {
                    mEtFName = findViewById(R.id.et_name_fn)
                    mEtMName = findViewById(R.id.et_name_mn)
                    mEtLName = findViewById(R.id.et_name_ln)

                    mFName = mEtFName!!.text.toString()
                    mMName = mEtMName!!.text.toString()
                    mLName = mEtLName!!.text.toString()


                    //goes to displayactivity
                    val messageIntent = Intent(this, DisplayActivity::class.java)
                    messageIntent.putExtra("ET_FN", mFName)
                    messageIntent.putExtra("ET_MN", mMName)
                    messageIntent.putExtra("ET_LN", mLName)
                    this.startActivity(messageIntent)
                }
                R.id.button_pic -> {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    try{
                        cameraActivity.launch(cameraIntent)
                    }catch(ex:ActivityNotFoundException){
                        Toast.makeText(
                            this@MainActivity,
                            "CAMERA FAIL!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


            }
        }
        override fun onSaveInstanceState(out: Bundle) {

            super.onSaveInstanceState(out)

            try {
                mFName = mEtFName!!.text.toString()
                mMName = mEtMName!!.text.toString()
                mLName = mEtLName!!.text.toString()

                out.putString("ET_FN", mFName)
                out.putString("ET_MN", mMName)
                out.putString("ET_LN", mLName)
            }catch(ex:NullPointerException){
                Toast.makeText(
                    this@MainActivity,
                    "Empty Fields!!!!!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        override fun onRestoreInstanceState(saved: Bundle) {

            super.onRestoreInstanceState(saved)

            mFirstNameText!!.text = saved.getString("ET_FN")
            mMiddleNameText!!.text = saved.getString("ET_MN")
            mLastNameText!!.text = saved.getString("ET_LN")
        }

        private val cameraActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    mPic = findViewById<View>(R.id.iv_pic) as ImageView

                    if (Build.VERSION.SDK_INT >= 33) {
                        val thumbnailImage =
                            result.data!!.getParcelableExtra("data", Bitmap::class.java)
                        mPic!!.setImageBitmap(thumbnailImage)
                    } else {
                        val thumbnailImage = result.data!!.getParcelableExtra<Bitmap>("data")
                        mPic!!.setImageBitmap(thumbnailImage)
                    }


                }
            }
    }
