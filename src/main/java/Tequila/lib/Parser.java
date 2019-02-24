package Tequila.lib;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    
    public static String bookAlias = null;
    public static String penulisAlias = null;
    public static Boolean error = false;

    public static Table parse(String command) {
        bookAlias = null;
        penulisAlias = null;
        Parser.error = false;

        String[] formattedCommand = command.split(" ");
        for (int i = 0; i < formattedCommand.length; i++)
            formattedCommand[i] = formattedCommand[i].toUpperCase();
        
        if (formattedCommand[0].equals("SELECT")) {
            String lastStatement = formattedCommand[formattedCommand.length - 1];
            if (!lastStatement.endsWith(";")) {
                System.out.println("ERROR: Missing Semicolon at the end of statement");
                Parser.error = true;
                return new Table();
            }

            formattedCommand[formattedCommand.length - 1] = lastStatement.replace(";", "");

            int fromPointer = 1;
            Argument argument = new Argument();
            argument.attribute = new ArrayList<String>();
            for (int i = 1; !formattedCommand[i].equals("FROM"); i++) {
                fromPointer++;
                if (!formattedCommand[i].endsWith(",") && !formattedCommand[i + 1].equals("FROM")) {
                    Parser.error = true;
                    System.out.println("ERROR: Missing comma at "+formattedCommand[i]);
                    return new Table();
                }
                argument.attribute.add(formattedCommand[i]);
            }

            if (!formattedCommand[fromPointer].equals("FROM")) {
                Parser.error = true;
                System.out.println("Excpected FROM statement but found'"+formattedCommand[fromPointer-1]+"'instead");
                return new Table();
            }

            if (fromPointer + 1 >= formattedCommand.length) {
                Parser.error = true;
                System.out.println("ERROR: Missing statement after FROM");
                return new Table();
            }
           
            int naturalPointer = -1;
            List<String> fromStatement = new ArrayList<String>();
            for (
                int i = fromPointer + 1; 
                i < formattedCommand.length; 
                i++
            ) {
                if (formattedCommand[i].equals("NATURAL")) {
                    naturalPointer = i;
                    break;
                }
                fromStatement.add(formattedCommand[i]);
            }

            Table table1 = new Table();
            if (fromStatement.size() == 2) {
                if (fromStatement.get(0).equals("BUKU")) {
                    Parser.bookAlias = fromStatement.get(1);
                    // make table1
                } else if (fromStatement.get(0).equals("PENULIS")) {
                    Parser.penulisAlias = fromStatement.get(1);
                    // make table1
                } else {
                    Parser.error = true;
                    System.out.println("ERROR: UNKNOWN entity of "+fromStatement.get(0));
                    return new Table();
                }
            } else if (fromStatement.size() == 1) {
                if (fromStatement.get(0).equals("BUKU")) {
                    //make table 1
                } else if (fromStatement.get(0).equals("PENULIS")) {
                    // make table 1
                } else {
                    Parser.error = true;
                    System.out.println("ERROR: UNKNOWN entity of "+fromStatement.get(0));
                    return new Table();
                }
            } else {
                Parser.error = true;
                System.out.println("ERROR: Missing entity statement");
                return new Table();
            }

            if (naturalPointer != -1) {
                return parse(argument, table1);
            } else {
            
            }
        } else {
            Parser.error = true;
            System.out.println("ERROR: Unknow  statement of"+formattedCommand[0]);
            return new Table();
        }
        return new Table();
    }

    public static Table parse(Argument select, Table from) {
        Table res = new Table();
        return new Table();
    }

    public static Table parse(Table from, Table join) {
        Table res = new Table();
        return new Table();
    }
}