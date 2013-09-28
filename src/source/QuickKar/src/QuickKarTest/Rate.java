package QuickKarTest;

import java.text.DecimalFormat;

public class Rate {

    private DecimalFormat df = new DecimalFormat("#.#");
    private int[] array = new int[5];
    private int count = 0;

    public void increaseStar(int star) {
        array[star - 1]++;
        count++;
    }

    public int getCount() {
        return count;
    }

    public double averageRate() {

        double total = 0;

        for (int i = 0; i < array.length; i++) {
            total = rate(i) + total;
        }
        return Double.parseDouble(df.format(total / count));
    }

    private int rate(int a) {
        return array[a] * (a + 1);
    }
}
