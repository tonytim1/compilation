package AST;
import TYPES.*;
import SYMBOL_TABLE.*;
import IR.*;
import TEMP.*;


public class AST_CLASS_DEC extends AST_Node {

	public String id1;
	public String id2;
	public AST_C_FIELD_LIST cFieldList;

	public TYPE_CLASS decClass; //new for IR

	
	public AST_CLASS_DEC(int lineNumber, String id1, String id2, AST_C_FIELD_LIST cFieldList)
	{
		super(lineNumber);
		/******************************/
		/* SET A UNIQUE SERIAL NUMBER */
		/******************************/
		SerialNumber = AST_Node_Serial_Number.getFresh();
		
		/***************************************/
		/* PRINT CORRESPONDING DERIVATION RULE */
		/***************************************/
		if ( id2 != null)
		    System.out.format("====================== classDec -> CLASS ID( %s ) EXTENDS ID( %s ) LBRACE classCont RBRACE", id1, id2);
		else
		    System.out.format("====================== classDec -> CLASS ID( %s ) LBRACE classCont RBRACE", id1);

		/*******************************/
		/* COPY INPUT DATA MEMBERS ... */
		/*******************************/
		this.id1 = id1;
		this.id2 = id2;
		this.cFieldList = cFieldList;
	}

	public TYPE SemantMe() throws SEMANTIC_EXCEPTION
	{	
		SYMBOL_TABLE s = SYMBOL_TABLE.getInstance();
		System.out.format("SEMANT-ME AST_CLASS_DEC %s\n", cFieldList);

		//check if depth != 0 or class name already declared in scope
		if (!(s.isGlobalScope()) || s.find(id1) != null) {
		    System.out.format(">> ERROR [%d] not in global scope or %s is not found - class AST_CLASS_DEC\n",lineNumber, id1);
			throw new SEMANTIC_EXCEPTION(lineNumber);
        }

		/************************************************/
		/* [1] Enter the Class Type to the Symbol Table */
		/************************************************/
		TYPE father = null;
		TYPE_CLASS fatherClass;
		TYPE_LIST fatherDataMembers = new TYPE_LIST(null, null);
		TYPE_LIST currList;

		if ( id2 != null)
		{
			father = s.find(id2);
			if (father == null || father.typeName != "class") {
				System.out.format(">> ERROR [%d] can't find father %s or father's type isn't class - class AST_CLASS_DEC\n",lineNumber, id2);
				throw new SEMANTIC_EXCEPTION(lineNumber);
			}
			fatherClass = (TYPE_CLASS) father;
			fatherDataMembers = (TYPE_LIST) fatherClass.data_members;
		}

		TYPE_LIST allDataMembersHolder = fatherDataMembers;
		TYPE_CLASS classType = new TYPE_CLASS((TYPE_CLASS) father, id1, fatherDataMembers); // starts with father's data members
		System.out.format("creating class type %s\n",id1);
		s.getInstance().enter(id1, classType);

		/*************************/
		/* [2] Begin Class Scope */
		/*************************/
		s.beginScope();
		s.curr_class = classType;

		/*************************/
		/* [3] Add the class data members one by one */
		/*************************/
		TYPE newClassAttr = null;
		AST_C_FIELD_LIST head_copy = cFieldList;
		while ((head_copy != null) && (head_copy.cField != null)) {
			newClassAttr = head_copy.cField.SemantMe();
			// add to father
			if (fatherDataMembers.head == null) {
				fatherDataMembers.head = newClassAttr;
			} else {
				while (fatherDataMembers.tail != null) {
					fatherDataMembers = fatherDataMembers.tail;
				}
				fatherDataMembers.tail = new TYPE_LIST(newClassAttr, null);
			}
			// next variable
			head_copy = head_copy.cFieldList;

			// print
			System.out.format("print list %s\n", "all");
			currList = allDataMembersHolder;
			while (currList.head != null) {
				System.out.format(" - %s, %s: %s\n", currList.head.varName, currList.head.typeName, currList.head);
				if (currList.tail == null) {
					break;
				}
				currList = currList.tail;
			}
		}

        /*****************/
        /* [4] End Scope */
        /*****************/
		s.curr_class = null;
        s.getInstance().endScope();

        this.decClass = classType; //NEW for IR

		/*********************************************************/
		/* [5] Return value is irrelevant for class declarations */
		/*********************************************************/
		return classType;
	}
	public TEMP IRme() {
		if (id2 == null) {
			ArrayList<ArrayList<String>> funclist = new ArrayList<ArrayList<String>>();
			Map<String, Integer> funcOff = new HashMap<>();
			int fieldCnt = 0;
			int funcCnt = 0;

			for (AST_C_FIELD_LIST it = cFieldList; it != null; it = it.tail) {
				AST_C_FIELD field = (AST_C_FIELD) (it.head);
				if (field instanceof AST_C_FIELD_VAR) { // field
					fieldCnt += 1;
					continue;
				}
				if (field instanceof AST_C_FIELD_FUNC) { // func
					// funcCnt += 1;
					AST_C_FIELD_FUNC a = (AST_C_FIELD_FUNC) field;
					AST_FUNC_DEC b = (AST_FUNC_DEC) (a.func);
					offsets.put(id + "_" + b.id, id + "_" + b.id);
				}

				AST_C_FIELD_FUNC a = (AST_C_FIELD_FUNC) field;
				AST_FUNCDEC b = (AST_FUNCDEC) (a.func);

				ArrayList<String> function = new ArrayList<String>();
				function.add(b.id);
				function.add(id);
				funclist.add(function);
				funcOff.put(b.id, funcCnt * 4);
				funcCnt += 1;
			}
			classFuncsOff.put(id, funcOff);

			ArrayList<ArrayList<ArrayList<String>>> fields = new ArrayList<ArrayList<ArrayList<String>>>();

			fieldCnt = 0;
			ArrayList<String> fieldslist = new ArrayList<>();
			for (AST_C_FEILD_LIST it = cFieldList; it != null; it = it.tail) {
				AST_C_FIELD field = (AST_C_FIELD) (it.head);

				if (field instanceof AST_C_FIELD_VAR) { // field
					// fieldCnt += 1;
					AST_C_FIELD_VAR var = (AST_C_FIELD_VAR) field;
					AST_VAR_DEC b = (AST_VAR_DEC) (var.vd);
					String off = String.valueOf(fieldCnt * 4 + 4);
					offsets.put(id + "_" + b.id, off);
					fieldslist.add(b.id);
					fieldCnt += 1;

					ArrayList<String> field1 = new ArrayList<String>();
					field1.add(b.id);
					if (b.typeName == "string")
						field1.add("1");
					else
						field1.add("0");
					ArrayList<ArrayList<String>> fieldandclass = new ArrayList<ArrayList<String>>();
					fieldandclass.add(field1);
					ArrayList<String> classname = new ArrayList<String>();
					classname.add(id);
					fieldandclass.add(classname);
					fields.add(fieldandclass);
				}
			}
			for (AST_C_FEILD_LIST it = cFieldList; it != null; it = it.tail) {
				if (it.head instanceof AST_C_FEILD_FUNC) {
					it.head.IRme();
				}
			}
			classSize.put(id, fields.size() * 4 + 4);
			IR.getInstance().Add_IRcommand(new IRcommand_declareClass(id, funclist, fields));
			for (AST_C_FEILD_LIST it = cFieldList; it != null; it = it.tail) {
				if (it.head instanceof AST_C_FEILD_VAR)
					it.head.IRme();
			}
			classfields.put(id, fieldslist);
//		} //else {
//			// Gal stopped - TODO
//			TYPE_CLASS fatherclass = (TYPE_CLASS) SYMBOL_TABLE.getInstance().find(id2);
//			boolean f = false;
//			// ############first part (taking father fields and funcs)#################
//			ArrayList<ArrayList<ArrayList<String>>> fieldlist = new ArrayList<ArrayList<ArrayList<String>>>();
//			ArrayList<ArrayList<String>> funclist = new ArrayList<ArrayList<String>>();
//			while (fatherclass != null) {
//				for (AST_TYPE_NAME_LIST it = fatherclass.functions; it != null; it = it.tail) {
//					int n = funclist.size();
//					for (int i = 0; i < n; i++) {
//						f = false;
//						if (((funclist.get(i)).get(0)).equals(it.head.name)) {
//							ArrayList<String> temp = funclist.get(i);
//							funclist.remove(i);
//							funclist.add(0, temp);
//							break;
//						}
//					}
//					if (f == false) {
//						ArrayList<String> funcOfFather = new ArrayList<String>();
//						funcOfFather.add(it.head.name);
//						funcOfFather.add(fatherclass.name);
//						funclist.add(0, funcOfFather);
//					}
//				}
//
//				for (AST_ARG_LIST it = fatherclass.data_members; it != null; it = it.tail) {
//					f = false;
//					int n = fieldlist.size();
//					for (int i = 0; i < n; i++) {
//						if ((((fieldlist.get(i)).get(0)).get(0)).equals(it.head.id)) {
//							f = true;
//							ArrayList<ArrayList<String>> temp = fieldlist.get(i);
//							fieldlist.remove(i);
//							fieldlist.add(0, temp);
//							break;
//						}
//					}
//					if (f == true)
//						continue;
//
//					ArrayList<String> fieldOfFather = new ArrayList<String>();
//					fieldOfFather.add(it.head.id);
//					if (it.head.t instanceof AST_TYPE_STRING)
//						fieldOfFather.add("1");
//					else
//						fieldOfFather.add("0");
//					ArrayList<ArrayList<String>> field = new ArrayList<ArrayList<String>>();
//					field.add(fieldOfFather);
//					ArrayList<String> fatherarray = new ArrayList<String>();
//					fatherarray.add(fatherclass.name);
//					field.add(fatherarray);
//					fieldlist.add(0, field);
//				}
//				fatherclass = fatherclass.father;
//			}
//
//			// #############################################################3
//			// #################second part - this class fields and funcs
//			for (AST_CFEILD_LIST it = data_members; it != null; it = it.tail) {
//				AST_CFIELD field = (AST_CFIELD) (it.head);
//				if (field instanceof AST_CFEILD_VARDEC) { // field
//					f = false;
//					AST_CFEILD_VARDEC a = (AST_CFEILD_VARDEC) field;
//					AST_VARDEC b = (AST_VARDEC) (a.vd);
//					int n = fieldlist.size();
//					for (int i = 0; i < n; i++) {
//						if ((((fieldlist.get(i)).get(0)).get(0)).equals(b.id)) {
//							f = true;
//							((fieldlist.get(i)).get(1)).set(0, id);
//							break;
//						}
//					}
//					if (f == false) {
//						ArrayList<String> fieldOfMe = new ArrayList<String>();
//						fieldOfMe.add(b.id);
//						if (b.type instanceof AST_TYPE_STRING)
//							fieldOfMe.add("1");
//						else
//							fieldOfMe.add("0");
//						ArrayList<ArrayList<String>> field2 = new ArrayList<ArrayList<String>>();
//						field2.add(fieldOfMe);
//						ArrayList<String> mearray = new ArrayList<String>();
//						mearray.add(id);
//						field2.add(mearray);
//						fieldlist.add(field2);
//					}
//					continue;
//				}
//				if (field instanceof AST_CFEILD_FUNCDEC) { // func
//					f = false;
//					AST_CFEILD_FUNCDEC a = (AST_CFEILD_FUNCDEC) field;
//					AST_FUNCDEC b = (AST_FUNCDEC) (a.func);
//					int n = funclist.size();
//					for (int i = 0; i < n; i++) {
//						if (((funclist.get(i)).get(0)).equals(b.id)) {
//							f = true;
//							(funclist.get(i)).set(1, id);
//							break;
//						}
//					}
//					if (f == false) {
//						ArrayList<String> funcOfMe = new ArrayList<String>();
//						funcOfMe.add(b.id);
//						funcOfMe.add(id);
//						funclist.add(funcOfMe);
//					}
//
//				}
//			}
//
//			// #######################part 3 - offsets ################################
//			int funcCnt = funclist.size();
//			int fieldCnt = 0;
//			Map<String, Integer> funcOff = new HashMap<>();
//			ArrayList<String> fields = new ArrayList<>();
//			for (int i = 0; i < fieldlist.size(); i++) {
//				// fieldCnt += 1;
//				String off = String.valueOf(fieldCnt * 4 + 4);
//				offsets.put(id + "_" + (((fieldlist.get(i)).get(0)).get(0)), off);
//				fieldCnt += 1;
//				fields.add(((fieldlist.get(i)).get(0)).get(0));
//			}
//			for (int i = 0; i < funclist.size(); i++) {
//				offsets.put(id + "_" + ((funclist.get(i)).get(0)), ((funclist.get(i)).get(1)) + "_" + ((funclist.get(i)).get(0)));
//				funcOff.put(((funclist.get(i)).get(0)), i * 4);
//			}
//
//			classFuncsOff.put(id, funcOff);
//
//			// System.out.println(fieldlist);
//			// System.out.println(funclist);
//			// #######################part 4 - mips function
//			// ################################
//			// son funcs
//			for (AST_CFEILD_LIST it = data_members; it != null; it = it.tail) {
//				if (it.head instanceof AST_CFEILD_FUNCDEC)
//					it.head.IRme();
//			}
//			IR.getInstance().Add_IRcommand(new IRcommand_declareClass(id, funclist, fieldlist));
//			// fields
//			AST_CFEILD_LIST temp = data_members;
//			for (int i = 0; i < fieldlist.size(); i++) {
//				String tf = fieldlist.get(i).get(1).get(0);
//				String n = fieldlist.get(i).get(0).get(0);
//				if (!(tf.equals(id))) // father fields
//				{
//					String v = defaultFields.get(tf + "_" + n);
//					if (v == null)
//						IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Object(id + "_" + n));
//					else if (fieldlist.get(i).get(0).get(1).equals("1")) // string
//						IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_String(id + "_" + n, v));
//					else// int
//						IR.getInstance().Add_IRcommand(new IRcommand_Declare_Global_Int(id + "_" + n, Integer.valueOf(v)));
//				} else // son feilds
//				{
//					for (AST_CFEILD_LIST it = temp; it != null; it = it.tail) {
//						if (it.head instanceof AST_CFEILD_VARDEC &&
//								((AST_CFEILD_VARDEC) it.head).vd.id.equals(n)) {
//							it.head.IRme();
//							temp = data_members;
//							break;
//						}
//					}
//				}
//			}
//
//			classSize.put(id, fieldlist.size() * 4 + 4);
//			classfields.put(id, fields);
		}
	}
}
