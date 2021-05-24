package Solutions;

import java.util.Arrays;

public class P1665_MinimumInitialEnergyToFinishTasks {
    public int minimumEffort(int[][] tasks) {
        /*
        Eventually, every investor will invest actual to you.
        You want to pay as little out-of-pocket energy/money as possible.

        So you want your company's energy/value to be as high as possible
        before visiting an investor to let it asses your company.

        In other words, you want to first visit those investors whose bar is lower.

        So we sort by investors by their "bar",
        i.e. by their minimum - actual.
        */
        int res = 0;
        Arrays.sort(tasks, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        for (int[] t : tasks) {
            res = Math.max(res + t[0], t[1]);
        }
        return res;
    }
}
