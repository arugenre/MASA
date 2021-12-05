import java.util.Random;
import java.util.Scanner;

public class Mozart {
    public final static int MAX_VALUE_OF_DICE = 6;
    public final static int MIN_VALUE_OF_DICE = 1;
    public final static int MEASURES = 16;
    public final static String SAVE_PATH = "MozartWaltzGenerator/saved_files/mozart_waltz.wav";
    public static final Random randomizer = new Random();
    private final static int[][] minuet = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 96, 22, 141, 41, 105, 122, 11, 30, 70, 121, 26, 9, 112, 49, 109, 14},
            {0, 32, 6, 128, 63, 146, 46, 134, 81, 117, 39, 126, 56, 174, 18, 116, 83},
            {0, 69, 95, 158, 13, 153, 55, 110, 24, 66, 139, 15, 132, 73, 58, 145, 79},
            {0, 40, 17, 113, 85, 161, 2, 159, 100, 90, 176, 7, 4, 67, 160, 52, 170},
            {0, 148, 74, 163, 45, 80, 97, 36, 107, 25, 143, 64, 125, 76, 136, 1, 93},
            {0, 104, 157, 27, 167, 154, 68, 118, 91, 138, 71, 150, 29, 101, 162, 23, 151},
            {0, 152, 60, 171, 53, 99, 133, 21, 127, 16, 155, 57, 175, 43, 168, 89, 172},
            {0, 119, 84, 114, 50, 140, 86, 169, 94, 120, 88, 48, 166, 51, 115, 72, 111},
            {0, 98, 142, 42, 156, 75, 129, 62, 123, 65, 77, 19, 82, 137, 38, 149, 8},
            {0, 3, 87, 165, 61, 135, 47, 147, 33, 102, 4, 31, 164, 44, 59, 173, 78},
            {0, 54, 130, 10, 103, 28, 37, 106, 5, 35, 20, 108, 92, 12, 124, 44, 131}
    };
    private final static int[][] trio = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 72, 6, 59, 25, 81, 41, 89, 13, 36, 5, 46, 79, 30, 95, 19, 66},
            {0, 56, 82, 42, 74, 14, 7, 26, 71, 76, 20, 64, 84, 8, 35, 47, 88},
            {0, 75, 39, 54, 1, 65, 43, 15, 80, 9, 34, 93, 48, 69, 58, 90, 21},
            {0, 40, 73, 16, 68, 29, 55, 2, 61, 22, 67, 49, 77, 57, 87, 33, 10},
            {0, 83, 3, 28, 53, 37, 17, 44, 70, 63, 85, 32, 96, 12, 23, 50, 91},
            {0, 18, 45, 62, 38, 4, 27, 52, 94, 11, 92, 24, 86, 51, 60, 78, 31}
    };

    public Mozart() {
    }

    public static int throwDice() {
        return randomizer.nextInt(MAX_VALUE_OF_DICE) + MIN_VALUE_OF_DICE;

    }

    public static int[] waltzPieceNumbers() {
        int[] nums = new int[2 * MEASURES];
        for (int i = 1; i <= MEASURES; i++) {
            nums[i - 1] = minuet[throwDice() + throwDice()][i];
        }
        for (int i = MEASURES + 1; i <= nums.length; i++) {
            nums[i - 1] = trio[throwDice()][i - MEASURES];
        }
        return nums;
    }

    public static String[] getWaltz() {
        int[] nums = waltzPieceNumbers();
        String[] waltz = new String[nums.length];
        for (int i = 0; i < MEASURES; i++) {
            waltz[i] = "MozartWaltzGenerator/Waves/M" + nums[i] + ".wav";
        }
        for (int i = MEASURES; i < waltz.length; i++) {
            waltz[i] = "MozartWaltzGenerator/Waves/T" + nums[i] + ".wav";
        }
        return waltz;
    }


    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Building new waltz");
        String[] waltz = Mozart.getWaltz();
        System.out.println("Playing waltz....");
        for (String peace : waltz) {
            WaltzPlayer.play(peace);
            System.out.println("Piece: " + peace.substring(27, 30));
        }
        WaltzPlayer.close();
        System.out.println("Finished!");
        System.out.print("Would you like to save it? type y if yes, type n if no: ");
        String input = scan.next();
        if (input.equals("y")) {
            WaltzPlayer.save(waltz, SAVE_PATH);
            System.out.println("File saved as: " + SAVE_PATH);
        }
        System.out.println("Good Luck!");
//        WaltzPlayer.play(SAVE_PATH);
//        WaltzPlayer.close();
    }
}