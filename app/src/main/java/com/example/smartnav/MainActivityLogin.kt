package com.example.smartnav

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivityLogin : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        enableEdgeToEdge()
        setContentView(R.layout.activity_main);

        auth= FirebaseAuth.getInstance()
        button=findViewById(R.id.btn_logout)
        textView=findViewById(R.id.user_details)
        val user = auth.currentUser
        if(user==null){
            val intent = Intent(applicationContext,Register::class.java)
            startActivity(intent)
            finish()
        }
        else{
            textView.setText(user.email)
        }

        button.setOnClickListener(){
            FirebaseAuth.getInstance().signOut() //Sign-out the user front the firebase
            val intent = Intent(applicationContext,Login::class.java)
            startActivity(intent)
            finish()
        }
    }
}