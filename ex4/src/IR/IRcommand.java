package IR;


public abstract class IRcommand {
	public String name="";
	protected static int label_counter = 0;
	public int offset;

	public static String getFreshLabel(String msg) {
		label_counter++;
		return String.format("label_%s_%d", msg, label_counter);
	}

	public void MIPSme() {
	}
	public void UpdateIRName(String name) {
		this.name=name;
	}

}
