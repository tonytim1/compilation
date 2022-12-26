package AST;

public class SEMANTIC_EXCEPTION extends Exception { 
    public int lineNumber;

    public SEMANTIC_EXCEPTION(int lineNumber) {
        this.lineNumber = lineNumber;    
    }
}