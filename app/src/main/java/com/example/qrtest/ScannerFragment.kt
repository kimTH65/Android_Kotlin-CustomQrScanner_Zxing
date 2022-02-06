package com.example.qrtest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qrtest.databinding.FragmentScannerBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.journeyapps.barcodescanner.CaptureManager

class ScannerFragment : Fragment(){

    private var _binding: FragmentScannerBinding? = null
    private val binding get() = _binding!!
    private lateinit var capture : CaptureManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScannerBinding.inflate(inflater, container, false)

        //integrator = 적분기, 바코드 스캐너 실행
        val integrator = IntentIntegrator.forSupportFragment(this@ScannerFragment)
        integrator.setBeepEnabled(false) // 스캔 시 소리
        integrator.setOrientationLocked(false) // 가로,세로 모드 고정
        integrator.captureActivity = Scanner::class.java //커스텀 스캐너 액티비티
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) //원하는 바코드 형식
        integrator.setPrompt("Scan QR code") //스캔 하단 문구
        integrator.initiateScan() // 스캐너 실행
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // qr결과값
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result : IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result !=null) {
            if(result.contents == null) {
                // qr코드 없을 때
                Log.d("#####################",result.contents.toString())
                Toast.makeText(requireContext() as Activity?,"cancelled", Toast.LENGTH_LONG).show()
            } else {
                //qr코드에 주소가 있을때
                Log.d("#####################",result.contents.toString())
                Toast.makeText(requireContext() as Activity?,result.contents.toString(), Toast.LENGTH_LONG).show()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }



}