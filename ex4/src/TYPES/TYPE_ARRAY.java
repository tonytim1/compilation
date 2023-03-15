package TYPES;

public class TYPE_ARRAY extends TYPE {
  public TYPE type;

  /****************/
  /* CTROR(S) ... */
  /****************/
  public TYPE_ARRAY(TYPE type, String name) {
    this.type = type;
    this.name = name;
  }
  public boolean isArray(){ return true;}
}
