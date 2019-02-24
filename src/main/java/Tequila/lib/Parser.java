package Tequila.lib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import Tequila.lib.FileHandler;
import Tequila.entity.*;

public class Parser {
    private static final String BUKU_FILE_PATH = "./csv/buku.csv";
    private static final String PENULIS_FILE_PATH = "./csv/penulis.csv";
    private static final String MENULIS_FILE_PATH = "./csv/menulis.csv";
    private static final FileHandler fileHandler = new FileHandler();

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
                    table1.bookRecords = fileHandler.readBuku(BUKU_FILE_PATH);
                } else if (fromStatement.get(0).equals("PENULIS")) {
                    Parser.penulisAlias = fromStatement.get(1);
                    table1.penulisRecord = fileHandler.readPenulis(PENULIS_FILE_PATH);
                } else {
                    Parser.error = true;
                    System.out.println("ERROR: UNKNOWN entity of "+fromStatement.get(0));
                    return new Table();
                }
            } else if (fromStatement.size() == 1) {
                if (fromStatement.get(0).equals("BUKU")) {
                    table1.bookRecords = fileHandler.readBuku(BUKU_FILE_PATH);
                } else if (fromStatement.get(0).equals("PENULIS")) {
                    table1.penulisRecord = fileHandler.readPenulis(PENULIS_FILE_PATH);
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

            if (naturalPointer == -1) {
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
        Table res = from;

        if (select.attribute.size() == 1 && select.attribute.get(0).equals("*")) {
            try {
                Field[] bukuFields = Buku.class.getFields();
                for (int i = 0; i < bukuFields.length; i++) {
                    String[] formattedField = bukuFields[i].toString().split("\\.");
                    String attribute = formattedField[formattedField.length - 1];
                    res.bookDisplayedAttribute.add(attribute);
                }

                Field[] penulisFields = Penulis.class.getFields();
                for (int i = 0; i < penulisFields.length; i++) {
                    String[] formattedField = penulisFields[i].toString().split("\\.");
                    String attribute = formattedField[formattedField.length - 1];
                    res.penulisDisplayedAtrribute.add(attribute);
                }

                return res;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        for (int i = 0; i < select.attribute.size(); i++) {
            if (select.attribute.get(i).endsWith(",")) {
                select.attribute.set(i, select.attribute.get(i).replace(",", ""));
            }
            if (select.attribute.get(i).contains(".")) {
                String[] parsedAttribute = select.attribute.get(i).split("\\.");
                if (parsedAttribute[0].equals(Parser.bookAlias)) {
                    try {
                        if (Buku.class.getField(parsedAttribute[1].toLowerCase()) != null) {
                            res.bookDisplayedAttribute.add(parsedAttribute[1].toLowerCase());
                        }
                    } catch (Exception e) {
                        Parser.error = true;
                        System.out.println(e);
                        return new Table();
                    }
                } else if (parsedAttribute[0].equals(Parser.penulisAlias)) {
                    try {
                        if (Penulis.class.getField(parsedAttribute[1].toLowerCase()) != null) {
                            res.penulisDisplayedAtrribute.add(parsedAttribute[1].toLowerCase());
                        }
                    } catch (Exception e) {
                        Parser.error = true;
                        System.out.println(e);
                        return new Table();
                    }
                } else {
                    Parser.error = true;
                    System.out.println("Error Unknow Alias of "+parsedAttribute[1]);
                    return new Table();
                }
            } else {
                String parsedAttribute = select.attribute.get(i);
                try {
                    if (Buku.class.getField(parsedAttribute.toLowerCase()) != null) {
                        res.bookDisplayedAttribute.add(parsedAttribute.toLowerCase());
                    }
                } catch (Exception e ) {
                    try {
                        if (Penulis.class.getField(parsedAttribute.toLowerCase()) != null) {
                            res.penulisDisplayedAtrribute.add(parsedAttribute.toLowerCase());
                        }
                    } catch (Exception error) {
                        Parser.error = true;
                        System.out.println(e);
                        return new Table();
                    }
                }
            }
        }

        return res;
    }

    public static Table parse(Table from, String join) {
        Table res = new Table();
        return new Table();
    }
}