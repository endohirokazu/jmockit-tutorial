package sample;

/**
 * サンプルロジック
 * 
 */
public class SampleLogic {

	private SampleIF sampleIF;

	private SamplePartial samplePartial = new SamplePartial();
	
	public void setSampleIF(SampleIF sampleIF) {
		this.sampleIF = sampleIF;
	}

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
		int constant = samplePartial.constant();
		int random = samplePartial.random();
		return constant + random;
	}
}
