package com.example.examples

import DisplayActivity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts


    class MainActivity : AppCompatActivity(), View.OnClickListener {
        private var mFName: String? = null
        private var mMName: String? = null
        private var mLName: String? = null
        private var fullName: String? = null

        private var mTvFirstName: TextView? = null
        private var mTvLastName: TextView? = null

        private var mButtonSubmitFn: Button? = null
        private var mButtonSubmitMn: Button? = null
        private var mButtonSubmitLn: Button? = null
        private var mButtonSubmitFinal: Button? = null
        private var mButtonCam: Button? = null
        private var mEtFName: EditText? = null
        private var mEtMName: EditText? = null
        private var mEtLName: EditText? = null

        private var mPic: ImageView? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
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
        }

        override fun onClick(v: View?) {
            when (v?.id) {
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
                }


            }
        }

        private val cameraActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    mPic = findViewById<View>(R.id.iv_pic) as ImageView
                    //val extras = result.data!!.extras
                    //val thumbnailImage = extras!!["data"] as Bitmap?

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
