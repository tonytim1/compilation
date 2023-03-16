package MIPS;

import java.io.PrintWriter;
import java.util.ArrayList;

import IR.*;
import TEMP.*;
import IR.*;

public class MIPSGenerator {
    private PrintWriter fileWriter;

    private static MIPSGenerator instance = null;

    protected MIPSGenerator() {
    }

    public void finalizeFile() {
        fileWriter.print("\t\n");
        fileWriter.print("main:\n");
        fileWriter.print("\tjal user_main\n");
        fileWriter.print("\tli $v0, 10\n");
        fileWriter.print("\tsyscall\n");
        fileWriter.close();
    }

    public void create_new_class(String class_name, ArrayList<ArrayList<String>> funcs) {
        fileWriter.format(".data\n");
        fileWriter.format("vt_%s:\n", class_name);
        if (funcs.size() == 0)
            fileWriter.format("\t.word 0\n");
        else
            for (int i = 0; i < funcs.size(); i++) {
                String n = (funcs.get(i)).get(1) + "_";
                n += (funcs.get(i)).get(0);
                fileWriter.format("\t.word %s\n", n);
            }
        fileWriter.format(".text\n");
    }

    public void field_Access(TEMP t2, int offset, TEMP t1) {
        int id1 = t1.getSerialNumber();
        int id2 = t2.getSerialNumber();

        fileWriter.format("\tbeq $t%d, 0, invalid_pointer_dereference\n", id1);
        fileWriter.format("\tlw $t%d, %d($t%d)\n", id2, offset, id1);

    }

    public void field_set(TEMP t1, int offset, TEMP val) {
        int id1 = t1.getSerialNumber();
        int isValid = val.getSerialNumber();

        fileWriter.format("\tbeq $t%d, 0, invalid_pointer_dereference\n", id1);
        fileWriter.format("\tla $s0, %d($t%d)\n", offset, id1);
        fileWriter.format("\tsw $t%d, 0($s0)\n", isValid);

    }

    public void virtual_call(TEMP dst, TEMP classTemp, int offset, TEMP_LIST args) {
        int cnt = 1;
        fileWriter.format("\tsubu $sp, $sp, 4\n");

        while (args != null) {
            cnt++;
            int num = args.head.getSerialNumber();
            fileWriter.format("\tsw $t%d, 0($sp)\n", num);
            fileWriter.format("\tsubu $sp, $sp, 4\n");
            args = args.tail;
        }
        int classix = classTemp.getSerialNumber();
        fileWriter.format("\tsw $t%d, 0($sp) \n", classix);

        fileWriter.format("\tlw $s0, 0($t%d)\n", classix);
        fileWriter.format("\tlw $s1, %d($s0)\n", offset);
        fileWriter.format("\tjalr $s1\n");
        fileWriter.format("\taddu $sp, $sp,%d\n", 4 * cnt);

        int dstix = dst.getSerialNumber();
        fileWriter.format("\tmove $t%d, $v0\n", dstix);

    }

    public void load_local(TEMP dst, int offset) {
        int destin = dst.getSerialNumber();
        fileWriter.format("\tlw $t%d,%d($fp)\n", destin, offset);
    }

    public void load_label(TEMP dst, String label) {
        int destin = dst.getSerialNumber();
        fileWriter.format("\tlw $t%d, %s\n", destin, label);
    }

    public void load_field_in_func(TEMP dst, int offset) {
        int id = dst.getSerialNumber();
        fileWriter.format("\t lw $s0, 8($fp)\n");
        fileWriter.format("\t lw $t%d, %d($s0)\n", id, offset);
    }

    public void store_local(TEMP dst, int offset) {
        int destin = dst.getSerialNumber();

        fileWriter.format("\tsw $t%d, %d($fp)\n", destin, offset);
    }

    public void store_label(TEMP dst, String label) {
        int destin = dst.getSerialNumber();
        fileWriter.format("\tsw $t%d, %s\n", destin, label);
    }

    public void store_field(int offset, TEMP val) {
        int id = val.getSerialNumber();
        fileWriter.format("\tlw $s0, 8($fp)\n");
        fileWriter.format("\tsw $t%d, %d($s0)\n", id, offset);
    }

    public void prologue(int vars) {
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $ra, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $fp, 0($sp)\n");
        fileWriter.format("\tmove $fp, $sp\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t0, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t1, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t2, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t3, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t4, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t5, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t6, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t7, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t8, 0($sp)\n");
        fileWriter.format("\tsubu $sp, $sp, 4\n");
        fileWriter.format("\tsw $t9, 0($sp)\n");
        fileWriter.format("\tsub $sp, $sp, %d\n", vars * 4);
    }

