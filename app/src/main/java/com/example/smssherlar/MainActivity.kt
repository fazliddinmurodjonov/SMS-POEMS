package com.example.smssherlar

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.smssherlar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

//        bind.buttonCopy.setOnClickListener {
//            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
//            val clip: ClipData = ClipData.newPlainText("label", "FAZLIDDIN")
//            clipboard.setPrimaryClip(clip)
//            Toast.makeText(this, "Copied!", Toast.LENGTH_SHORT).show()
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp()
    }
}
