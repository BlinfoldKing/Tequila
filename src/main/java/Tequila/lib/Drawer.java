package Tequila.lib;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;

import Tequila.entity.Buku;
import Tequila.entity.Penulis;
import Tequila.lib.Table;

public class Drawer {
    public static void generateTable(Table table) {
        List<Integer> columnSize = new ArrayList<Integer>();
        // generate book column
        if (table.bookRecords != null) {
            for (int i = 0; i < table.bookDisplayedAttribute.size(); i++) {
                String currAttribute = table.bookDisplayedAttribute.get(i);
                try {
                    Field currField = Buku.class.getField(currAttribute);
                    Integer currWidth = currAttribute.length();
                    for (int j = 0; j < table.bookRecords.size() - 1; j++) {
                        String curr = currField.get(table.bookRecords.get(j)).toString();
                        if (curr.length() > currWidth) {
                            currWidth = curr.length();
                        }
                    }
                    columnSize.add(currWidth);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.print("| "+currAttribute);
                for (int j = 0; j < columnSize.get(i) - currAttribute.length() + 1; j++)
                    System.out.print(" ");
            }
        }

        // generate penulis column
        if (table.penulisRecord != null) {
            for (int i = 0; i < table.penulisDisplayedAtrribute.size(); i++) {
                String currAttribute = table.penulisDisplayedAtrribute.get(i);
                try {
                    Field currField = Penulis.class.getField(currAttribute);
                    Integer currWidth = currAttribute.length();
                    for (int j = 0; j < table.penulisRecord.size() - 1; j++) {
                        String curr = currField.get(table.penulisRecord.get(j)).toString();
                        if (curr.length() > currWidth) {
                            currWidth = curr.length();
                        }
                    }
                    columnSize.add(currWidth);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.print("| "+currAttribute);
                for (int j = 0; j < columnSize.get(i) - currAttribute.length() + 1; j++)
                    System.out.print(" ");
            }
        }

        System.out.println("|");
        for (int i = 0; i < columnSize.size(); i++) {
            for (int j = 0; j < columnSize.get(i) + 3; j++) {
                System.out.print("=");
            }
        }
        System.out.println("=");

        // listing value of buku
        if (table.bookRecords != null) {
            for (int i = 0; i < table.bookRecords.size(); i++) {
                for (int j = 0; j < table.bookDisplayedAttribute.size(); j++) {
                    String currAttribute = table.bookDisplayedAttribute.get(j);
                    try {
                        Field currField = Buku.class.getField(currAttribute);
                        String value = currField.get(table.bookRecords.get(i)).toString();
                        System.out.print("| "+value);
                        for (int k = 0; k < columnSize.get(j) - value.length() + 1; k++ ) {
                            System.out.print(" ");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                System.out.println("|");
            }
        }

        // listing value of penulis
        if (table.penulisRecord != null) {
            for (int i = 0; i < table.penulisRecord.size(); i++) {
                for (int j = 0; j < table.penulisDisplayedAtrribute.size(); j++) {
                    String currAttribute = table.penulisDisplayedAtrribute.get(j);
                    try {
                        Field currField = Penulis.class.getField(currAttribute);
                        String value = currField.get(table.penulisRecord.get(i)).toString();
                        System.out.print("| "+value);
                        for (int k = 0; k < columnSize.get(j) - value.length() + 1; k++ ) {
                            System.out.print(" ");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                System.out.println("|");
            }
        }
    }
}