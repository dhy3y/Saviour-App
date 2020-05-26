package com.example.hackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassActivity extends AppCompatActivity {

    Button resetpass;
    FirebaseAuth firebaseAuth;
    TextInputLayout resetml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        resetpass = findViewById(R.id.resetButton);
        resetml = findViewById(R.id.emailrst);

        firebaseAuth = FirebaseAuth.getInstance();
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                final String ResetMail = resetml.getEditText().getText().toString().trim();
                Log.d("myTag", "emailIs : " + ResetMail);
                if(ResetMail.isEmpty())
                {
                    Toast.makeText(ResetPassActivity.this,"Please Enter your mail address",Toast.LENGTH_LONG).show();
                }
                else if(!ResetMail.matches(emailPattern))
                {
                    Toast.makeText(ResetPassActivity.this,"Please Use VIT Bhopal mail address only",Toast.LENGTH_LONG).show();
                }
                else if(ResetMail.matches(emailPattern))
                {   final ACProgressFlower dialog = new ACProgressFlower.Builder(ResetPassActivity.this)
                        .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                        .themeColor(Color.WHITE)
                        .text("sending Reset link:")
                        .fadeColor(Color.DKGRAY).build();
                    dialog.show();
                    firebaseAuth.sendPasswordResetEmail(ResetMail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                dialog.dismiss();
                                Toast.makeText(ResetPassActivity.this,"Reset Password Link Has Been Sent to:"+ResetMail,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ResetPassActivity.this,LoginActiivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(ResetPassActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }

            }
        });
    }
}
