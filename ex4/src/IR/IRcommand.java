package IR;


public abstract class IRcommand {
	public String name="";

	/*****************/
	/* Label Factory */
	/*****************/
	protected static int label_counter = 0;

	public int offset;

	public static String getFreshLabel(String msg) {
		return String.format("Label_%d_%s", label_counter++, msg);
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		System.out.println("DEFAULT MIPSme!!!");
	}
	public void UpdateIRName(String name) {
		this.name=name;
	}

}
