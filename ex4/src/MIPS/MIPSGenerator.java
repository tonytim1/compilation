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
		int idx=t.getSerialNumber();
		// fileWriter.format("\taddi $a0,Temp_%d,0\n",idx);
		fileWriter.format("\tmove $a0,Temp_%d\n",idx);
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
	public void allocate(String var_name)
	{
		fileWriter.format(".data\n");
		fileWriter.format("\tglobal_%s: .word 721\n",var_name);
	}
	public void load(String dst,String var_name)
	{
		int idxdst=dst.getSerialNumber();
		fileWriter.format("\tlw Temp_%d,global_%s\n",idxdst,var_name);
	}
	public void store(String var_name,String src)
	{
		int idxsrc=src.getSerialNumber();
		fileWriter.format("\tsw Temp_%d,global_%s\n",idxsrc,var_name);		
	}
	public void li(String t,int value)
	{
		int idx=t.getSerialNumber();
		fileWriter.format("\tli Temp_%d,%d\n",idx,value);
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
		int i1 =oprnd1.getSerialNumber();
		int i2 =oprnd2.getSerialNumber();
		int dstidx=dst.getSerialNumber();

		fileWriter.format("\tadd Temp_%d,Temp_%d,Temp_%d\n",dstidx,i1,i2);
	}
	public void sub(String dst,String oprnd1,String oprnd2)
	{
		int i1 =oprnd1.getSerialNumber();
		int i2 =oprnd2.getSerialNumber();
		int dstidx=dst.getSerialNumber();

		fileWriter.format("\tsubu Temp_%d,Temp_%d,Temp_%d\n",dstidx,i1,i2);
	}
	public void mul(String dst,String oprnd1,String oprnd2)
	{
		int i1 =oprnd1.getSerialNumber();
		int i2 =oprnd2.getSerialNumber();
		int dstidx=dst.getSerialNumber();

		fileWriter.format("\tmul Temp_%d,Temp_%d,Temp_%d\n",dstidx,i1,i2);
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
	public void ble(String oprnd1,String oprnd2,String label)
	{
		fileWriter.format("\tble %s,%s,%s\n",oprnd1,oprnd2,label);				
	}
	public void blt(String oprnd1,String oprnd2,String label)
	{
		int i1 =oprnd1.getSerialNumber();
		int i2 =oprnd2.getSerialNumber();
		
		fileWriter.format("\tblt Temp_%d,Temp_%d,%s\n",i1,i2,label);				
	}
	public void bge(String oprnd1,String oprnd2,String label)
	{
		int i1 =oprnd1.getSerialNumber();
		int i2 =oprnd2.getSerialNumber();
		
		fileWriter.format("\tbge Temp_%d,Temp_%d,%s\n",i1,i2,label);				
	}
	public void bne(String oprnd1,String oprnd2,String label)
	{
		int i1 =oprnd1.getSerialNumber();
		int i2 =oprnd2.getSerialNumber();
		
		fileWriter.format("\tbne Temp_%d,Temp_%d,%s\n",i1,i2,label);				
	}
	public void beq(String oprnd1,String oprnd2,String label)
	{
		int i1 =oprnd1.getSerialNumber();
		int i2 =oprnd2.getSerialNumber();
		
		fileWriter.format("\tbeq Temp_%d,Temp_%d,%s\n",i1,i2,label);				
	}
	public void beqz(String oprnd1,String label)
	{
		int i1 =oprnd1.getSerialNumber();
				
		fileWriter.format("\tbeq Temp_%d,$zero,%s\n",i1,label);				
	}
	public void move(String oprnd1, String oprnd2)
	{
		fileWriter.format("\tmove %s, %s\n",dst,src);
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
	public void bltz(String oprnd1,String label)
	{		
		fileWriter.format("\tbltz %s,%s\n",oprnd1,label);				
	}
	public void print_string(){
		fileWriter.format("\tli $v0,4\n");
		fileWriter.format("\tsyscall\n");
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
			instance.fileWriter.print("string_illegal_div_by_0: .asciiz \"Illegal Division By Zero\"\n");
			instance.fileWriter.print("string_invalid_ptr_dref: .asciiz \"Invalid Pointer Dereference\"\n");
		}
		return instance;
	}
}
