package com.company;

import java.io.File;
import java.util.*;

public class Main {
    public static ArrayList<Student>  list = new ArrayList<>();
    public static void main(String[] args) throws java.io.IOException{
        Scanner sc = new Scanner(new File("data.txt"));
        sc.nextLine();
        while(sc.hasNextLine()){
            try {
                int num = sc.nextInt();

                if (getStudent(num) == null) {
                    list.add(new Student(num));
                }
            }
            catch(InputMismatchException e){
                sc.nextLine();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).secret);
        }

    }
    public static Student getStudent(int n){
        int i = 0;
        while(i < list.size()){
            if(list.get(i).secret == n){
                return list.get(i);
            }
            i++;
        }
        return null;
    }
    public static void setNames() throws java.io.IOException{
        Scanner sc = new Scanner(new File("names.txt"));
        while(sc.hasNextLine()){
            int num = sc.nextInt();
            getStudent(num).setName(sc.next());
        }
    }
}
