package AST;

import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.*;
import TEMP.*;
import IR.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class AST_CLASS_DEC_EXTENDS extends AST_CLASS_DEC {
	String fatherName;

	public AST_CLASS_DEC_EXTENDS(String id1, String id2, AST_C_FIELD_LIST data_members, int line) {
		this.id = id1;
		this.father = (TYPE_CLASS) SYMBOL_TABLE.getInstance().find(id2);
		this.fatherName = id2;
		this.data_members = data_members;
		this.line = line;

		System.out.print("=============== classDec -> CLASS ID:id1 EXTENDS ID:id2 LBRACE cFieldList:cl RBRACE\n");

		SerialNumber = AST_Node_Serial_Number.getFresh();

	}


	

	public TYPE SemantMe() {
				this.father = (TYPE_CLASS) SYMBOL_TABLE.getInstance().find(this.fatherName);


		TYPE isExist = SYMBOL_TABLE.getInstance().findInCurrScope(id);
		if (isExist != null) { 			
			printError(line);
		}
		SYMBOL_TABLE.getInstance().beginScope("class-" + id + "-extends-" + this.father.name);

		AST_ARG_LIST fields = null;
		AST_TYPE_NAME_LIST funcs = null;
		TYPE t = null;
		for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
			t = it.head.SemantMe();			AST_TYPE currType = null;

			if (it.head instanceof AST_C_FIELD_VAR_DEC) {
				switch (t.name) {
					case "int": {
						currType = new AST_TYPE_INT(line);
						break;
					}
					case "string": {
						currType = new AST_TYPE_STRING(line);
						break;
					}
					case "void": {
						printError(line);
					}
					default: {
						currType = new AST_TYPE_ID(t.name, line);
						break;
					}
				}
				AST_ARG curr = new AST_ARG(currType, ((AST_C_FIELD_VAR_DEC) it.head).vd.id);
				fields = new AST_ARG_LIST(curr, fields);
			}

			if (it.head instanceof AST_C_FIELD_FUNC_DEC) {
				AST_TYPE_NAME curr = new AST_TYPE_NAME(t, ((AST_C_FIELD_FUNC_DEC) it.head).func.id);
				funcs = new AST_TYPE_NAME_LIST(curr, funcs);
			}
		}

		SYMBOL_TABLE.getInstance().clearScope();
		TYPE_CLASS classType = new TYPE_CLASS(father, id, fields, funcs);
		SYMBOL_TABLE.getInstance().enter(id, classType);
		SYMBOL_TABLE.getInstance().beginScope("class-" + id + "-extends-" + this.father.name);
		for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
			t = it.head.SemantMe(); 		}
		SYMBOL_TABLE.getInstance().endScope();


								
		return null;
	}

	public TEMP IRme() {
		TYPE_CLASS fatherclass = father;
		boolean f = false;
				ArrayList<ArrayList<ArrayList<String>>> fieldlist = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> funclist = new ArrayList<ArrayList<String>>();
		while (fatherclass != null) {
			for (AST_TYPE_NAME_LIST it = fatherclass.functions; it != null; it = it.tail) {
				int n = funclist.size();
				for (int i = 0; i < n; i++) {
					f = false;
					if (((funclist.get(i)).get(0)).equals(it.head.name)) {
						ArrayList<String> temp = funclist.get(i);
						funclist.remove(i);
						funclist.add(0, temp);
						break;
					}
				}
				if (f == false) {
					ArrayList<String> funcOfFather = new ArrayList<String>();
					funcOfFather.add(it.head.name);
					funcOfFather.add(fatherclass.name);
					funclist.add(0, funcOfFather);
				}
			}

			for (AST_ARG_LIST it = fatherclass.data_members; it != null; it = it.tail) {
				f = false;
				int n = fieldlist.size();
				for (int i = 0; i < n; i++) {
					if ((((fieldlist.get(i)).get(0)).get(0)).equals(it.head.id)) {
						f = true;
						ArrayList<ArrayList<String>> temp = fieldlist.get(i);
						fieldlist.remove(i);
						fieldlist.add(0, temp);
						break;
					}
				}
				if (f == true)
					continue;

				ArrayList<String> fieldOfFather = new ArrayList<String>();
				fieldOfFather.add(it.head.id);
				if (it.head.t instanceof AST_TYPE_STRING)
					fieldOfFather.add("1");
				else
					fieldOfFather.add("0");
				ArrayList<ArrayList<String>> field = new ArrayList<ArrayList<String>>();
				field.add(fieldOfFather);
				ArrayList<String> fatherarray = new ArrayList<String>();
				fatherarray.add(fatherclass.name);
				field.add(fatherarray);
				fieldlist.add(0, field);
			}
			fatherclass = fatherclass.father;
		}

						for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
			AST_C_FIELD field = (AST_C_FIELD) (it.head);
			if (field instanceof AST_C_FIELD_VAR_DEC) { 				f = false;
				AST_C_FIELD_VAR_DEC a = (AST_C_FIELD_VAR_DEC) field;
				AST_VAR_DEC b = (AST_VAR_DEC) (a.vd);
				int n = fieldlist.size();
				for (int i = 0; i < n; i++) {
					if ((((fieldlist.get(i)).get(0)).get(0)).equals(b.id)) {
						f = true;
						((fieldlist.get(i)).get(1)).set(0, id);
						break;
					}
				}
				if (f == false) {
					ArrayList<String> fieldOfMe = new ArrayList<String>();
					fieldOfMe.add(b.id);
					if (b.type instanceof AST_TYPE_STRING)
						fieldOfMe.add("1");
					else
						fieldOfMe.add("0");
					ArrayList<ArrayList<String>> field2 = new ArrayList<ArrayList<String>>();
					field2.add(fieldOfMe);
					ArrayList<String> mearray = new ArrayList<String>();
					mearray.add(id);
					field2.add(mearray);
					fieldlist.add(field2);
				}
				continue;
			}
			if (field instanceof AST_C_FIELD_FUNC_DEC) { 				f = false;
				AST_C_FIELD_FUNC_DEC a = (AST_C_FIELD_FUNC_DEC) field;
				AST_FUNC_DEC b = (AST_FUNC_DEC) (a.func);
				int n = funclist.size();
				for (int i = 0; i < n; i++) {
					if (((funclist.get(i)).get(0)).equals(b.id)) {
						f = true;
						(funclist.get(i)).set(1, id);
						break;
					}
				}
				if (f == false) {
					ArrayList<String> funcOfMe = new ArrayList<String>();
					funcOfMe.add(b.id);
					funcOfMe.add(id);
					funclist.add(funcOfMe);
				}

			}
		}

				int funcCnt = funclist.size();
		int fieldCnt = 0;
		Map<String, Integer> funcOff = new HashMap<>();
		ArrayList<String> fields = new ArrayList<>();
		for (int i = 0; i < fieldlist.size(); i++) {
						String off = String.valueOf(fieldCnt * 4 + 4);
			offsets.put(id + "_" + (((fieldlist.get(i)).get(0)).get(0)), off);
			fieldCnt += 1;
			fields.add(((fieldlist.get(i)).get(0)).get(0));
		}
		for (int i = 0; i < funclist.size(); i++) {
			offsets.put(id + "_" + ((funclist.get(i)).get(0)), ((funclist.get(i)).get(1)) + "_" + ((funclist.get(i)).get(0)));
			funcOff.put(((funclist.get(i)).get(0)), i * 4);
		}

		classFuncsOff.put(id, funcOff);

												for (AST_C_FIELD_LIST it = data_members; it != null; it = it.tail) {
			if (it.head instanceof AST_C_FIELD_FUNC_DEC)
				it.head.IRme();
		}
		IR.getInstance().Add_IRcommand(new IRcommand_Declare_Class(id, funclist, fieldlist));
				AST_C_FIELD_LIST temp = data_members;
		for (int i = 0; i < fieldlist.size(); i++) {
			String tf = fieldlist.get(i).get(1).get(0);
			String n = fieldlist.get(i).get(0).get(0);
			if (!(tf.equals(id))) 			{
				String v = defaultFields.get(tf + "_" + n);
				if (v == null)
					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(id + "_" + n));
				else if (fieldlist.get(i).get(0).get(1).equals("1")) 					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_String(id + "_" + n, v));
				else					IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Int(id + "_" + n, Integer.valueOf(v)));
			} else 			{
				for (AST_C_FIELD_LIST it = temp; it != null; it = it.tail) {
					if (it.head instanceof AST_C_FIELD_VAR_DEC &&
							((AST_C_FIELD_VAR_DEC) it.head).vd.id.equals(n)) {
						it.head.IRme();
						temp = data_members;
						break;
					}
				}
			}
		}

		classSize.put(id, fieldlist.size() * 4 + 4);
		classfields.put(id, fields);
				return null;
	}
}
