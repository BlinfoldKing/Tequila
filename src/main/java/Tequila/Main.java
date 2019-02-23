package Tequila;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    
    static String getInput() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.print("["+sdf.format(cal.getTime())+"]$ ");
        return input.nextLine();
    }

    public static void main(String[] args) {
        String query = getInput();
        while(!query.equals("exit")) {
            System.out.println("output: "+query.length());
            query = getInput();
        } 
    }
}