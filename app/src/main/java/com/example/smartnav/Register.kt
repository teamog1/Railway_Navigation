package com.example.smartnav

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonReg: Button;
    private lateinit var auth: FirebaseAuth;
    private lateinit var progressBar: ProgressBar;
    private lateinit var textView: TextView;

    //To check if it is already register or not?
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);

        textView.setOnClickListener {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish();
        }

        buttonReg = findViewById(R.id.btn_Register);
        buttonReg.setOnClickListener() {
            // Inside here, you have to read the text from the EditText.

            //Making Progress Bar Visible
            progressBar.visibility = View.VISIBLE


            val email: String = editTextEmail.text.toString()
            val password: String = editTextPassword.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@Register, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this@Register, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->

                    //On the completion of Task,
                    //Removing the progressBar Visibilty
                    progressBar.visibility = View.GONE


                    if (task.isSuccessful) {


                        //Show the Toast Message
                        Toast.makeText(
                            this@Register,
                            "Account Created",
                            Toast.LENGTH_SHORT,
                        ).show()

                        //OPEN the login Activity
                        val intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                        finish()

                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "createUserWithEmail:success")
                        //val user = auth.currentUser
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@Register,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        //updateUI(null)
                    }
                }
        }

    }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
}