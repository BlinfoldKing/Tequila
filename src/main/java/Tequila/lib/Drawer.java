package Tequila.lib;

import java.lang.reflect.Field;
import java.util.List;

import javax.xml.bind.PrintConversionEvent;

import java.util.ArrayList;

import Tequila.entity.Buku;
import Tequila.entity.Penulis;
import Tequila.entity.Menulis;
import Tequila.lib.Table;

public class Drawer {
    public static void generateTable(Table table) {
        int n = 1;
        if (table.bookRecords != null) {
            System.out.println("Tabel ("+(n++)+") : Buku");
            System.out.print("List Kolom :");
            for (int i = 0; i < table.bookDisplayedAttribute.size(); i++) {
                System.out.print(table.bookDisplayedAttribute.get(i)+", ");
            }
            System.out.println();
        }

        if (table.penulisRecord != null) {
            System.out.println("Tabel ("+(n++)+") : Penulis");
            System.out.print("List Kolom :");
            for (int i = 0; i < table.penulisDisplayedAtrribute.size(); i++) {
                System.out.print(table.penulisDisplayedAtrribute.get(i)+", ");
            }
            System.out.println();
        }

        if (table.menulisRecord != null) {
            System.out.println("Tabel ("+(n++)+") : Menulis");
            System.out.print("List Kolom :");
            for (int i = 0; i < table.menulisDisplayedAttribute.size(); i++) {
                System.out.print(table.menulisDisplayedAttribute.get(i)+", ");
            }
            System.out.println();
        }
        // List<Integer> columnSize = new ArrayList<Integer>();
        // // generate book column
        // if (table.bookRecords != null) {
        //     for (int i = 0; i < table.bookDisplayedAttribute.size(); i++) {
        //         String currAttribute = table.bookDisplayedAttribute.get(i);
        //         try {
        //             Field currField = Buku.class.getField(currAttribute);
        //             Integer currWidth = currAttribute.length();
        //             for (int j = 0; j < table.bookRecords.size() - 1; j++) {
        //                 String curr = currField.get(table.bookRecords.get(j)).toString();
        //                 if (curr.length() > currWidth) {
        //                     currWidth = curr.length();
        //                 }
        //             }
        //             columnSize.add(currWidth);
        //         } catch (Exception e) {
        //             System.out.println(e);
        //         }
        //         System.out.print("| "+currAttribute);
        //         for (int j = 0; j < columnSize.get(i) - currAttribute.length() + 1; j++)
        //             System.out.print(" ");
        //     }
        // }

        // // generate penulis column
        // if (table.penulisRecord != null) {
        //     for (int i = 0; i < table.penulisDisplayedAtrribute.size(); i++) {
        //         String currAttribute = table.penulisDisplayedAtrribute.get(i);
        //         try {
        //             Field currField = Penulis.class.getField(currAttribute);
        //             Integer currWidth = currAttribute.length();
        //             for (int j = 0; j < table.penulisRecord.size() - 1; j++) {
        //                 String curr = currField.get(table.penulisRecord.get(j)).toString();
        //                 if (curr.length() > currWidth) {
        //                     currWidth = curr.length();
        //                 }
        //             }
        //             columnSize.add(currWidth);
        //         } catch (Exception e) {
        //             System.out.println(e);
        //         }
        //         System.out.print("| "+currAttribute);
        //         for (int j = 0; j < columnSize.get(i) - currAttribute.length() + 1; j++)
        //             System.out.print(" ");
        //     }
        // }

        // // generate menulis column
        // if (table.menulisRecord != null) {
        //     for (int i = 0; i < table.menulisDisplayedAttribute.size(); i++) {
        //         String currAttribute = table.menulisDisplayedAttribute.get(i);
        //         try {
        //             Field currField = Menulis.class.getField(currAttribute);
        //             Integer currWidth = currAttribute.length();
        //             for (int j = 0; j < table.menulisRecord.size() - 1; j++) {
        //                 String curr = currField.get(table.menulisRecord.get(j)).toString();
        //                 if (curr.length() > currWidth) {
        //                     currWidth = curr.length();
        //                 }
        //             }
        //             columnSize.add(currWidth);
        //         } catch (Exception e) {
        //             System.out.println(e);
        //         }
        //         System.out.print("| "+currAttribute);
        //         for (int j = 0; j < columnSize.get(i) - currAttribute.length() + 1; j++)
        //             System.out.print(" ");
        //     }
        // }

        // System.out.println("|");
        // for (int i = 0; i < columnSize.size(); i++) {
        //     for (int j = 0; j < columnSize.get(i) + 3; j++) {
        //         System.out.print("=");
        //     }
        // }
        // System.out.println("=");

        // int bookWidth = 
        //     (table.bookRecords != null ? table.bookDisplayedAttribute.size() : 0);
        // int penulisWidth =
        //     (table.penulisRecord != null ? table.penulisDisplayedAtrribute.size() : 0);
        // int menulisWidth =
        //     (table.menulisRecord != null ? table.menulisDisplayedAttribute.size() : 0);
        // int totalColumn = bookWidth + penulisWidth + menulisWidth;

        // int maxRecords = table.bookRecords != null ? table.bookRecords.size(): 0;
        // if (table.penulisRecord != null) {
        //     maxRecords = table.penulisRecord.size() > maxRecords ? table.penulisRecord.size(): maxRecords;
        // }

        // if (table.menulisRecord != null) {
        //     maxRecords = table.menulisRecord.size() > maxRecords ? table.menulisRecord.size(): maxRecords;
        // }
        
        // for (int i = 0; i < maxRecords; i++) {
        //     for (int j = 0; j < totalColumn; j++) {
        //         if (j < bookWidth) {
        //             if (table.bookRecords != null && i < table.bookRecords.size()) {
        //                 String currAttribute = table.bookDisplayedAttribute.get(j);
        //                 try {
        //                     Field currField = Buku.class.getField(currAttribute);
        //                     String value = currField.get(table.bookRecords.get(i)).toString();
        //                     System.out.print("| "+value);
        //                     for (int k = 0; k < columnSize.get(j) - value.length() + 1; k++ ) {
        //                         System.out.print(" ");
        //                     }
        //                 } catch (Exception e) {
        //                     System.out.println(e);
        //                 }
        //             } else if (table.bookRecords != null) {
        //                 System.out.print("| ");
        //                 for (int k = 0; k < columnSize.get(j)+ 1; k++ ) {
        //                     System.out.print(" ");
        //                 }
        //             }
        //         }
                
        //         if (j >= bookWidth && j < (bookWidth + penulisWidth)) {
        //             if (table.penulisRecord != null && i < table.penulisRecord.size()) {
        //                 int attributPointer = j - bookWidth;
        //                 String currAttribute = table.penulisDisplayedAtrribute.get(attributPointer);
        //                 try {
        //                     Field currField = Penulis.class.getField(currAttribute);
        //                     String value = currField.get(table.penulisRecord.get(i)).toString();
        //                     System.out.print("| "+value);
        //                     for (int k = 0; k < columnSize.get(j) - value.length() + 1; k++ ) {
        //                         System.out.print(" ");
        //                     }
        //                 } catch (Exception e) {
        //                     System.out.println(e);
        //                 }
        //             } else if (table.penulisRecord != null) {
        //                 System.out.print("| ");
        //                 for (int k = 0; k < columnSize.get(j)+ 1; k++ ) {
        //                     System.out.print(" ");
        //                 }
        //             }
        //         }

        //         if (j >= (bookWidth + penulisWidth) && j < (bookWidth + penulisWidth + menulisWidth)) {
        //             if (table.menulisRecord != null && i < table.menulisRecord.size()) {
        //                 int attributPointer = j - bookWidth - penulisWidth;
        //                 String currAttribute = table.menulisDisplayedAttribute.get(attributPointer);
        //                 try {
        //                     Field currField = Menulis.class.getField(currAttribute);
        //                     String value = currField.get(table.menulisRecord.get(i)).toString();
        //                     System.out.print("| "+value);
        //                     for (int k = 0; k < columnSize.get(j) - value.length() + 1; k++ ) {
        //                         System.out.print(" ");
        //                     }
        //                 } catch (Exception e) {
        //                     System.out.println(e);
        //                 }
        //             } else if (table.menulisRecord != null) {
        //                 System.out.print("| ");
        //                 for (int k = 0; k < columnSize.get(j)+ 1; k++ ) {
        //                     System.out.print(" ");
        //                 }
        //             }
        //         }
                
        //     }
        //     System.out.println("|");
        // }

        // // listing value of menulis
        // if (table.menulisRecord != null) {
        //     for (int i = 0; i < table.menulisRecord.size(); i++) {
        //         for (int j = 0; j < table.menulisDisplayedAttribute.size(); j++) {
        //             String currAttribute = table.menulisDisplayedAttribute.get(j);
        //             try {
        //                 Field currField = Menulis.class.getField(currAttribute);
        //                 String value = currField.get(table.menulisRecord.get(i)).toString();
        //                 System.out.print("| "+value);
        //                 for (int k = 0; k < columnSize.get(j) - value.length() + 1; k++ ) {
        //                     System.out.print(" ");
        //                 }
        //             } catch (Exception e) {
        //                 System.out.println(e);
        //             }
        //         }
        //         System.out.println("|");
        //     }
        // }
    }
}
