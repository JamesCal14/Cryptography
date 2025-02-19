/*
 * Write a program that computes the relative frequencies of letters in a given piece of 
text. To compute the relative frequency, you need to count the occurrence of each 
letter in the text and divide by the length (total number of letters) of the text. 

Implement the working of a Shift cipher. You should provide a continuous stream of 
characters as input and it should generate the ciphertext as the output. Your program 
should also perform the decryption of the encrypted text. 

Implement the working of an affine cipher. Write the encryption, decryption methods 
and verify encryption and decryption with some plaintext/ciphertext.

Implement the Vigenere encryption algorithm. Implement two separate methods for 
encryption and decryption. 
 */
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Relative Frequency Calculation / Cipher Tester");
        System.out.println("Enter word: ");
        String word = in.next();
        System.out.println("Enter letter: ");
        char letter = in.next().charAt(0);
        System.out.println("Enter key for Vigenere: ");
        String key = in.next();
        double freq = RelFrequency(word, letter);
        System.out.println("Word: "+word);
        System.out.println("The frequency of letter "+letter+" is "+(freq*100)+"%");
        String encryptedResultShift = ShiftCipher(word, 3);
        System.out.println("Shift Encryption of word: "+encryptedResultShift);
        String decryptedResultShift = ShiftCipherDecryption(encryptedResultShift, 26 - 3);
        System.out.println("Shift Decryption of word: "+decryptedResultShift);
        String encryptedResultAffine = AffineCipherEncryption(word.toUpperCase());
        System.out.println("Affine Encryption of word: "+encryptedResultAffine);
        String decryptedResultAffine = AffineCipherDecryption(encryptedResultAffine);
        System.out.println("Affine Decryption of word: "+decryptedResultAffine);
        String generate = VigenereCipherKey(word.toUpperCase(), key.toUpperCase());
        String encryptedResultVigenere = VigenereCipherEncryption(word.toUpperCase(), generate);
        System.out.println("Vigenere Encryption of word: "+encryptedResultVigenere);
        String decryptedResultVigenere = VigenereCipherDecryption(encryptedResultVigenere, generate);
        System.out.println("Vigenere Decryption of word: "+decryptedResultVigenere);
        in.close();
    }

    public static double RelFrequency(String word, char letter)
    {
        int count = 0;
        for (int i = 0; i < word.length(); i++)
        {
            if (word.charAt(i) == (letter))
            {
                count++;
            }
        }
        double freq = (double) count / (word.length());
        return freq;
    }

    public static String ShiftCipher(String text, int shift)
    {
        String encryptedText = "";
        for (int i = 0; i < text.length(); i++)
        {
            char letter = (char)(text.charAt(i) + shift);
            if (letter > 'z')
                encryptedText += (char)(text.charAt(i) - (26-shift));
            else
                encryptedText += (char)(text.charAt(i) + shift);
        }
        return encryptedText;
    }

    public static String ShiftCipherDecryption(String cipherText, int shift)
    {
        String decryptedText = "";
        for (int i = 0; i < cipherText.length(); i++)
        {
            char letter = (char)(cipherText.charAt(i) + shift);
            if (letter > 'z')
                decryptedText += (char)(cipherText.charAt(i) - (26-shift));
            else
                decryptedText += (char)(cipherText.charAt(i) + shift);
        }
        return decryptedText;
    }

    public static String AffineCipherEncryption(String text)
    {
        // Text needs to be capatilized
        int a = 17;
        int b = 20;
        String encryptedText = "";
        for (int i = 0; i < text.length(); i++)
        {
            if (text.charAt(i) != ' ')
            {
                encryptedText = encryptedText + (char)((((a * (text.charAt(i) - 'A')) + b) % 26) + 'A');
            }
            else
            {
                encryptedText += text.charAt(i);
            }
        }
        return encryptedText;
    }

    public static String AffineCipherDecryption(String cipherText)
    {
        int a = 17;
        int b = 20;
        int a_inv = 0;
        int flag = 0;
        String decryptedText = "";

        for (int i = 0; i < 26; i++)
        {
            flag = (a * i) % 26;
            if (flag == 1)
            {
                a_inv = i;
            }
        }
        for (int i = 0; i <cipherText.length(); i++)
        {
            if (cipherText.charAt(i) != ' ')
            {
                decryptedText = decryptedText + (char) (((a_inv * ((cipherText.charAt(i) + 'A' - b)) % 26)) + 'A');
            }
            else
            {
                decryptedText += cipherText.charAt(i);
            }
        }
        return decryptedText;
    }

    public static String VigenereCipherKey(String plainText, String key)
    {
        int a = plainText.length();
        for (int i = 0; ;i++)
        {
            if (a == i)
                i = 0;
            if (key.length() == plainText.length())
                break;
            key+=(key.charAt(i)); 
        }
        return key;
    }

    public static String VigenereCipherEncryption(String plainText, String key)
    {
        String encryptedText = "";
        for (int i = 0; i < plainText.length(); i++)
        {
            int a = (plainText.charAt(i) + key.charAt(i)) %26;
            a += 'A';
            encryptedText+=(char)(a);
        }
        return encryptedText;
    }

    public static String VigenereCipherDecryption(String cipherText, String key)
    {
        String decryptedText = "";
        for (int i = 0; i < cipherText.length() && i < key.length(); i++)
        {
            int a = (cipherText.charAt(i) - key.charAt(i) + 26) % 26;
            a += 'A';
            decryptedText+=(char)(a);
        }
        return decryptedText;
    }
}
