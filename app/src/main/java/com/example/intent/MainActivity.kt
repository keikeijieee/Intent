package com.example.intent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSend.setOnClickListener {

            sendMessage()

        }
    }

    private fun sendMessage() {
        //Input validation
        if(editTextMessage.text.isEmpty()){
            editTextMessage.setError("Value required")
            return
        }
        if(editTextLucky.text.isEmpty()){
            editTextMessage.setError("Value required")
            return
        }
        //Explicit Intent
        val intent = Intent(this, SecondActivity::class.java)

        //Insert extra to the Intent
        val message = editTextMessage.text.toString()
        intent.putExtra(EXTRA_MSG, message)
        val lucky = editTextLucky.text.toString().toInt()
        intent.putExtra(EXTRA_LUCKY,lucky)

        startActivityForResult(intent, REQUEST_CODE)
        //startActivity(intent) //No return value from the SecondActivity

    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(EXTRA_REPLY)
                //Display reply here
                textViewReply.text = reply
            }
        }
    }

    companion object{
        const val EXTRA_MSG = "com.example.intent.EXTRA_MSG" //infront is package name
        const val EXTRA_LUCKY = "com.example.intent.EXTRA_LUCKY"
        const val EXTRA_REPLY = "com.example.intent.EXTRA_REPLY"
        const val REQUEST_CODE = 1 // other request code must be another value
    }

}
