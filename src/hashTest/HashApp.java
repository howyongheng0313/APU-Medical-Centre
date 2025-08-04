/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hashTest;

public class HashApp {
    public static void main(String[] args) {
        PassHash   myPass = PassHash.hashing("Hello World");
        boolean checkPass = PassHash.checkPass("Hello World", myPass.hash64, myPass.salt64);

        System.out.printf("%s%n%s%n%s%n", myPass.hash64, myPass.salt64, checkPass);
    }
}
