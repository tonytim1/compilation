package IR;


public class IRcommand_List {
    public IRcommand head;
    public IRcommand_List tail;

    IRcommand_List(IRcommand head, IRcommand_List tail) {
        this.head = head;
        this.tail = tail;
    }

    public void MIPSme() {
        if (head != null) {
            head.MIPSme();
        }
        if (tail != null) {
            tail.MIPSme();
        }
    }
}
