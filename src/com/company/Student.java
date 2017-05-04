package com.company;
public class Student {
    public final int secret;
    public Student(int n){
        secret = n;
    }
    public double frq1 = -1;
    public double frq2 = -1;
    public void r1(double n){
        if(frq1 != -1)
            frq1 = (frq1 + n) / 2;
        else
            frq1 = n;
    }
    public void r2(double n){
        if(frq2 != -1)
            frq2 = (frq2 + n) / 2;
        else
            frq2 = n;
    }
    public String name = "";
    public void setName(String n){
        name = n;
    }
    public String toString(){
        return name + "\n" + "Question 1:\t" + frq1 + "\n" + "Question 2:\t" + frq2;
    }
}