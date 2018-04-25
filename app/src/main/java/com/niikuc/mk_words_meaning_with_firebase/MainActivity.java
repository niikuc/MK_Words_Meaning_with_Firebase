package com.niikuc.mk_words_meaning_with_firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_word,et_des_word;
    Button b_word;

    DatabaseReference dbWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        b_word=(Button) findViewById(R.id.bt_word);
        et_word=(EditText) findViewById(R.id.et_word);
        et_des_word=(EditText) findViewById(R.id.et_desc_word);

        dbWord = FirebaseDatabase.getInstance().getReference("word");



        // Write a message to the database


        final List<MacWord> zborovi=new ArrayList<MacWord>();

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child:children) {
                    MacWord macWord = child.getValue(MacWord.class);
                    zborovi.add(macWord);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        b_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MacWord word=new MacWord();
                word.setWord(et_word.getText().toString());
                word.setDescription(et_des_word.getText().toString());

                dbWord.push().setValue(word);



            }
        });
        final ArrayAdapter<MacWord> wordAdapter = new ArrayAdapter<MacWord>(this, android.R.layout.simple_list_item_1, zborovi);
        final ListView list = (ListView) findViewById(R.id.list);

        Button bt=(Button) findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.setAdapter(wordAdapter);

            }
        });








    }
}
