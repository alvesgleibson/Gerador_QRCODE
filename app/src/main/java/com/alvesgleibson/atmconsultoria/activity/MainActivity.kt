package com.alvesgleibson.atmconsultoria.activity

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.alvesgleibson.atmconsultoria.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponentes()

    }

    private fun initComponentes() {
        button!!.setOnClickListener(){

            if ( TextUtils.isEmpty( txtQRcode.text.toString() ) ){
                txtQRcode.error =  "Não pode ser vazio"
                txtQRcode.requestFocus()
            }else{
                gerarQRCodigo( txtQRcode.text.toString() )
            }

        }
    }

    private fun gerarQRCodigo(conteudoQRCodeParametro: String) {

        //zxing-android-embedded
        val qrCode = QRCodeWriter()

        try {
            val bitMapMatrix = qrCode.encode(conteudoQRCodeParametro, BarcodeFormat.QR_CODE, 500, 500)
            val largura = bitMapMatrix.width
            val altura = bitMapMatrix.height

            val bitmap =  Bitmap.createBitmap( largura, altura, Bitmap.Config.RGB_565)

            for (x in 0 until largura){
                for (y in 0 until altura){
                    bitmap.setPixel(x,y, if (bitMapMatrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }
            ivQRCode.setImageBitmap( bitmap )

        }catch (e: WriterException){

        }

    }

}