package shivhare.ayush.demofirebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity_custom_intent extends Activity {
    EditText eEmail, ePassword;
    Button bSignin;
    TextView tSignup;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_custom_intent);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), user_profile.class);
            startActivity(intent);

        }
        progressDialog = new ProgressDialog(this);
        eEmail = findViewById(R.id.email_intent_cumtom);
        ePassword = findViewById(R.id.password_intent_custom);
        bSignin = findViewById(R.id.signin_intent_button);
        tSignup = findViewById(R.id.signup_intent_button);

        bSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userlogin();
            }
        });
        tSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(getApplicationContext(), LoginActivity_custom.class);
                startActivity(i);
            }
        });

    }

    public void userlogin() {
        final String email = eEmail.getText().toString().trim();
        String password = ePassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            eEmail.setError("please enter the email");
        }
        if (TextUtils.isEmpty(password)) {

            ePassword.setError("please enter the password");
        }
        // if all are okk then only we can show the progress dialog
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        //here we call the intent activity
                        eEmail.setText(null);
                        ePassword.setText(null);
                        Intent intent = new Intent(getApplicationContext(), user_profile.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "you are not the valid user", Toast.LENGTH_LONG).show();
                        eEmail.setText(null);
                        ePassword.setText(null);
                    }
                }
            });
        }
    }
}
