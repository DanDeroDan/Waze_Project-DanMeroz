package com.example.wazeproject_dan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {

    private EditText email, password, name, surname, age;
    private Button signinButton, loginButton;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        db = FirebaseFirestore.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        age = findViewById(R.id.age);
        signinButton = findViewById(R.id.signin_button);
        loginButton = findViewById(R.id.login_button);

        signinButton.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            String nameText = name.getText().toString();
            String surnameText = surname.getText().toString();
            String ageText = age.getText().toString();

            Map<String, Object> user = new HashMap<>();
            user.put("email", emailText);
            user.put("password", passwordText);
            user.put("name", nameText);
            user.put("surname", surnameText);
            user.put("age", ageText);

            db.collection("users").document(emailText).set(user);
        });

        loginButton.setOnClickListener(v -> {
            startActivity(new Intent(SigninActivity.this, login.class));
        });
    }
}
