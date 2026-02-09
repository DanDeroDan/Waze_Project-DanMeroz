package com.example.wazeproject_dan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static boolean isLoggedIn = false;

    // הגדרת משתני Firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. בדיקה אם המשתמש מחובר באמצעות Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            // אם לא מחובר, שלח למסך התחברות
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
            return;
        }

        // 2. אם הגענו לכאן, המשתמש מחובר - נטען את המסך
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // כאן בהמשך תוכל להוסיף OnClickListener לכפתורים שלך
    }

    // --- פונקציה להצטרפות לקבוצה (Join Group) ---
    public void joinGroupByCode(String inputCode) {
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("groups")
                .whereEqualTo("invite_code", inputCode)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        // מצאנו את הקבוצה
                        DocumentSnapshot groupDoc = queryDocumentSnapshots.getDocuments().get(0);
                        String groupId = groupDoc.getId();

                        // הוספת המשתמש לקבוצה
                        Map<String, Object> memberData = new HashMap<>();
                        memberData.put("role", "member");

                        db.collection("groups").document(groupId)
                                .collection("members").document(userId).set(memberData);

                        // עדכון רשימת הקבוצות אצל המשתמש
                        db.collection("users").document(userId)
                                .update("account_info.my_groups", FieldValue.arrayUnion(groupId))
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(MainActivity.this, "הצטרפת לקבוצה!", Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        Toast.makeText(MainActivity.this, "קוד לא תקין", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error joining group", e));
    }
}