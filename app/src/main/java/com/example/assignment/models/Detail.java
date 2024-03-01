package com.example.assignment.models;

public class Detail {
    String emp_id;
    String emp_no;
    String emp_name;
    String emp_dob;
    String emp_role;

    public Detail() {
    }

    public Detail(String emp_id, String emp_no, String emp_name, String emp_dob, String emp_role) {
        this.emp_id = emp_id;
        this.emp_no = emp_no;
        this.emp_name = emp_name;
        this.emp_dob = emp_dob;
        this.emp_role = emp_role;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_dob() {
        return emp_dob;
    }

    public void setEmp_dob(String emp_dob) {
        this.emp_dob = emp_dob;
    }

    public String getEmp_role() {
        return emp_role;
    }

    public void setEmp_role(String emp_role) {
        this.emp_role = emp_role;
    }
}
