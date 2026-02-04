package com.example.wazeproject_dan;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isLoggedIn) {
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        // דוגמה: לחיצה על כפתור הצטרפות
        findViewById(R.id.joinBtn).setOnClickListener(v -> {
            String code = "A1B2C3"; // כאן תביא את הקוד מה-EditText
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            joinGroupByCode(code, userId); // קריאה לפונקציה

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}