package com.company;

public class Student {
    public final int secret;
    public Student(int n){
        secret = n;
    }
    public double frq1 = -1;
    public double frq2 = -1;
    public void r1(double n){
        if(frq1 != -1){
            frq1 = (frq1 + n) / 2;
        }
    }
    public void r2(double n){
        if(frq2 != -1){
            frq2 = (frq2 + n) / 2;
        }
    }
    public String name = "";
    public void setName(String n){
        name = n;
    }

}
