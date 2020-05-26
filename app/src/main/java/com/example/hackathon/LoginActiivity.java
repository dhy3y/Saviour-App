package com.example.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class LoginActiivity extends AppCompatActivity {
    Button signupbt,loginnn;
    TextInputEditText id ,pass;
    FirebaseAuth firebaseAuth;

    public void openReset(View view) {
        Intent a = new Intent(LoginActiivity.this,ResetPassActivity.class);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actiivity);
        signupbt = findViewById(R.id.signup);
        loginnn = findViewById(R.id.loginbtn);
        id = findViewById(R.id.emailaddresslg);
        pass = findViewById(R.id.passwordlg);
        signupbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(LoginActiivity.this,SignUp.class);
                startActivity(a);
            }
        });
        firebaseAuth =FirebaseAuth.getInstance();
        loginnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Emailid = id.getText().toString().trim();
                String Password = pass.getText().toString().trim();

                if(TextUtils.isEmpty(Emailid)){
                    Toast.makeText(LoginActiivity.this,"Please Enter Emailid",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    Toast.makeText(LoginActiivity.this,"Please Enter Password",Toast.LENGTH_LONG).show();
                    return;
                }
                final ACProgressFlower dialoglogin = new ACProgressFlower.Builder(LoginActiivity.this)
                        .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                        .themeColor(Color.WHITE)
                        .text("Logging In")
                        .fadeColor(Color.DKGRAY).build();
                dialoglogin.show();
                firebaseAuth.signInWithEmailAndPassword(Emailid, Password)
                        .addOnCompleteListener(LoginActiivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                        dialoglogin.dismiss();
                                        Toast.makeText(LoginActiivity.this, "Logged In successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new  Intent(LoginActiivity.this,valuedactivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else if(!firebaseAuth.getCurrentUser().isEmailVerified())
                                    {
                                        Toast.makeText(LoginActiivity.this, "Please Verify your mailid first", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {

                                    Toast.makeText(LoginActiivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                }
                                dialoglogin.dismiss();
                            }
                        });

            }
        });
    }
}
