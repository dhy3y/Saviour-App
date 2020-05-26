package com.example.hackathon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class SignUp extends AppCompatActivity {
    TextInputLayout fname,lname,eml,pswd,cnfrmpswd;
    Button signupbtn,login;
    RadioButton gender_male,gender_female,gender_others;
    private FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    FirebaseDatabase rootnode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupbtn = findViewById(R.id.nextbuttonnon);
        login = findViewById(R.id.loginsignupnon);
        fname = findViewById(R.id.firstnamelt);
        lname = findViewById(R.id.lastnamenlt);
        eml = findViewById(R.id.emailaddressnlt);
        pswd = findViewById(R.id.passwordsignuplt);
        cnfrmpswd = findViewById(R.id.cnfrmpasswordlt);
        gender_male = findViewById(R.id.Rb_male);
        gender_female = findViewById(R.id.Rb_female);
        gender_others = findViewById(R.id.Rb_others);
        firebaseAuth = FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("NewUsers");

                String passwordVal = //"^" +
                        //"(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        //"(?=.*[a-zA-Z])" +      //any letter
                        //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                        //"(?=\\S+$)" +           //no white spaces
                        // "$" +
                        ".{6,}" ;
                String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9]+\\.+[a-z]";
                String Gender = "";
                final String Firstname = fname.getEditText().getText().toString().trim();
                final String Lastname = lname.getEditText().getText().toString().trim();
                final String Emailidd = eml.getEditText().getText().toString().trim();
                final String Password = pswd.getEditText().getText().toString().trim();
                final String confirmpassword = cnfrmpswd.getEditText().getText().toString().trim();

                if(Firstname.isEmpty())
                      fname.setError("First name Can't be Empty");
                if(Lastname.isEmpty())
                    lname.setError("Last Name can't be Empty");
                if(Emailidd.isEmpty())
                    eml.setError("Email Address can't be Empty");
                if(Password.isEmpty())
                    pswd.setError("Password can't be Empty");
                if(!Password.matches(passwordVal))
                    pswd.setError("Please enter a valid password");
                 if(confirmpassword.isEmpty())
                     cnfrmpswd.setError("Please Enter the Confirm Password");
                 if(!confirmpassword.equals(Password))
                      cnfrmpswd.setError("Confirm Password doesn't Match");
                  if(gender_male.isChecked())
                      Gender = "Male";
                  if(gender_female.isChecked())
                      Gender = "Female";
                  if(gender_others.isChecked())
                      Gender = "Others";
                 if(!Firstname.isEmpty() && !Lastname.isEmpty() && !Emailidd.isEmpty()  && !Password.isEmpty() && !confirmpassword.isEmpty() && Password.matches(passwordVal) && confirmpassword.equals(Password) && Gender != "")
                 {
                     final ACProgressFlower dialog = new ACProgressFlower.Builder(SignUp.this)
                             .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                             .themeColor(Color.WHITE)
                             .text("Creating Account")
                             .fadeColor(Color.DKGRAY).build();
                     dialog.show();
                     final String finalGender = Gender;
                     firebaseAuth.createUserWithEmailAndPassword(Emailidd, Password)
                             .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {

                                     if (task.isSuccessful()) {
                                         dialog.dismiss();
                                         FirebaseUser fuser = firebaseAuth.getCurrentUser();
                                         fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                             @Override
                                             public void onSuccess(Void aVoid) {
                                                 Toast.makeText(SignUp.this,"Verification mail Has been sent to:"+Emailidd,Toast.LENGTH_SHORT).show();

                                             }
                                         }).addOnFailureListener(new OnFailureListener() {
                                             @Override
                                             public void onFailure(@NonNull Exception e) {
                                                 Log.d("TAG","onFailure:Email not sent " +e.getMessage());
                                             }
                                         });

                                       User info = new User(Firstname,Lastname,Emailidd,Password);
                                         FirebaseDatabase.getInstance().getReference("NewUsers")
                                                 .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                 .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                             @Override
                                             public void onComplete(@NonNull Task<Void> task) {
                                                 Toast.makeText(SignUp.this,"Registration Successful",Toast.LENGTH_LONG).show();
                                             }
                                         });
                                         startActivity(new Intent(getApplicationContext(), LoginActiivity.class));
                                         Toast.makeText(SignUp.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                     }
                                     else
                                     {    dialog.dismiss();
                                         if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                         {
                                             Toast.makeText(SignUp.this, "You are already registered! Please SignIn", Toast.LENGTH_LONG).show();
                                         }
                                         else
                                         {
                                             Toast.makeText(SignUp.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                         }
                                     }
                                 }
                             });
                 }
            }
        });
    }

    public void openLogin(View view) {
        Intent intent = new  Intent(SignUp.this,LoginActiivity.class);
        startActivity(intent);
    }
}
