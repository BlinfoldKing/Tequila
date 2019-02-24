package Tequila;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import Tequila.lib.Parser;

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
        Parser.parse(query);
        while(!query.equals("exit") && !Parser.error) {
            System.out.println("output: "+query.length());
            query = getInput();
            Parser.parse(query);
        } 
    }
}