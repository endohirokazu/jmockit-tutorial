package sample;

import java.util.Random;

public class SamplePartial {
	public int constant() {
		return 1;
	}

	public int random() {
		return new Random().nextInt();
	}
}
