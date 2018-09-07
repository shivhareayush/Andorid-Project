package shivhare.ayush.demofirebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends Activity {
    EditText eName,eProfession,eSalary,eCompany;
    Button bSave,bView;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference().child("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bView=findViewById(R.id.bView);
        eName=findViewById(R.id.eName);
        eProfession=findViewById(R.id.eProfession);
        eSalary=findViewById(R.id.eSalary);
        eCompany=findViewById(R.id.eCompany);
        bSave=findViewById(R.id.bSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=eName.getText().toString();
                String s2=eProfession.getText().toString();
                String s3=eCompany.getText().toString();
                String s4=eSalary.getText().toString();
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("Name",s1);
                hashMap.put("Profession",s2);
                hashMap.put("Company",s3);
                hashMap.put("Salary",s4);
                databaseReference.setValue(hashMap);
                Toast.makeText(getApplicationContext(),"record inserted",Toast.LENGTH_LONG).show();
            eName.setText(null);
            eProfession.setText(null);
            eSalary.setText(null);
            eCompany.setText(null);

            }
        });
        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),View1.class);
                startActivity(i);
                }
        });

    }
}
