package szh.algorithm;

public class MyMath {
    public static int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        return gcd(b, a % b);
    }

    public static int arithmeticSequenceSum(int n, int a1, int d) {
        return n * a1 + n * (n - 1) * d / 2;
    }

    public static int geometricSequenceSum(int n, int a1, int q) {
        return a1 * (1 - (int) Math.pow(q, n)) / (1 - q);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println((int)Math.pow(2, i-1) + " " + geometricSequenceSum(i, 1, 2));
        }
    }
}
