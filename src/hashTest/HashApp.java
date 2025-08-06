package hashTest;

public class HashApp {
    public static void main(String[] args) {
        PassHash   myPass = PassHash.hashing("Hello World");
        boolean checkPass = PassHash.checkPass("Hello World", myPass.hash64, myPass.salt64);

        System.out.printf("%s%n%s%n%s%n", myPass.hash64, myPass.salt64, checkPass);
    }
}
