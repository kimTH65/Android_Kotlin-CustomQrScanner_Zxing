package com.example.qrtest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qrtest.databinding.ActivityMainBinding
import com.example.qrtest.databinding.ScannerBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureManager

class Scanner : AppCompatActivity() {
    private var mBinding: ScannerBinding? = null
    private val binding get() = mBinding!!
    private lateinit var capture : CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.barcodescanner.setStatusText("") // 스캐너 아래쪽 텍스트 설정
        capture = CaptureManager(this,binding.barcodescanner)
        capture.initializeFromIntent(Intent(), savedInstanceState)
        capture.decode()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }
    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        capture.onRequestPermissionsResult(requestCode,permissions, grantResults)
    }
}