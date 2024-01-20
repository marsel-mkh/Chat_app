package com.example.pozischatapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
//допилить пустые сообщения,сообщения других пользователей с другой стороны,скрытие пароля,добавление имени вместо почты

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private RelativeLayout activityMain;
    private ImageView loguot;
    private FirebaseListAdapter<Message> adapter;
    private FloatingActionButton sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        activityMain = findViewById(R.id.activityMain);
        sendBtn = findViewById(R.id.btnSendMessage);
        loguot = findViewById(R.id.logout);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textField = findViewById(R.id.messageField);
                if(textField.getText().toString() == "")
                    return;

                FirebaseDatabase.getInstance().getReference().push().setValue(
                        new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),textField.getText().toString()
                                    )
                );
                textField.setText("");
            }
        });
//выход из аккаунта
        loguot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        SendMessages();
    }

    public void SendMessages() {
        ListView listOfMessage = findViewById(R.id.list_ofMessages);

        adapter = new FirebaseListAdapter<Message>(this,Message.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView mess_user,mess_time;
                BubbleTextView mess_txt;
                mess_user = v.findViewById(R.id.message_user);
                mess_time = v.findViewById(R.id.message_time);
                mess_txt =  v.findViewById(R.id.message_text);

                mess_user.setText(model.getName());
                mess_txt.setText(model.getTextMessage());
                mess_time.setText(DateFormat.format("dd-MM-yyyy hh:mm",model.getMessageTime()));
            }
        };
        listOfMessage.setAdapter(adapter);
    }

}
