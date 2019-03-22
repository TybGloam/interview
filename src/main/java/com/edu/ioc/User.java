package com.edu.ioc;

/**
 *
 * Created by zhangxuan on 2019/3/22.
 */
public class User {

    private String firstName;

    private String lastName;

    private int age;

    private String company;

    private String job;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    public User(String firstName, String lastName, int age, String company, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.company = company;
        this.job = job;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
