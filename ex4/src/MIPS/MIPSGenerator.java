/***********/
/* PACKAGE */
/***********/
package MIPS;

/*******************/
/* GENERAL IMPORTS */
/*******************/
import java.io.PrintWriter;

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;
import REGALLOC.*;
import IR.*;

public class MIPSGenerator
{
	private int WORD_SIZE=4;
	/***********************/
	/* The file writer ... */
	/***********************/
	private PrintWriter fileWriter;

	/***********************/
	/* The file writer ... */
	/***********************/
	public void finalizeFile()
	{
		fileWriter.print("\tli $v0,10\n");
		fileWriter.print("\tsyscall\n");
		fileWriter.close();
	}
	public void print_int(String t)
	{
		System.out.println("************MIPS PRINTING******");
		fileWriter.format("\tmove $a0,%s\n",t);
		fileWriter.format("\tli $v0,1\n");
		fileWriter.format("\tsyscall\n");
		fileWriter.format("\tli $a0,32\n");
		fileWriter.format("\tli $v0,11\n");
		fileWriter.format("\tsyscall\n");
	}
	//public TEMP addressLocalVar(int serialLocalVarNum)
	//{
	//	TEMP t  = TEMP_FACTORY.getInstance().getFreshTemp();
	//	int idx = t.getSerialNumber();
	//
	//	fileWriter.format("\taddi Temp_%d,$fp,%d\n",idx,-serialLocalVarNum*WORD_SIZE);
	//	
	//	return t;
	//}
	public void allocate(String var_name,int value)
	{
		fileWriter.format(".data\n");
		fileWriter.format("\tglobal_%s: .word %d\n",var_name, value);
	}
	public void load(String dst,String var_name)
	{
		fileWriter.format("\tla %s,global_%s\n",dst,var_name);
	}
	public void store(String var_name,String src)
	{
		fileWriter.format("\tsw %s,global_%s\n",src,var_name);		
	}
	public void load_label(String dst,String var_name)
	{
		fileWriter.format("\tlw %s,global_%s\n",dst,var_name);
	}
	public void li(String t,int value)
	{
		fileWriter.format("\tli %s, %d\n",t,value);
	}
	public void la(String dst,String src)
	{
		fileWriter.format("\tla %s, %s\n",dst,src);
	}
	public void sb(String dst,String src,int offset)
	{
		fileWriter.format("\tsb %s, %d(%s)\n",dst,offset,src);
	}
	public void lb(String dst,String src,int offset)
	{
		fileWriter.format("\tlb %s, %d(%s)\n",dst,offset,src);
	}
	public void allocate_func(String var_name)
	{
		fileWriter.format("\t.word %s\n",var_name);
	}
	public void allocate_string(String var_name, String value)
	{
		fileWriter.format(".data\n");
		fileWriter.format("\t%s:\n",var_name);
		fileWriter.format("\t .asciiz \"%s\"\n",value);
	}
	public void add(String dst,String oprnd1,String oprnd2)
	{
		fileWriter.format("\tadd %s,%s,%s\n",dst,oprnd1,oprnd2);
	}
	public void sub(String dst,String oprnd1,String oprnd2)
	{
		fileWriter.format("\tsub %s,%s,%s\n",dst,oprnd1,oprnd2);
	}
	public void mul(String dst,String oprnd1,String oprnd2)
	{
		fileWriter.format("\tmul %s,%s,%s\n",dst,oprnd1,oprnd2);
	}
	public void addi(String dst, String src, int val) {
		fileWriter.format("\taddi %s,%s,%d\n", dst, src, val);
	}
	public void addu(String dst, String src, int val) {
		fileWriter.format("\taddu %s,%s,%d\n", dst, src, val);
	}
	public void subu(String dst,String src,int offset)
	{
		fileWriter.format("\tsubu %s,%s,%d\n",dst,src,offset);
	}
	public void label(String inlabel)
	{
		if (inlabel.equals("main"))
		{
			fileWriter.format(".text\n");
			fileWriter.format("%s:\n",inlabel);
		}
		else
		{
			fileWriter.format("%s:\n",inlabel);
		}
	}	
	public void label_text(String inlabel)
	{
		fileWriter.format(".text\n");
		fileWriter.format("%s:\n",inlabel);
	}	
	public void label_data(String inlabel)
	{
		fileWriter.format(".data\n");
		fileWriter.format("%s:\n",inlabel);
	}	
	public void jump(String inlabel)
	{
		fileWriter.format("\tj %s\n",inlabel);
	}	
	public void blt(String oprnd1,String oprnd2,String label)
	{
		fileWriter.format("\tblt %s,%s,%s\n",oprnd1,oprnd2,label);				
	}	
	public void bge(String oprnd1,String oprnd2,String label)
	{
		fileWriter.format("\tbge %s,%s,%s\n",oprnd1,oprnd2,label);				
	}	
	public void ble(String oprnd1,String oprnd2,String label)
	{
		fileWriter.format("\tble %s,%s,%s\n",oprnd1,oprnd2,label);				
	}	
	public void bne(String oprnd1,String oprnd2,String label)
	{
		fileWriter.format("\tbne %s,%s,%s\n",oprnd1,oprnd2,label);				
	}
	public void beq(String oprnd1,String oprnd2,String label)
	{
		fileWriter.format("\tbeq %s,%s,%s\n",oprnd1,oprnd2,label);				
	}
	public void beqz(String oprnd1,String label)
	{		
		fileWriter.format("\tbeqz %s,%s\n",oprnd1,label);				
	}
	public void bltz(String oprnd1,String label)
	{		
		fileWriter.format("\tbltz %s,%s\n",oprnd1,label);				
	}
	public void move(String oprnd1, String oprnd2)
	{
		fileWriter.format("\tmove %s, %s\n",oprnd1,oprnd2);
	}
	public void sll(String dst,String oprnd1,int times)
	{
		fileWriter.format("\tsll %s,%s,%d\n",dst,oprnd1,times);
	}
	public void jr(String dst)
	{
		fileWriter.format("\tjr %s\n",dst);				
	}
	public void jal(String label)
	{
		fileWriter.format("\tjal %s\n",label);				
	}
	public void jalr(String dst)
	{
		fileWriter.format("\tjalr %s\n",dst);				
	}
	public void push_to_stack(String var)
	{
		fileWriter.format("\tsubu $sp,$sp,4\n");
		fileWriter.format("\tsw %s,0($sp)\n", var);		
	}
	public void lw(String dst,String src, int offset)
	{
		fileWriter.format("\tlw %s, %d(%s)\n",dst,offset,src);
	}
	public void sw(String src,String dst, int offset)
	{
		fileWriter.format("\tsw %s, %d(%s)\n",src,offset,dst);
	}
	public int pushList(TEMP_LIST lst){
		if(lst == null){
			return 0;
		}
		int stack_offset = pushList(lst.next);
		push_to_stack(lst.value.toString());
		return stack_offset + 4;
	}
	public void pop_from_stack(String var)
	{
		fileWriter.format("\tlw %s,0($sp)\n", var);
		fileWriter.format("\taddu $sp,$sp,4\n");
	}
	public void malloc()
	{
		fileWriter.format("\tli $v0,9\n");
		fileWriter.format("\tsyscall\n");
	}
	public void malloc_num_bytes(int numOfBytes)
	{
		fileWriter.format("\tli $a0,%d\n",numOfBytes);
		fileWriter.format("\tli $v0,9\n");
		fileWriter.format("\tsyscall\n");
	}
	public void text_segment(){
		fileWriter.format(".text\n");
	}
	public void store_word(String src,String dst, int offset)
	{
		fileWriter.format("\tsw %s, %d(%s)\n",src,offset,dst);
	}
	public void funcPrologue(){
		for(int i=0;i<10;i++){
			fileWriter.format("\tsubu $sp,$sp,4\n");
			fileWriter.format("\tsw $t%d,0($sp)\n", i);
		}
	}
	public void funcEpilogue(){
		for(int i=0;i<10;i++){
			fileWriter.format("\tlw $t%d,0($sp)\n", 9-i);
			fileWriter.format("\taddu $sp,$sp,4\n");			
		}
	}
	public void div(String dst,String oprnd1,String oprnd2)
	{
		fileWriter.format("\tdiv %s,%s,%s\n",dst,oprnd1,oprnd2);
	}
	public void print_string(){
		fileWriter.format("\tli $v0,4\n");
		fileWriter.format("\tsyscall\n");
	}
	public void exit()
	{
		fileWriter.print("\tli $v0,10\n");
		fileWriter.print("\tsyscall\n");
	}
	
