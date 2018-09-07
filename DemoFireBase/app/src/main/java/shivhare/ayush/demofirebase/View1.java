package shivhare.ayush.demofirebase;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;



public class View1 extends Activity {
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String > arrayListkey=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    public static class Post{
        public String name;
        public String profession;
        public String company_name;
        public String salary;

        public Post() {
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getName() {

            return name;
        }

        public String getProfession() {
            return profession;
        }

        public String getCompany_name() {
            return company_name;
        }

        public String getSalary() {
            return salary;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);
        listView=findViewById(R.id.listview);
        final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference()
                .child("user");

        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                Post post=new Post();;
                for(DataSnapshot ds:iterable){
                    post=ds.getValue(Post.class);
                    Toast.makeText(getApplicationContext(), ""+post.getClass(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        arrayAdapter=new ArrayAdapter(this,android.R.layout
                .simple_list_item_activated_1, arrayList);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String v=(String)dataSnapshot.getValue(String.class);
                arrayList.add(v);
                String key=dataSnapshot.getKey();
                arrayListkey.add(key);
                Toast.makeText(getApplicationContext(), ""+v,
                        Toast.LENGTH_LONG).show();
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               String v=dataSnapshot.getValue(String.class);
               String key=dataSnapshot.getKey();
                int index=arrayListkey.indexOf(key);
                arrayList.set(index,v);
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
        });

        listView.setAdapter(arrayAdapter);
    }
}
