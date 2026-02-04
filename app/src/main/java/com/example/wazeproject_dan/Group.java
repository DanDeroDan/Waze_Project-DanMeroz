package com.example.wazeproject_dan;

public class Group {
    private String group_name;
    private String admin_id;
    private String invite_code;

    public Group() {} // חובה

    public Group(String group_name, String admin_id, String invite_code) {
        this.group_name = group_name;
        this.admin_id = admin_id;
        this.invite_code = invite_code;
    }

    // Getters & Setters
    public String getGroup_name() { return group_name; }
    public void setGroup_name(String group_name) { this.group_name = group_name; }

    public String getAdmin_id() { return admin_id; }
    public void setAdmin_id(String admin_id) { this.admin_id = admin_id; }

    public String getInvite_code() { return invite_code; }
    public void setInvite_code(String invite_code) { this.invite_code = invite_code; }
}
