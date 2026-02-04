package com.example.wazeproject_dan;

import java.util.ArrayList;
import java.util.List;

public class AccountInfo {
    public String first_name, last_name, display_name, email;
    public List<String> my_groups; // רשימה של ה-ID של הקבוצות שלי
    public AccountInfo() {
        my_groups = new ArrayList<>(); // אתחול הרשימה כדי שלא תהיה Null
    }
}
