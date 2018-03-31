package com.example.android.xpenseauditor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputName;
    private EditText inputPhnnum;


    private DatabaseReference mRootRef;
    private DatabaseReference RefUid;
    private DatabaseReference RefName, RefDetails;
    private DatabaseReference RefEmail;
    private DatabaseReference RefPhnnum;
    private DatabaseReference RefCat, RefFood, RefHealth, RefTravel, RefEdu, RefBills, RefHomeNeeds, RefOthers, RefUncat, RefAddress;


    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputName = (EditText) findViewById(R.id.Name);
        inputPhnnum = (EditText) findViewById(R.id.phnum);


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                String password = "defaultpw9";

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

               /* if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {


                                    auth.sendPasswordResetEmail(email)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(SignupActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(SignupActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                                    }

                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            });

                                    String Uid = auth.getUid();
                                    String name = inputName.getText().toString().trim();
                                    String Phnnum = inputPhnnum.getText().toString().trim();

                                    RefUid = mRootRef.child(Uid);
                                    Toast.makeText(SignupActivity.this, "add:" + RefUid, Toast.LENGTH_LONG).show();
                                    RefDetails = RefUid.child("Details");
                                    RefName = RefDetails.child("Name");
                                    RefName.setValue(name);
                                    RefEmail = RefDetails.child("Email");
                                    RefEmail.setValue(email);
                                    RefPhnnum = RefDetails.child("Phone Number");
                                    RefPhnnum.setValue(Phnnum);
                                    Toast.makeText(SignupActivity.this, "add:" + RefUid + "name:" + name, Toast.LENGTH_LONG).show();
                                    RefCat = RefUid.child("Categories");
                                    RefFood = RefCat.child("Food");
                                    RefFood.setValue("");
                                    RefHealth = RefCat.child("Health");
                                    RefHealth.setValue("");
                                    RefTravel = RefCat.child("Travel");
                                    RefTravel.setValue("");
                                    RefEdu = RefCat.child("Education");
                                    RefEdu.setValue("");
                                    RefBills = RefCat.child("Bills");
                                    RefBills.setValue("");
                                    RefHomeNeeds = RefCat.child("Home Needs");
                                    RefHomeNeeds.setValue("");
                                    RefOthers = RefCat.child("Others");
                                    RefOthers.setValue("");
                                    RefUncat = RefCat.child("Uncategorised");
                                    RefUncat.setValue("");


                                    auth.signOut();
                                    startActivity(new Intent(SignupActivity.this, setpw.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}