	/**************************************/
	/* USUAL SINGLETON IMPLEMENTATION ... */
	/**************************************/
	private static MIPSGenerator instance = null;

	/*****************************/
	/* PREVENT INSTANTIATION ... */
	/*****************************/
	protected MIPSGenerator() {}

	/******************************/
	/* GET SINGLETON INSTANCE ... */
	/******************************/
	public static MIPSGenerator getInstance()
	{
		if (instance == null)
		{
			/*******************************/
			/* [0] The instance itself ... */
			/*******************************/
			instance = new MIPSGenerator();

			try
			{
				/*********************************************************************************/
				/* [1] Open the MIPS text file and write data section with error message strings */
				/*********************************************************************************/
				String dirname="./output/";
				String filename=String.format("MIPS.txt");

				/***************************************/
				/* [2] Open MIPS text file for writing */
				/***************************************/
				instance.fileWriter = new PrintWriter(dirname+filename);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			/*****************************************************/
			/* [3] Print data section with error message strings */
			/*****************************************************/
						instance.fileWriter.print(".data\n");
			instance.fileWriter.print("string_access_violation: .asciiz \"Access Violation\"\n");
			instance.fileWriter.print("string_illegal_div_by_0: .asciiz \"Division By Zero\"\n");
			instance.fileWriter.print("string_invalid_ptr_dref: .asciiz \"Invalid Pointer Dereference\"\n");
			instance.fileWriter.format("max: .word %d\n",32767);
			instance.fileWriter.format("min: .word %d\n",-32768);
			instance.label_text("abort_pointer");
			instance.la("$a0","string_invalid_ptr_dref");
			instance.print_string();
			instance.exit();
			instance.label_text("abort_array");
			instance.la("$a0","string_access_violation");
			instance.print_string();
			instance.exit();
		}
		return instance;
	}
}
