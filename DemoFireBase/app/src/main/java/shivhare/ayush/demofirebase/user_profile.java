package shivhare.ayush.demofirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class user_profile extends Activity {
    TextView textwelcome;
    EditText ename,eprofession,ecompany,esalary;

    Button signout,save,view;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        textwelcome=findViewById(R.id.welcome);
        signout=findViewById(R.id.logout);
        ecompany=findViewById(R.id.eCompany_profile);
        ename=findViewById(R.id.eName_profile);
        eprofession=findViewById(R.id.eProfession_profile);
        esalary=findViewById(R.id.eSalary_profile);
        save=findViewById(R.id.bSave_profile);
        view=findViewById(R.id.bView_profile);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity_custom_intent.class));
        }
        final FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
        textwelcome.setText("welcome "+firebaseUser.getEmail());
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity_custom_intent.class));
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                String ui=user.getUid();
                String name=ename.getText().toString();
                String profession=eprofession.getText().toString();
                String company=ecompany.getText().toString();
                String salary=esalary.getText().toString();
                databaseReference.child(ui).setValue(new UserInformation(name,profession,company,
                        salary));
                Toast.makeText(getApplicationContext(),"User Information has been saved..",Toast
                        .LENGTH_LONG).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserInformation_Show.class));

            }
        });
    }
}
