package com.example.qrtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.qrtest.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val transaction = supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, MainFragment())
        transaction.commit()


    }
    override fun onDestroy()
    {
        mBinding = null
        super.onDestroy()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result : IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result !=null) {
            if(result.contents == null) {
                // qr코드에 주소가 없거나, 뒤로가기 클릭 시
                Log.d("#####################",result.contents.toString())
                Toast.makeText(this,result.contents.toString(), Toast.LENGTH_LONG).show()
            } else {
                //qr코드에 주소가 있을때 -> 주소에 관한 Toast 띄우는 함수 호출
                Toast.makeText(this,"cancelled", Toast.LENGTH_LONG).show()

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

}