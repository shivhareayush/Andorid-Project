package shivhare.ayush.demofirebase;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserInformation_Show extends Activity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<String> arrayList_key=new ArrayList<>();
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information__show);
        listView=findViewById(R.id.listview_profile);
        arrayAdapter=new ArrayAdapter(this,R.layout.list_content,R.id.cont ,arrayList);
        firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference=firebaseDatabase.getReference().child(user.getUid());
        /* databaseReference.addChildEventListener(new ChildEventListener() {
             @Override
             public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                 String s1=(String)dataSnapshot.getValue();
                 arrayList_key.add(dataSnapshot.getKey());
                 arrayList.add(s1);

                 arrayAdapter.notifyDataSetChanged();
             }

             @Override
             public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String s1=(String)dataSnapshot.getValue();
                String s2=(String)dataSnapshot.getKey();
               int i= arrayList_key.indexOf(s2);
               arrayList.set(i,s1);
             }

             @Override
             public void onChildRemoved(DataSnapshot dataSnapshot) {

             }

             @Override
             public void onChildMoved(DataSnapshot dataSnapshot, String s) {

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });*/
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i=1;
                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                for(DataSnapshot dataSnapshot1:iterable){
                     String s1=dataSnapshot1.getKey()+" : "+dataSnapshot1.getValue(String.class);
                     arrayList.add(s1);
                     arrayAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setAdapter(arrayAdapter);
    }
}
