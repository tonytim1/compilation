/***********/
/* PACKAGE */
/***********/
package IR;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/

public class IR {
	public IRcommand head = null;
	public IRcommand_List tail = null;

	private IRcommand initialization = null;
	private IRcommand_List initializationTail = null;
	/******************/
	/* Add IR command */
	/******************/
	public void Add_IRcommand(IRcommand cmd) {
		if ((head == null) && (tail == null)) {
			this.head = cmd;
		} else if ((head != null) && (tail == null)) {
			this.tail = new IRcommand_List(cmd, null);
		} else {
			IRcommand_List it = tail;
			while ((it != null) && (it.tail != null)) {
				it = it.tail;
			}
			it.tail = new IRcommand_List(cmd, null);
		}
	}

	public void Add_IRcommand_initialization(IRcommand cmd) {
		if ((initialization == null) && (initializationTail == null)) {
			this.initialization = cmd;
		} else if ((initialization != null) && (initializationTail == null)) {
			this.initializationTail = new IRcommand_List(cmd, null);
		} else {
			IRcommand_List it = initializationTail;
			while ((it != null) && (it.tail != null)) {
				it = it.tail;
			}
			it.tail = new IRcommand_List(cmd, null);
		}
	}

	/***************/
	/* MIPS me !!! */
	/***************/
	public void MIPSme() {
		if (head != null)
			head.MIPSme();
		if (tail != null)
			tail.MIPSme();
	}
	public void MIPSinitialization() {
		if (initialization != null)
			initialization.MIPSme();
		if (initializationTail != null)
			initializationTail.MIPSme();
	}

	/**************************************/
	/* USUAL SINGLETON IMPLEMENTATION ... */
	/**************************************/
	private static IR instance = null;

	/*****************************/
	/* PREVENT INSTANTIATION ... */
	/*****************************/
	protected IR() {
	}

	/******************************/
	/* GET SINGLETON INSTANCE ... */
	/******************************/
	public static IR getInstance() {
		if (instance == null) {
			/*******************************/
			/* [0] The instance itself ... */
			/*******************************/
			instance = new IR();
		}
		return instance;
	}
}
