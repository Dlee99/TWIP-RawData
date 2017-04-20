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
        Scanner sc2 = new Scanner(new File("data.txt"));
        sc2.nextLine();
        while(sc2.hasNextLine()){
            String line = sc2.nextLine();
            Scanner sc3 = new Scanner(line);
            int id = sc3.nextInt();
            sc3.useDelimiter("\\t");
            double frq1 = 0;
            double frq2 = 0;
            while(sc3.hasNext()){
                int fr = 1;
                String portion = sc3.next();
                if(portion.contains("\"")){
                    portion = portion.replaceAll("[^0123456789.\\s]", "");
                    Scanner sc4 = new Scanner(portion);
                    while(sc4.hasNextDouble()){
                        if(fr == 1) {
                            frq1 += sc4.nextDouble();
                        }
                        else{
                            frq2 += sc4.nextDouble();
                        }
                    }
                }
                else if(!portion.isEmpty()){

                    if((portion.replaceAll("[0123456789]", "").isEmpty())){
                        if(fr == 1){
                            frq1 -= (Integer.parseInt(portion)) * .25;
                            fr++;
                        }
                        else{

                            frq2 -= (Integer.parseInt(portion)) * .25;
                            break;
                        }
                    }
                }
            }
            int l = 0;
            int i = 0;
            while(i < list.size()){
                if(list.get(i).secret == id){
                    l = i;
                }
                i++;
            }
            list.get(l).r1(frq1);
            list.get(l).r2(frq2);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).frq1);
            System.out.println(list.get(i).frq2);
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
