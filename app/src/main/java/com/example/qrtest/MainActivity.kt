package com.example.qrtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qrtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, ScannerFragment())
        transaction.commit()

    }
    override fun onDestroy()
    {
        mBinding = null
        super.onDestroy()
    }


}