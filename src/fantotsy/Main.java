/* Anton Tsymbal
 * 23.04.2016
 * Problem_1: Task_1, Task_2, Task_3
 */
package fantotsy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Task_1.
        System.out.println("byte has " + bitsCount("byte") + " bits.");
        System.out.println("short has " + bitsCount("short") + " bits.");
        System.out.println("int has " + bitsCount("int") + " bits.");
        System.out.println("long has " + bitsCount("long") + "bits.");

        // Task_2.
        Scanner in = new Scanner(System.in);
        int intVar = 0;
        int pos = 0;
        System.out.print("Enter integer: ");
        intVar = in.nextInt();
        System.out.print("Enter position of the bit: ");
        pos = in.nextInt();
        System.out.println("Change position " + pos + " in " + intVar + " to '1': "
                + changeBitToOne(intVar, pos));
        System.out.println("Change position " + pos + " in " + intVar + " to '0': "
                + changeBitToZero(intVar, pos));

        // Task_3.
        System.out.println(multiplyKaratsuba(343, 4444));
    }

    public static byte bitsCount(String type) {
        byte result = 0;
        if (type == "byte") {
            byte byteType = 1;
            while (byteType != 0) {
                byteType = (byte) (byteType << 1);
                result++;
            }
        } else if (type == "short") {
            short shortType = 1;
            while (shortType != 0) {
                shortType = (short) (shortType << 1);
                result++;
            }
        } else if (type == "int") {
            int intType = 1;
            while (intType != 0) {
                intType = intType << 1;
                result++;
            }
        } else if (type == "long") {
            long longType = 1;
            while (longType != 0) {
                longType = longType << 1;
                result++;
            }
        } else {
            System.out.print("The type is unacceptable! ");
            return -1;
        }
        return result;
    }

    public static int changeBitToOne(int number, int position) {
        if ((position > 0) && (position <= bitsCount("int"))) {
            int mask = 1;
            mask = mask << (position - 1);
            return number | mask;
        }
        System.out.print("Error! Position is out of range. ");
        return -1;
    }

    public static int changeBitToZero(int number, int position) {
        if ((position > 0) && (position <= bitsCount("int"))) {
            int mask = 1;
            mask = mask << (position - 1);
            return number ^ mask;
        }
        System.out.print("Error! Position is out of range. ");
        return -1;
    }

    public static int numberLength(int number) {
        int length = 0;
        while (number != 0) {
            number = number >> 1;
            length++;
        }
        return length;
    }

    public static long multiplyKaratsuba(int number1, int number2) {
        int n = Math.max(numberLength(number1), numberLength(number2));
        if (n < 2) {
            return (number1 * number2);
        }
        // Левая часть числа длиной [n/2] + n%2.
        int left1 = number1 >> (n >> 1);
        // Правая часть числа длиной [n/2] (число - л.ч. * 2^([n/2])).
        int right1 = number1 - (left1 << (n >> 1));
        int left2 = number2 >> (n >> 1);
        int right2 = number2 - (left2 << (n >> 1));

        long mult1 = multiplyKaratsuba(left1, left2);
        long mult2 = multiplyKaratsuba(right1, right2);
        long mult3 = multiplyKaratsuba(left1 + right1, left2 + right2);

        // mult1 * 2^(2*[n/2]) + (mult3 - mult1 - mult2)*2^([n/2]) + mult2.
        return ((mult1 << ((n >> 1) << 1)) + ((mult3 - mult1 - mult2) << (n >> 1)) + mult2);
    }
}