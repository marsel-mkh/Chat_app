package com.example.pozischatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText email_reg;
    private EditText password_reg;
    private Button btn_reg;
    private TextView registory_reg;

    private FirebaseAuth mAuthh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_reg = findViewById(R.id.email_reg);
        password_reg = findViewById(R.id.password_reg);
        btn_reg = findViewById(R.id.btn_reg);

        mAuthh = FirebaseAuth.getInstance();

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email_reg.getText().toString().isEmpty() || password_reg.getText().toString().isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,"Одно из полей пустое",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuthh.createUserWithEmailAndPassword(email_reg.getText().toString(), password_reg.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this,"Ошибка регистрации",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}