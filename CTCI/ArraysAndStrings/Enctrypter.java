package CTCI.ArraysAndStrings;

public class Enctrypter {
    public static void main(String[] args) {
        String test = "abcdef";
        char[] chars = test.toCharArray();
        char temp = chars[0];
        int last = chars.length - 1;
        chars[0] = chars[last];
        chars[last] = temp;
        String s = String.valueOf(chars);
        System.out.println("Swapped " + s);
    }
}