    public void epilogue() {
        fileWriter.format("\tmove $sp, $fp\n");
        fileWriter.format("\tlw $t0, -4($sp)\n");
        fileWriter.format("\tlw $t1, -8($sp)\n");
        fileWriter.format("\tlw $t2, -12($sp)\n");
        fileWriter.format("\tlw $t3, -16($sp)\n");
        fileWriter.format("\tlw $t4, -20($sp)\n");
        fileWriter.format("\tlw $t5, -24($sp)\n");
        fileWriter.format("\tlw $t6, -28($sp)\n");
        fileWriter.format("\tlw $t7, -32($sp)\n");
        fileWriter.format("\tlw $t8, -36($sp)\n");
        fileWriter.format("\tlw $t9, -40($sp)\n");
        fileWriter.format("\tlw $fp, 0($sp)\n");
        fileWriter.format("\tlw $ra, 4($sp)\n");
        fileWriter.format("\taddu $sp, $sp, 8\n");
        fileWriter.format("\tjr $ra\n");
        fileWriter.format("\n");
    }

    public void ret(TEMP val) {
        if (val != null) {
            int idx = val.getSerialNumber();
            fileWriter.format("\tmove $v0, $t%d\n", idx);
        }
        epilogue();
    }

    public void li(TEMP dst, int val) {
        int idxdst = dst.getSerialNumber();
        fileWriter.format("\tli $t%d, %d\n", idxdst, val);
    }

    public void allocate_const_string(TEMP dst, String label, String val) {
        int idxdst = dst.getSerialNumber();
        fileWriter.format(".data\n");
        fileWriter.format("%s: .asciiz \"%s\"\n", label, val);
        fileWriter.format(".text\n");
        fileWriter.format("\tla $t%d, %s\n", idxdst, label);
    }

    public void checkOverflow(TEMP dst) {
        String label = IRcommand.getFreshLabel("not_over");
        int ind = dst.getSerialNumber();
        int max = (int) Math.pow(2, 15) - 1;

        fileWriter.format("\tli $s0, %d\n", max);
        fileWriter.format("\tbge $s0, $t%d, %s\n", ind, label);
        fileWriter.format("\tli $t%d, %d\n", ind, max);
        fileWriter.format("%s:\n", label);
    }

    public void checkUnderflow(TEMP dst) {
        String label = IRcommand.getFreshLabel("not_under");
        int ind = dst.getSerialNumber();
        int min = -(int) Math.pow(2, 15);

        fileWriter.format("\tli $s0, %d\n", min);
        fileWriter.format("\tble $s0, $t%d, %s\n", ind, label);
        fileWriter.format("\tli $t%d, %d\n", ind, min);
        fileWriter.format("%s:\n", label);
    }

