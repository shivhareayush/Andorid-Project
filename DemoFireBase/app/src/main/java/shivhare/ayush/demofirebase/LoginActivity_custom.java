package shivhare.ayush.demofirebase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity_custom extends Activity {
    EditText eEmail, ePassword;
    Button bSignup;
    TextView tSignin;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_custom);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        eEmail = findViewById(R.id.email_cumtom);
        ePassword = findViewById(R.id.password_custom);
        bSignup = findViewById(R.id.signup_button);
        tSignin = findViewById(R.id.signin_button);
        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        tSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity_custom_intent.class);
                startActivity(intent);
            }
        });

    }

    public void register() {
        String email = eEmail.getText().toString().trim();
        String password = ePassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            eEmail.setError("please enter the email");
        }
        if (TextUtils.isEmpty(password)) {

            ePassword.setError("please enter the password");
        }
        // if all are okk then only we can show the progress dialog
       if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            progressDialog.setMessage("Registering user...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener
                    (this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //user has been register successfully
                                //we will do panding task
                                eEmail.setText(null);
                                ePassword.setText(null);
                                Toast.makeText(getApplicationContext(), "user register successfully",
                                        Toast.LENGTH_LONG).show();
                                progressDialog.cancel();
                            } else {
                                eEmail.setText(null);
                                ePassword.setText(null);
                                Toast.makeText(getApplicationContext(), "some error occured", Toast
                                        .LENGTH_LONG).show();
                                progressDialog.cancel();
                            }
                            if (task.isCanceled()) {
                                eEmail.setText(null);
                                ePassword.setText(null);
                                Toast.makeText(getApplicationContext(), "some error occured", Toast
                                        .LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
