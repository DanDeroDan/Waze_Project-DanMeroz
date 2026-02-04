package com.example.wazeproject_dan;

import java.util.ArrayList;
import java.util.List;

public class User {
    private AccountInfo account_info;
    private PersonalDetails personal_details;
    private UserPreferences preferences;

    public User() {}

    public User(AccountInfo account_info, PersonalDetails personal_details, UserPreferences preferences) {
        this.account_info = account_info;
        this.personal_details = personal_details;
        this.preferences = preferences;
    }

    public AccountInfo getAccount_info() { return account_info; }
    public void setAccount_info(AccountInfo account_info) { this.account_info = account_info; }

    public PersonalDetails getPersonal_details() { return personal_details; }
    public void setPersonal_details(PersonalDetails personal_details) { this.personal_details = personal_details; }

    public UserPreferences getPreferences() { return preferences; }
    public void setPreferences(UserPreferences preferences) { this.preferences = preferences; }

    // --- מחלקות פנימיות ---

    public static class AccountInfo {
        public String first_name, last_name, display_name, email;
        // הוספנו את רשימת ה-ID של הקבוצות שהמשתמש חבר בהן
        public List<String> my_groups;

        public AccountInfo() {
            this.my_groups = new ArrayList<>(); // אתחול כדי למנוע NullPointerException
        }
    }

    public static class PersonalDetails {
        public int age;
        public String phone, bio;
        public PersonalDetails() {}
    }

    public static class UserPreferences {
        public int font_size;
        public boolean dark_mode; // כפי שסיכמנו
        public UserPreferences() {}
    }
}