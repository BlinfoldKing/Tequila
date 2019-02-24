package Tequila.lib;

import java.lang.reflect.Field;
import java.util.Arrays;
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
    public static String menulisAlias = null;
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
           
            int joinPointer = -1;
            List<String> fromStatement = new ArrayList<String>();
            for (
                int i = fromPointer + 1; 
                i < formattedCommand.length; 
                i++
            ) {
                if (formattedCommand[i].equals("JOIN")) {
                    joinPointer = i;
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
                } else if (fromStatement.get(0).equals("MENULIS")) {
                    Parser.menulisAlias = fromStatement.get(1);
                    table1.menulisRecord = fileHandler.readMenulis(MENULIS_FILE_PATH);
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
                } else if(fromStatement.get(0).equals("MENULIS")) {
                    table1.menulisRecord = fileHandler.readMenulis(MENULIS_FILE_PATH);
                }else {
                    Parser.error = true;
                    System.out.println("ERROR: UNKNOWN entity of "+fromStatement.get(0));
                    return new Table();
                }
            } else {
                Parser.error = true;
                System.out.println("ERROR: Missing entity statement");
                return new Table();
            }

            if (joinPointer == -1) {
                return parse(argument, table1);
            } else {
                Table joinTable = new Table();
                if (formattedCommand[joinPointer + 1].equals("BUKU")) {
                    joinTable.bookRecords = fileHandler.readBuku(BUKU_FILE_PATH);
                } else if (formattedCommand[joinPointer + 1].equals("PENULIS")) {
                    joinTable.penulisRecord = fileHandler.readPenulis(PENULIS_FILE_PATH);
                } else if (formattedCommand[joinPointer + 1].equals("MENULIS")) {
                    joinTable.menulisRecord = fileHandler.readMenulis(MENULIS_FILE_PATH);
                } else {
                    Parser.error = true;
                    System.out.println("ERROR: UNKNOWN entity of "+fromStatement.get(0));
                    return new Table();
                }

                if (joinPointer + 2 <= formattedCommand.length - 1) {
                    if (formattedCommand[joinPointer + 2].equals("JOIN")) {
                        String[] nextStatement = Arrays.copyOfRange(formattedCommand, joinPointer + 3, formattedCommand.length);
                        return parse(argument, Parser.join(table1, Parser.parse(joinTable, nextStatement)));
                    } else {

                    }
                } else {
                    return parse(argument, Parser.join(table1, joinTable));
                }
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

                Field[] menuFields = Menulis.class.getFields();
                for (int i = 0; i < menuFields.length; i++) {
                    String[] formattedField = menuFields[i].toString().split("\\.");
                    String attribute = formattedField[formattedField.length - 1];
                    res.menulisDisplayedAttribute.add(attribute);
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
                } else if (parsedAttribute[0].equals(Parser.menulisAlias)) {
                    try {
                        if (Menulis.class.getField(parsedAttribute[1].toLowerCase()) != null) {
                            res.menulisDisplayedAttribute.add(parsedAttribute[1].toLowerCase());
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
                Boolean isFound = false;
                Exception err = null;
                try {
                    if (Buku.class.getField(parsedAttribute.toLowerCase()) != null 
                        && res.bookRecords != null) {
                        res.bookDisplayedAttribute.add(parsedAttribute.toLowerCase());
                        isFound = true;
                    }
                } catch (Exception e) {
                    err = e;
                }
                try {
                    if (Penulis.class.getField(parsedAttribute.toLowerCase()) != null
                        && res.penulisRecord != null) {
                        res.penulisDisplayedAtrribute.add(parsedAttribute.toLowerCase());
                        isFound = true;
                    }
                } catch (Exception e) {
                    err = e;
                }
                try {
                    if(Menulis.class.getField(parsedAttribute.toLowerCase()) != null
                        && res.menulisRecord != null) {
                        res.menulisDisplayedAttribute.add(parsedAttribute.toLowerCase());
                        isFound = true;
                    }
                } catch (Exception e) {
                    err = e;
                }

                if (!isFound) {
                    Parser.error = true;
                    System.out.println(err);
                    return new Table();
                }
            }
        }

        return res;
    }

    public static Table join(Table lhs, Table rhs) {
        Table result = lhs;
        if (rhs.bookRecords != null) {
            result.bookRecords = rhs.bookRecords;
        } 

        if (rhs.penulisRecord != null) {
            result.penulisRecord = rhs.penulisRecord;
        }

        if (rhs.menulisRecord != null) {
            result.menulisRecord = rhs.menulisRecord;
        }
        
        return result;
    }

    public static Table parse(Table from, String[] join) {
        Table res = new Table();
        return new Table();
    }
}