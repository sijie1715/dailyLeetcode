package Solutions;

public class P1052_GrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int totalCus = 0, totalGrump = 0, saved = 0, maxSaved = 0;
        for (int i = 0; i < customers.length; i++) {
            totalCus += customers[i];
            totalGrump += customers[i] * grumpy[i];
            saved += customers[i] * grumpy[i];
            if (i > X - 1) {
                saved -= customers[i - X] * grumpy[i - X];
            }
            maxSaved = Math.max(maxSaved, saved);
        }

        return totalCus - totalGrump + maxSaved;
    }
}
