package sample;

import java.util.Random;

/**
 * サンプルクラス
 * シングルトン実装になっていて、通常モック化できない！
 */
public class SampleSingleton {

	private static SampleSingleton instance = new SampleSingleton();

	public static SampleSingleton newInstance() {
		return instance;
	}

	private SampleSingleton() {
	}

	public int random() {
		return new Random().nextInt();
	}
}
