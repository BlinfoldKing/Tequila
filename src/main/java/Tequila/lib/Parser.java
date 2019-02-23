package Tequila.lib;

public class Parser {
    public static Table parse(String command) {
        // to be implemented
        return new Table();
    }

    public static Table parse(Table table) {
        return table;
    }

    public static Table parse(Argument select, Table from) {
        return new Table();
    }

    public static Table parseJoin(Table table1, Table table2) {
        return new Table();
    }
}