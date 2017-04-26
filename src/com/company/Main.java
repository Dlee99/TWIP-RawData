package com.company;

import javafx.scene.control.cell.TextFieldTableCell;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Main {
    public static ArrayList<Student> list = new ArrayList<>();

    public static void main(String[] args) throws java.io.IOException {

        Scanner sc = new Scanner(new File("data.txt"));

        sc.nextLine();
        while (sc.hasNextLine()) {
            try {
                int num = sc.nextInt();

                if (getStudent(num) == null) {
                    list.add(new Student(num));
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
            }
        }
        Scanner sc2 = new Scanner(new File("data.txt"));
        sc2.nextLine();
        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();
            Scanner sc3 = new Scanner(line);
            int id = sc3.nextInt();
            sc3.useDelimiter("\\t");
            double frq1 = 0;
            double frq2 = 0;
            int fr = 1;
            while (sc3.hasNext()) {

                String portion = sc3.next();
                //System.out.println(portion.replaceAll("[^0123456789\\+]", ""));
                if (portion.replaceAll("[^0123456789\\+]", "").matches("\\+.*\\d")){
                    portion = RNNTC(portion);
                    portion = portion.replaceAll("[^0123456789.\\s]", "");
                    //System.out.println(portion);
                    Scanner sc4 = new Scanner(portion);
                    while (sc4.hasNextDouble()) {
                        double number = sc4.nextDouble();
                        if(number != 3) {
                            if (fr == 1) {
                                frq1 += number;
                            } else {
                                frq2 += number;
                            }
                        }
                    }
                } else if (!portion.isEmpty()) {

                    if ((portion.replaceAll("[0123456789]", "").isEmpty())) {
                        if (fr == 1) {
                            frq1 -= (Integer.parseInt(portion)) * .25;
                            fr++;
                        } else {

                            frq2 -= (Integer.parseInt(portion)) * .25;
                            break;
                        }
                    }
                }
            }
            int l = 0;
            int i = 0;
            while (i < list.size()) {
                if (list.get(i).secret == id) {
                    l = i;
                }
                i++;
            }
            Student x = list.get(l);
            x.r1(frq1);
            x.r2(frq2);
            list.set(l, x);
        }
        setNames();

        for (int i = 0; i < list.size(); i++) {
            System.out.format("%15s%7s%7s%14s", "Student", "FR #1", "FR #2", "Total");
            String name = list.get(i).name;
            String frq1 = "" + list.get(i).frq1;
            String frq2 = "" + list.get(i).frq2;
            String total = "" + (list.get(i).frq1 + list.get(i).frq2);
            System.out.println();
            System.out.format("%15s%7s%7s%14s", name, frq1, frq2, total);
           /*
           System.out.println("Student: " + list.get(i).name);

           System.out.println("Average FR 1: " + list.get(i).frq1);
           System.out.println("Average FR 2: " + list.get(i).frq2);
           System.out.println("Total Average: " + (list.get(i).frq1 + list.get(i).frq2) + "/19");
           */
            System.out.println();
            System.out.println();
        }


    }

    public static Student getStudent(int n) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).secret == n) {
                return list.get(i);
            }
            i++;
        }
        return null;
    }

    public static void setNames() throws java.io.IOException {
        Scanner sc = new Scanner(new File("names.txt"));
        while (sc.hasNextLine()) {

            int num = sc.nextInt();
            getStudent(num).setName(sc.next());
        }
    }

    public static String RNNTC(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + 1).replaceAll("[a-zA-Z]", "").isEmpty()) {
                try {
                    s = s.substring(0, i) + s.substring(i + 2, s.length());
                }
                catch(java.lang.StringIndexOutOfBoundsException e){
                }
            }
        }

        return s;
    }
}

