package com.pojo;

public class Employee {

    private int empId;
    private String name;
    private String email;
    private String password;
    private String department;

    public Employee() {
        super();
    }

    public Employee(int empId, String name, String email,
                    String password, String department) {
        super();
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}