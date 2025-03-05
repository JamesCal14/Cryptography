import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShiftCipherDecryptor {

    public static void main(String[] args) {
        
        Map<Character, Double> P_E = new HashMap<>();
        Scanner in = new Scanner(System.in);
        P_E.put('a', 0.082);
        P_E.put('b', 0.015);
        P_E.put('c', 0.028);
        P_E.put('d', 0.043);
        P_E.put('e', 0.127);
        P_E.put('f', 0.022);
        P_E.put('g', 0.020);
        P_E.put('h', 0.061);
        P_E.put('i', 0.070);
        P_E.put('j', 0.002);
        P_E.put('k', 0.008);
        P_E.put('l', 0.040);
        P_E.put('m', 0.024);
        P_E.put('n', 0.067);
        P_E.put('o', 0.075);
        P_E.put('p', 0.019);
        P_E.put('q', 0.001);
        P_E.put('r', 0.060);
        P_E.put('s', 0.063);
        P_E.put('t', 0.091);
        P_E.put('u', 0.028);
        P_E.put('v', 0.010);
        P_E.put('w', 0.023);
        P_E.put('x', 0.001);
        P_E.put('y', 0.020);
        P_E.put('z', 0.001);

        Double uniformNoise = (1.0/26.0);
        Map<Character, Double> P_N = new HashMap<>();
        P_N.put('a', uniformNoise);
        P_N.put('b', uniformNoise);
        P_N.put('c', uniformNoise);
        P_N.put('d', uniformNoise);
        P_N.put('e', uniformNoise);
        P_N.put('f', uniformNoise);
        P_N.put('g', uniformNoise);
        P_N.put('h', uniformNoise);
        P_N.put('i', uniformNoise);
        P_N.put('j', uniformNoise);
        P_N.put('k', uniformNoise);
        P_N.put('l', uniformNoise);
        P_N.put('m', uniformNoise);
        P_N.put('n', uniformNoise);
        P_N.put('o', uniformNoise);
        P_N.put('p', uniformNoise);
        P_N.put('q', uniformNoise);
        P_N.put('r', uniformNoise);
        P_N.put('s', uniformNoise);
        P_N.put('t', uniformNoise);
        P_N.put('u', uniformNoise);
        P_N.put('v', uniformNoise);
        P_N.put('w', uniformNoise);
        P_N.put('x', uniformNoise);
        P_N.put('y', uniformNoise);
        P_N.put('z', uniformNoise);

        //1/26 all letters
     

        System.out.println("Enter ciphertext: ");
        String ciphertext = in.next();

        double maxLlr = Double.NEGATIVE_INFINITY;
        int bestShift = 0;

        for (int shift = 0; shift < 26; shift++) {
            String decryptedText = decrypt(ciphertext, shift);
            double llr = computeLlr(decryptedText, P_E, P_N);
            if (llr > maxLlr) {
                maxLlr = llr;
                bestShift = shift;
            }
        }

        System.out.println("Best shift: " + bestShift + ", Max LLR: " + maxLlr);
        System.out.println(decrypt(ciphertext, bestShift));
    }

    public static String decrypt(String text, int shift) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char shifted = (char) ((ch - 'a' - shift + 26) % 26 + 'a');
                decrypted.append(shifted);
            } else {
                decrypted.append(ch);
            }
        }
        return decrypted.toString();
    }

    public static double computeLlr(String decryptedText, Map<Character, Double> P_E, Map<Character, Double> P_N) {
        double llr = 0;
        for (char ch : decryptedText.toCharArray()) {
            if (P_E.containsKey(ch) && P_N.containsKey(ch)) {
                llr += Math.log(P_E.get(ch) / P_N.get(ch));
            }
        }
        return llr;
    }
}
