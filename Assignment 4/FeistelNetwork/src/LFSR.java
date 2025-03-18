import java.util.Scanner;

public class LFSR {
    private int[] state; // Current state of the LFSR
    private int[] feedbackCoefficients; // Feedback coefficients (tap positions)
    private int degree; // Degree of the LFSR

    public LFSR(int degree, int[] feedbackCoefficients, int[] initialState) {
        this.degree = degree;
        this.feedbackCoefficients = feedbackCoefficients;
        this.state = initialState;
    }

    // Method to generate the next bit in the key stream
    public int nextBit() {
        int newBit = 0;

        // Calculate the feedback bit based on feedback coefficients and the state
        for (int i = 0; i < degree; i++) {
            if (feedbackCoefficients[i] == 1) {
                newBit ^= state[i]; // XOR operation
            }
        }

        // Shift the LFSR state to the right and insert the new bit at the leftmost position
        for (int i = degree - 1; i > 0; i--) {
            state[i] = state[i - 1];
        }
        state[0] = newBit;

        return newBit;
    }

    // Method to generate a sequence of key stream bits
    public void generateKeyStream(int numberOfBits) {
        for (int i = 0; i < numberOfBits; i++) {
            System.out.print(nextBit());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Degree of the LFSR
        System.out.print("Enter the degree of the LFSR: ");
        int degree = scanner.nextInt();

        // Input: Feedback coefficients
        System.out.println("Enter the feedback coefficients (0 or 1) as space-separated values:");
        int[] feedbackCoefficients = new int[degree];
        for (int i = 0; i < degree; i++) {
            feedbackCoefficients[i] = scanner.nextInt();
        }

        // Input: Initial state
        System.out.println("Enter the initial state of the LFSR (0 or 1) as space-separated values:");
        int[] initialState = new int[degree];
        for (int i = 0; i < degree; i++) {
            initialState[i] = scanner.nextInt();
        }

        // Input: Number of bits to generate
        System.out.print("Enter the number of key stream bits to generate: ");
        int numberOfBits = scanner.nextInt();

        // Initialize the LFSR and generate the key stream
        LFSR lfsr = new LFSR(degree, feedbackCoefficients, initialState);
        System.out.println("Generated key stream:");
        lfsr.generateKeyStream(numberOfBits);

        scanner.close();
    }
}

