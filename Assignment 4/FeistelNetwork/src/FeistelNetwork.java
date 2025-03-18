public class FeistelNetwork {
    public static void main(String[] args) {
        String plaintext = "ABSOLUTE";
        String key = "salmon";

        // Split plaintext into left and right halves
        String left = plaintext.substring(0, plaintext.length() / 2);
        String right = plaintext.substring(plaintext.length() / 2);

        // Perform one Feistel round
        String newRight = feistelRoundFunction(right, key);
        String newLeft = xorWithKey(left, newRight);

        // Combine halves to get the encrypted output
        String encrypted = newRight + newLeft;

        System.out.println("Encrypted output: " + encrypted);
    }

    // Feistel round function (simulating DES-style operations)
    private static String feistelRoundFunction(String right, String key) {
        // Simplified example: XOR the right half with the key
        return xorWithKey(right, key);
    }

    // XOR function
    private static String xorWithKey(String input, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char inputChar = input.charAt(i);
            char keyChar = key.charAt(i % key.length());
            result.append((char) (inputChar ^ keyChar));
        }
        return result.toString();
    }
}
