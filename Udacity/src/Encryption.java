import java.util.Scanner;
import java.util.Arrays;
import java.lang.*;


public class Encryption {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Program to Encrypt String");
        System.out.println("\n-----------------------------------------------------------\n");
        System.out.print("Enter the string to be encrpted: ");
        String a = input.nextLine();
        System.out.print("\nEnter the Shift Value: ");
        int value = input.nextInt();
        System.out.print("\nEnter the Group value: ");
        int group = input.nextInt();
        String result = encryptString(a, value, group);
        System.out.println("\n-----------------------------------------------------------\n");
        System.out.println("The Encrypted text : " + result);
        System.out.println("\n-----------------------------------------------------------\n");

    }

    public static String encryptString(String x, int value, int group) {
        String normal = normalizeText(x);
        String crypt = cesarify(normal, value);
        String result = groupify(crypt, group);
        return result;
    }

    public static String normalizeText(String x) {
        x = x.trim();
        int n = x.length();
        char a[] = x.toCharArray();
        char b[] = x.toCharArray();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (!((a[i] == ' ') || (a[i] == ',') || (a[i] == '!') || (a[i] == '.') || (a[i] == ':') || (a[i] == ';')
                    || (a[i] == '(') || (a[i] == ')') || (a[i] == '?') || (a[i] == '"') || (a[i] == '\'') || (a[i] == '/') || (a[i] == '\\'))) {
                b[j] = a[i];
                j++;

            }

        }
        String y = new String(b);
        y = y.substring(0, (j));
        y = y.toUpperCase();
        return y;
    }

    public static String cesarify(String x, int v) {

        String base = shiftAlphabet(v);
        char baseArray[] = base.toCharArray();
        char xArray[] = x.toCharArray();
        int l = x.length();
        String result = "";
        for (int i = 0; i < l; i++) {
            int temp = (int) xArray[i];
            result += baseArray[temp - 65];
        }
        return result;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for (; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if (result.length() < 26) {
            for (currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String groupify(String x, int y) {
        char a[] = x.toCharArray();
        int l = x.length();
        String result = "";
        if (l % y == 0) {
            for (int i = 0; i < l; ) {
                int temp = i;
                for (int j = y - 1; j >= 0; j--) {

                    result = result + a[temp];
                    temp++;
                }
                result += ' ';
                i += y;
            }
        } else {
            for (int i = 0; i < l - y; ) {
                int temp = i;
                for (int j = y - 1; j >= 0; j--) {
                    result = result + a[temp];
                    temp++;
                }
                result += ' ';
                i += y;
            }
            String u = normalizeText(result);
            int q = u.length();
            int m = 0;
            for (int v = q - 1 + 1; v < l; v++) {
                result = result + a[v];
                m++;
            }
            for (int d = m; d < y; d++) {
                result = result + 'x';
            }
        }

        return result;

    }

}

