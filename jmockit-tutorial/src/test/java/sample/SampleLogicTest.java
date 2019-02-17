package sample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import mockit.*;

import org.junit.Before;
import org.junit.Test;

/**
 * サンプルロジックのテストケース
 *
 */
public class SampleLogicTest {

    /** テスト対象 */
    @Tested
    private SampleLogic target;

    // @Injectableアノテーションをつけるとモック化し、テスト対象（@Testedを付与したオブジェクト）にDIしてくれる。
    @Injectable
    private SampleIF mockSampleIF;

    // @Mockedアノテーションをつけると全てのオブジェクトがモック化。
    @Mocked
    private SampleSingleton mockSampleSingleton;

    @Before
    public void setUp() throws Exception {

    }

    /**
     * 基本的な使い方
     */
    @Test
    public void testExecute001() {

        // この様に記述し、モックの振る舞いを定義する。newを忘れないように。
        // (Expectationsの無名サブクラスの初期化子ブロックに振る舞いを記述)
        new Expectations() {
            {
                // SampleIFクラスのinvoke()メソッドが呼ばれたときに、、、
                mockSampleIF.invoke();
                // "hello"を返すという振る舞いを設定したことになる。
                result = "hello";
            }
        };

        // テスト対象メソッド呼び出し。
        String actual = target.execute();

        // 結果検証
        assertThat(actual, is("hello"));
    }

    /**
     * staticメソッドのモック化
     */
    @Test
    public void testExecuteSingleton001() {

        // モックの振る舞いを定義
        // testExecute001()では説明しなかったが、
        // Expectations()のブロックに振る舞いを記述することで
        // テスト対象メソッドがモック化された処理を正しく呼び出しているかの検証もできる。
        // 以下の場合だと、newInstance()→random()の順で一回ずつメソッドが呼ばれていないとテスト失敗となる。
        new Expectations() {
            {
                // staticメソッドもモック化可能
                SampleSingleton.newInstance();
                result = mockSampleSingleton;

                // random()が呼ばれたときに10を返すように設定
                mockSampleSingleton.random();
                result = 10;
            }
        };

        // テスト対象メソッド呼び出し。
        int actual = target.executeSingleton();

        // 結果検証
        assertThat(actual, is(10));
    }

    /**
     * 引数の検証について
     */
    @Test
    public void testExecuteWithParam001() {

        new Expectations() {
            {
                //引数に"abc"であるかの検証もしているので、
                //"abcd"のような引数を渡すとテスト失敗となる。
                //またこういった検証をしたくない場合は
                //Expectations()の代わりにNonStrictExpectations()を利用する。
                mockSampleIF.invokeWithParam("abc");
            }
        };

        // テスト対象メソッド呼び出し。
        target.executeWithParam("abc");

    }

    /**
     * パーシャルモック
     */
    @Test
    public void testExecuteSamplePartial001() {
		new Expectations(SamplePartial.class) {
			{
                // randomメソッドだけモック化する
				//constant()はモック化していないので、通常どおり処理される。
                SamplePartial.random();
				result = 10;
			}
		};

		// テスト対象メソッド呼び出し。
		int actual = target.executeSamplePartial();

		// 結果検証
		assertThat(actual, is(11));
    }
}
