package LEET;

public class LEET_848_Shifting_Letters {
    public static String shiftingLetters(String S, int[] shifts) {
        char chList[] = S.toCharArray();

        int shift = 0;
        for (int i = chList.length - 1; i >= 0; i--) {
            shift = (shift + shifts[i]) % 26;
            chList[i] = (char)('a' + (chList[i] - 'a' + shift) % 26);
        }

        return new String(chList);
    }
}
