/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbTest;

import java.util.List;

/**
 *
 * @author kzy
 */
public class DbApp {
    public static void main(String[] args) {
        final DBHandle test_db = new DBHandle("./db/test.txt");
        List<List<String>> rowList = test_db.select(row -> true, -1);
        for (List<String> row: rowList) {
            for (String item: row) {
                System.out.println("["+item+"]");
            }
            System.out.println("-----------------");
        }

//        test_db.insert(rowList);
//        test_db.delete(row -> row.get(0).isEmpty(), -1);
        test_db.update(row -> row.get(0).equals("a"), row -> {row.set(0, "A");return row;}, 1);
    }
}
