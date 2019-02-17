package sample;

/**
 * サンプルロジック
 */
public class SampleLogic {

    private SampleIF sampleIF;

    public String execute() {
        return sampleIF.invoke();
    }

    public int executeSingleton() {
        return SampleSingleton.newInstance().random();
    }

    public void executeWithParam(String param) {
        sampleIF.invokeWithParam(param);
    }

    public int executeSamplePartial() {
        int constant = SamplePartial.constant();
        int random = SamplePartial.random();
        return constant + random;
    }
}
