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
import com.example.qrtest.databinding.FragmentMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.journeyapps.barcodescanner.CaptureManager

class MainFragment : Fragment(){

    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var capture : CaptureManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        val integrator = IntentIntegrator.forSupportFragment(this@MainFragment)

        integrator.setOrientationLocked(false)
        integrator.setPrompt("Scan QR code")
        integrator.setBeepEnabled(false)
        integrator.captureActivity = Scanner::class.java
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)

        integrator.initiateScan()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result : IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result !=null) {
            if(result.contents == null) {
                // qr코드에 주소가 없거나, 뒤로가기 클릭 시
                Log.d("#####################",result.contents.toString())
                Toast.makeText(requireContext() as Activity?,"cancelled", Toast.LENGTH_LONG).show()
            } else {
                //qr코드에 주소가 있을때 -> 주소에 관한 Toast 띄우는 함수 호출
                Log.d("#####################",result.contents.toString())
                Toast.makeText(requireContext() as Activity?,result.contents.toString(), Toast.LENGTH_LONG).show()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }



}