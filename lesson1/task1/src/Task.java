public class Task {
    static final int integer1 = 4205674;
    static final int integer2 = -948; // Should have the very top bit activated due to how signed integers work in CPUs, but apparently Java is really weird and doesn't do this????
    static final int integer3 = 20030459;
    static final int integer4 = 0b00100110101010010001011111110000; // Turning this into binary SHOULD be 1:1 with how this was input after 0b.

    static final String string1 = "00101001011101"; // 14 chars

    public static void main(String[] args) {
        System.out.println(integer1);
        String stringer1 = decToBin(integer1);
        System.out.println(stringer1);
        System.out.println(binToDec(stringer1)); // Demonstrating that it can convert back.

        System.out.println(integer2);
        String stringer2 = decToBin(integer2);
        System.out.println(stringer2);
        System.out.println(binToDec(stringer2));

        System.out.println(integer3);
        System.out.println(decToBin(integer3));

        System.out.println(integer4);
        System.out.println(decToBin(integer4));

        System.out.println(string1);
        System.out.println(binToDec(string1));
    }

    public static int binToDec(String binary) { // String should contain under or equal to 32 characters in order ensure the bits fit.
        int strlen = binary.length();
        if (strlen > 32) { // String NEEDS to have UNDER 32 characters, else it will never represent or fit the bits in the 'int' type.
            System.out.println("INVALID!!!");
            return -1;
        }
        int maxIdx = strlen-1;
        int loadInto = 0; // This is going to be the thing to load bits into.
        for (int i = 0; i < strlen; i++) {
            int loadWith = 1 << i; // A number with one bit on, shifted up to the position of the bit to turn on based on the index of character that is going to be checked, because the String is supposed to represent binary.
            char bit = binary.charAt(maxIdx - i); // Needs (maxIdx - i) because binary goes from right-to-left, Strings don't.
            https://stackoverflow.com/questions/6906001/how-do-i-use-a-char-as-the-case-in-a-switch-case (It was strangely simple, but I haven't used C in a while)
            switch (bit) {
                case '0': break;
                case '1': loadInto |= loadWith; break; // The OR operation compares the bits between the two and has the bit be 1 if either of the bits at the same position are on, and if bit is 0 for both in one position then it will be 0; the reason why this line is here is to activate the bit in the integer.
                default:
                    System.out.println("INVALID!!!");
                    return -1;
            }
        }
        return loadInto;
    }

    public static String decToBin(int decimal) { // An int is always 32-bit, function will always work as the integer cannot be anything else.
        String loadInto = "";
        for (int i = 31; i >= 0; i--) { // Needs to go reverse, since binary is right-to-left.
            int loadWith = 1 << i; // A number with one bit on, shifted up to the position of the bit to check it based on the 'index', because it will be very useful for checking if the bit that's in 'decimal' is on.
            if ((loadWith & decimal) > 0) // AND operation, this is to check if the bit at the position of the bit in loadWith is on at all: if bit is on, it will stay activated while the rest deactivate. But if the bit is off at the position of the only activated bit in loadWith, then all bits are off and the if statement gets '0'.
                loadInto += '1';
            else
                loadInto += '0';
        }
        return loadInto;
    }
}