package sample;

import java.util.Random;

public class SamplePartial {
    public static int constant() {
        return 1;
    }

    public static int random() {
        return new Random().nextInt();
    }
}
