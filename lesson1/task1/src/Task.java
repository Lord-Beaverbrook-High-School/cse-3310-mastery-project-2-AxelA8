public class Task {
    static final int integer1 = 4205674;
    static final int integer2 = -948; // Should have the very top bit activated due to how signed integers work in CPUs.
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
        String stringer3 = decToBin(integer3);
        System.out.println(stringer3);
        System.out.println(binToDec(stringer3));

        System.out.println(integer4);
        String stringer4 = decToBin(integer4);
        System.out.println(stringer4);
        System.out.println(binToDec(stringer4));

        System.out.println(string1);
        System.out.println(binToDec(string1));
    }

    public static int binToDec(String binary) { // String should contain under or equal to 32 characters in order ensure the bits fit.
        int strlen = binary.length();
        if (strlen == 0) {
            return 0;
        }
        if (strlen > 32) { // Function only returns integer, letting binary go above 32 in length will NOT do anything useful... just shift number until it turns to 0.
            int goTo = strlen - 32;
            for (int i = 0; i < goTo; i++) {
                if (binary.charAt(i) == '1') { // Just because the conversions I have with the decToBin function adds an extra bit... It also shifts into nothing anyway.
                    System.out.println("INVALID!!!");
                    throw new RuntimeException("Function only returns int, which is 32 bit maximum!");
                }
            }
        }

        int less = strlen-1;

        char bit = binary.charAt(0); // Highest bit.

        int scroll = 1 << less; // Will shift up to the highest bit in the String in order to have compatibility for all binaries under 32 bit (subtracted by "less" in order for it not to shift with more than necessary, length 1 will never mean bit 2 with CPUs).
        int resultive = 0;
        switch (bit) {
            case '0': break;
            case '1': resultive |= scroll; break; // The OR operation compares the bits between the two and has the bit be 1 if either of the bits at the same position are on, and if bit is 0 for both in one position then it will be 0; the reason why this line is here is to activate the bit in the integer.
            default:
                System.out.println("INVALID!!!");
                throw new RuntimeException("Bits are only supposed to be 0 and 1!");
        }
        return resultive | binToDec(binary.substring(1));
    }

    public static String decToBin(int decimal) { // An int is always 32-bit, function will always work as the integer cannot be anything else.
        String loadInto;
        if (decimal == 0)
            return "0"; // Ending bit, may be a bit extra BUT it will make the binary technically correct nonetheless.
        int bitOn = decimal & 1; // Shut off any bits other than the rightmost one, because starting from leftmost will end up in inconsistencies if rightmost bit isn't on; going from rightmost means higher bits are left alone until climbed up to, building the String up properly.
        if (bitOn != 0)
            loadInto = "1";
        else
            loadInto = "0";

        return decToBin(decimal >>> 1) + loadInto; // Shift to go to the next 'higher' bit.
    }
}