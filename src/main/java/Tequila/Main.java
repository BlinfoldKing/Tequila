package Tequila;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import Tequila.lib.Table;
import Tequila.lib.Parser;
import Tequila.lib.Drawer;

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
        Table result = Parser.parse(query);
        while(!query.equals("exit")) {
            if (!Parser.error) {
                Drawer.generateTable(result);
            }
            query = getInput();
            result = Parser.parse(query);
        } 
    }
}