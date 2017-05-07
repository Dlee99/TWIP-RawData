package com.company;
import java.io.File;
import java.util.*;
public class Main {
    public static ArrayList<Student> list = new ArrayList<>();
    public static void main(String[] args) throws java.io.IOException {
        Scanner sc = new Scanner(new File("data.txt"));
        sc.nextLine();
        while (sc.hasNextLine()) {
            int num = sc.nextInt();
            if (getStudent(num) == null)
                list.add(new Student(num));
            sc.nextLine();
        }
        Scanner sc2 = new Scanner(new File("data.txt"));
        sc2.nextLine();
        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();
            Scanner sc3 = new Scanner(line);
            int id = sc3.nextInt(), fr = 1;
            sc3.useDelimiter("\\t");
            double frq1 = 0, frq2 = 0;
            while (sc3.hasNext()) {
                String portion = sc3.next();
                if (portion.replaceAll("[^0123456789\\+]", "").matches("\\+.*\\d")) {
                    portion = RNNTC(portion);
                    portion = portion.replaceAll("[^0123456789.\\s]", "");
                    Scanner sc4 = new Scanner(portion);
                    while (sc4.hasNextDouble()) {
                        double number = sc4.nextDouble();
                        if (number != 3) {
                            if (fr == 1)
                                frq1 += number;
                            else
                                frq2 += number;
                        }
                    }
                } else if (!portion.isEmpty()) {
                    if ((portion.replaceAll("[0123456789]", "").isEmpty())) {
                        if (fr == 1) {
                            frq1 -= (Integer.parseInt(portion)) * .25;
                            if(frq1 <= 0)
                                frq1 = 0;
                            fr++;
                        } else if(fr == 2){
                            frq2 -= (Integer.parseInt(portion)) * .25;
                            if(frq2 <= 0)
                                frq2 = 0;
                            break;
                        }
                    }
                }
            }
            list.get(id - 1).r1(frq1);
            list.get(id - 1).r2(frq2);
        }
        /////////setNames();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).printInfo();
            System.out.println();
        }
    }
    public static Student getStudent(int n) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).secret == n)
                return list.get(i);
            i++;
        }
        return null;
    }
    public static void setNames() throws java.io.IOException {
        Scanner sc = new Scanner(new File("names.txt"));
        while (sc.hasNextLine()) {
            String name = sc.next();
            getStudent(sc.nextInt()).setName(name);
            sc.nextLine();
        }
    }
    public static String RNNTC(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i, i + 1).replaceAll("[a-zA-Z]", "").isEmpty()) {
                try {
                    s = s.substring(0, i) + s.substring(i + 2, s.length());
                } catch (java.lang.StringIndexOutOfBoundsException e) {
                }
            }
        }
        return s;
    }
}