    public void add(TEMP dst, TEMP op1, TEMP op2) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();
        int dstidx = dst.getSerialNumber();
        fileWriter.format("\tadd $t%d,$t%d,$t%d\n", dstidx, i1, i2);
        checkOverflow(dst);
        checkUnderflow(dst);
    }

    public void sub(TEMP dst, TEMP op1, TEMP op2) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();
        int dstidx = dst.getSerialNumber();
        fileWriter.format("\tsub $t%d,$t%d,$t%d\n", dstidx, i1, i2);
        checkUnderflow(dst);
        checkOverflow(dst);
    }

    public void mul(TEMP dst, TEMP op1, TEMP op2) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();
        int dstidx = dst.getSerialNumber();

        fileWriter.format("\tmul $t%d,$t%d,$t%d\n", dstidx, i1, i2);
        checkOverflow(dst);
        checkUnderflow(dst);
    }

    public void div(TEMP dst, TEMP op1, TEMP op2) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();
        int dstidx = dst.getSerialNumber();
        beqz(op2, "division_by_zero");
        fileWriter.format("\tdiv $t%d,$t%d,$t%d\n", dstidx, i1, i2);
        checkUnderflow(dst);
        checkOverflow(dst);
    }

    public void move(TEMP dst, TEMP value) {
        int val = value.getSerialNumber();
        int dstidx = dst.getSerialNumber();
        fileWriter.format("\tmove $t%d,$t%d\n", dstidx, val);
    }

    public void label(String inlabel) {
        fileWriter.format("\n");
        fileWriter.format("%s:\n", inlabel);
    }

    public void jump(String inlabel) {
        fileWriter.format("\tj %s\n", inlabel);
    }

    public void blt(TEMP op1, TEMP op2, String label) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();

        fileWriter.format("\tblt $t%d,$t%d,%s\n", i1, i2, label);
    }

    public void bgt(TEMP op1, TEMP op2, String label) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();

        fileWriter.format("\tbgt $t%d,$t%d,%s\n", i1, i2, label);
    }

    public void bge(TEMP op1, TEMP op2, String label) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();

        fileWriter.format("\tbge $t%d,$t%d,%s\n", i1, i2, label);
    }

    public void ble(TEMP op1, TEMP op2, String label) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();

        fileWriter.format("\tble $t%d,$t%d,%s\n", i1, i2, label);
    }

    public void bne(TEMP op1, TEMP op2, String label) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();

        fileWriter.format("\tbne $t%d,$t%d,%s\n", i1, i2, label);
    }

    public void beq(TEMP op1, TEMP op2, String label) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();

        fileWriter.format("\tbeq $t%d,$t%d,%s\n", i1, i2, label);
    }

    public void beqz(TEMP op1, String label) {
        int i1 = op1.getSerialNumber();

        fileWriter.format("\tbeq $t%d,$zero,%s\n", i1, label);
    }

    public void bnez(TEMP op1, String label) {
        int i1 = op1.getSerialNumber();

        fileWriter.format("\tbne $t%d,$zero,%s\n", i1, label);
    }

    public void load(TEMP op1, TEMP op2, int off) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();
        fileWriter.format("\tlw $t%d, %d($t%d)\n", i1, i2, off);
    }

    public void store(TEMP op1, TEMP op2, int off) {
        int i1 = op1.getSerialNumber();
        int i2 = op2.getSerialNumber();
        fileWriter.format("\tsw $t%d, %d($t%d)\n", i1, i2, off);
    }

    public void concat_string(TEMP dst, TEMP str1, TEMP str2, String[] labels) {
        int idx1 = str1.getSerialNumber();
        int idx2 = str2.getSerialNumber();
        int idxdst = dst.getSerialNumber();

        MIPSGenerator generator = MIPSGenerator.getInstance();
        
        generator.label(labels[0]);
        fileWriter.format("\tmove $s0, $t%d\n", idx1);
        fileWriter.format("\tmove $s1, $t%d\n", idx2);
        fileWriter.format("\tli $s2, 0\n");
        generator.label(labels[1]);
        fileWriter.format("\tlb $s3, 0($s0)\n");
        fileWriter.format("\tbeq $s3, 0, %s\n", labels[2]);
        fileWriter.format("\taddu $s0, $s0, 1\n");
        fileWriter.format("\taddu $s2, $s2, 1\n");
        generator.jump(labels[1]);
        generator.label(labels[2]);
        fileWriter.format("\tlb $s3, 0($s1)\n");
        fileWriter.format("\tbeq $s3, 0, %s\n", labels[3]);
        fileWriter.format("\taddu $s1, $s1, 1\n");
        fileWriter.format("\taddu $s2, $s2, 1\n");
        generator.jump(labels[2]);
        generator.label(labels[3]);
        fileWriter.format("\taddu $s2, $s2, 1\n");
        fileWriter.format("\tmove $a0, $s2\n", idx1);
        fileWriter.format("\tli $v0, 9\n");
        instance.fileWriter.format("\tsyscall\n");
        fileWriter.format("\tmove $s0, $t%d\n", idx1);
        fileWriter.format("\tmove $s1, $t%d\n", idx2);
        fileWriter.format("\tmove $s2, $v0\n");
        generator.label(labels[4]);
        fileWriter.format("\tlb $s3, 0($s0)\n");
        fileWriter.format("\tbeq $s3, 0, %s\n", labels[5]);
        fileWriter.format("\tsb $s3, 0($s2)\n");
        fileWriter.format("\taddu $s0, $s0, 1\n");
        fileWriter.format("\taddu $s2, $s2, 1\n");
        generator.jump(labels[4]);
        generator.label(labels[5]);
        fileWriter.format("\tlb $s3, 0($s1)\n");
        fileWriter.format("\tbeq $s3, 0, %s\n", labels[6]);
        fileWriter.format("\tsb $s3, 0($s2)\n");
        fileWriter.format("\taddu $s1, $s1, 1\n");
        fileWriter.format("\taddu $s2, $s2, 1\n");
        generator.jump(labels[5]);
        generator.label(labels[6]);
        fileWriter.format("\taddu $s2, $s2, 1\n");
        fileWriter.format("\tli $s3, 0\n");
        fileWriter.format("\tsb $s3, 0($s2)\n");
        fileWriter.format("\tmove $t%d, $v0\n", idxdst);
    }



    public static MIPSGenerator getInstance() {
        if (instance == null) {
            instance = new MIPSGenerator();

            try {
                String output = "./output/MIPS.txt";
                instance.fileWriter = new PrintWriter(output);
            } catch (Exception e) {
                e.printStackTrace();
            }

            instance.fileWriter.print(".data\n");
            instance.fileWriter.print("string_access_violation: .asciiz \"Access Violation\"\n");
            instance.fileWriter.print("string_invalid_ptr_dref: .asciiz \"Invalid Pointer Dereference\"\n");
            instance.fileWriter.print("string_illegal_div_by_0: .asciiz \"Division By Zero\"\n");
            instance.fileWriter.print("string_space: .asciiz \" \"\n");
            instance.fileWriter.print("string_tab: .asciiz \"\t\"\n");
            instance.fileWriter.format("\n.text\n");
            instance.label("division_by_zero");
            instance.fileWriter.format("\tla $a0, string_illegal_div_by_0\n");
            instance.fileWriter.format("\tli $v0, 4\n");
            instance.fileWriter.format("\tsyscall\n");
            instance.fileWriter.format("\tli $v0, 10\n");
            instance.fileWriter.format("\tsyscall\n");
            instance.label("invalid_pointer_dereference");
            instance.fileWriter.format("\tla $a0, string_invalid_ptr_dref\n");
            instance.fileWriter.format("\tli $v0,4\n");
            instance.fileWriter.format("\tsyscall\n");
            instance.fileWriter.format("\tli $v0,10\n");
            instance.fileWriter.format("\tsyscall\n");
            instance.label("out_of_bound");
            instance.fileWriter.format("\tla $a0, string_access_violation\n");
            instance.fileWriter.format("\tli $v0, 4\n");
            instance.fileWriter.format("\tsyscall\n");
            instance.fileWriter.print("\tli $v0, 10\n");
            instance.fileWriter.print("\tsyscall\n");

            instance.fileWriter.format(".text\n");
            generator.label("PrintInt");
            generator.prologue(0);
            instance.fileWriter.format("\tlw $a0, 8($fp)\n");
            instance.fileWriter.format("\tli $v0, 1\n");
            instance.fileWriter.format("\tsyscall\n");
            instance.fileWriter.format("\tla $a0, string_space\n");
            instance.fileWriter.format("\tli $v0, 4\n");
            instance.fileWriter.format("\tsyscall\n");
            generator.epilogue();

            generator.label("PrintString");
            generator.prologue(0);
            instance.fileWriter.format("\tlw $a0, 8($fp)\n");
            instance.fileWriter.format("\tli $v0, 4\n");
            instance.fileWriter.format("\tsyscall\n");
            generator.epilogue();

        }
        return instance;
    }

    public void array_access(TEMP t0, TEMP t1, TEMP t2) {
        int t0_ind = t0.getSerialNumber();
        int t1_ind = t1.getSerialNumber();
        int t2_ind = t2.getSerialNumber();

        fileWriter.format("\tbeq $t%d, 0, invalid_pointer_dereference\n", t1_ind);

        fileWriter.format("\tbltz $t%d, out_of_bound\n", t2_ind);
        fileWriter.format("\tlw $s0, 0($t%d)\n", t1_ind);
        fileWriter.format("\tbge $t%d, $s0, out_of_bound\n", t2_ind);

        fileWriter.format("\tmove $s0, $t%d\n", t2_ind);
        fileWriter.format("\tadd $s0, $s0, 1\n");
        fileWriter.format("\tmul $s0, $s0, 4\n");
        fileWriter.format("\taddu $s0, $t%d, $s0\n", t1_ind);
        fileWriter.format("\tlw $t%d, 0($s0)\n", t0_ind);

    }

    public void new_array(TEMP t0, TEMP t1) {
        int t0_ind = t0.getSerialNumber();
        int t1_ind = t1.getSerialNumber();

        fileWriter.format("\tli $v0, 9\n");
        fileWriter.format("\tmove $a0, $t%d\n", t1_ind);
        fileWriter.format("\tadd $a0, $a0, 1\n");
        fileWriter.format("\tmul $a0, $a0, 4\n");
        fileWriter.print("\tsyscall\n");
        fileWriter.format("\tmove $t%d, $v0\n", t0_ind);
        fileWriter.format("\tsw $t%d, 0($t%d)\n", t1_ind, t0_ind);
    }

    public void new_class(TEMP t0, String className, int size) {
        int t0_ind = t0.getSerialNumber();

        fileWriter.format("\tli $v0, 9\n");
        fileWriter.format("\tli $a0, %d\n", size);
        fileWriter.print("\tsyscall\n");
        fileWriter.format("\tmove $t%d, $v0\n", t0_ind);
        fileWriter.format("\tla $s0, vt_%s\n", className);
        fileWriter.format("\tsw $s0, 0($t%d)\n", t0_ind);

    }

    public void set_field_default(TEMP t0, String label, int offset) {
        int t0_ind = t0.getSerialNumber();

        fileWriter.format("\tlw $s0, %s\n", label);
        fileWriter.format("\tsw $s0, %d($t%d)\n", offset, t0_ind);
    }

    public void call_func(TEMP t, String startLabel, TEMP_LIST reversedTempList) {
        int cnt = 0;
        int tInd = t.getSerialNumber();

        for (TEMP_LIST it = reversedTempList; it != null; it = it.tail) {
            cnt++;
            TEMP curr = it.head;
            int currInd = curr.getSerialNumber();

            fileWriter.format("\tsubu $sp, $sp, 4\n");
            fileWriter.format("\tsw $t%d, 0($sp)\n", currInd);
        }
        fileWriter.format("\tjal %s\n", startLabel);
        fileWriter.format("\taddu $sp, $sp, %d\n", 4 * cnt);
        fileWriter.format("\tmove $t%d, $v0\n", tInd);
    }

    public void declare_global_string(String label, String id, String value) {
        fileWriter.format(".data\n");
        fileWriter.format("%s: .asciiz \"%s\"\n", label, value);
        fileWriter.format("%s: .word %s\n", id, label);
        fileWriter.format("\n.text\n");
    }

    public void declare_global_int(String id, int value) {
        fileWriter.format(".data\n");
        fileWriter.format("%s: .word %d\n", id, value);
        fileWriter.format("\n.text\n");
    }

    public void declare_global_object(String id) {
        fileWriter.format(".data\n");
        fileWriter.format("%s: .word 0\n", id);
        fileWriter.format("\n.text\n");
    }

    public void eq_strings(TEMP dst, TEMP t1, TEMP t2, String[] labels) {
        int dstInd = dst.getSerialNumber();
        int t1Ind = t1.getSerialNumber();
        int t2Ind = t2.getSerialNumber();

        fileWriter.format("\tli $t%d, 1\n", dstInd);
        fileWriter.format("\tmove $s0, $t%d\n", t1Ind);
        fileWriter.format("\tmove $s1, $t%d\n", t2Ind);

        label(labels[0]);
        fileWriter.format("\tlb $s2, 0($s0)\n");
        fileWriter.format("\tlb $s3, 0($s1)\n");
        fileWriter.format("\tbne $s2, $s3, %s\n", labels[1]);
        fileWriter.format("\tbeq $s2, 0, %s\n", labels[2]);
        fileWriter.format("\taddu $s0, $s0, 1\n");
        fileWriter.format("\taddu $s1, $s1, 1\n");
        jump(labels[0]);
        label(labels[1]);
        fileWriter.format("\tli $t%d, 0\n", dstInd);
        label(labels[2]);
    }

    public void array_set(TEMP array, TEMP index, TEMP val) {
        int arrayInd = array.getSerialNumber();
        int indexInd = index.getSerialNumber();
        int valInd = val.getSerialNumber();

        fileWriter.format("\tbeq $t%d, 0, invalid_pointer_dereference\n", arrayInd);
        fileWriter.format("\tbltz $t%d, out_of_bound\n", indexInd);
        fileWriter.format("\tlw $s0, 0($t%d)\n", arrayInd);
        fileWriter.format("\tbge $t%d, $s0, out_of_bound\n", indexInd);
        fileWriter.format("\tmove $s0, $t%d\n", indexInd);
        fileWriter.format("\tadd $s0, $s0, 1\n");
        fileWriter.format("\tmul $s0, $s0, 4\n");
        fileWriter.format("\taddu $s0, $t%d, $s0\n", arrayInd);
        fileWriter.format("\tsw $t%d, 0($s0)\n", valInd);
    }
}
