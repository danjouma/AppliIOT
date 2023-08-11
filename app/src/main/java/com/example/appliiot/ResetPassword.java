package com.example.appliiot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    TextInputEditText newEditMail;
    Button butttonSend;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        newEditMail = findViewById(R.id.Newmail);
        butttonSend=findViewById(R.id.envoyer);
        butttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = newEditMail.getText().toString();
                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPassword.this, "E-mail de réinitialisation envoyé.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent (getApplicationContext(),Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(ResetPassword.this, "Erreur lors de l'envoi de l'e-mail de réinitialisation.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}