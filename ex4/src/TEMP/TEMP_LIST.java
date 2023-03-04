package TEMP;

public class TEMP_LIST {
    public TEMP value;
    public TEMP_LIST next;
    public int length = 0;

    public TEMP_LIST(TEMP value, TEMP_LIST next) {
        this.value = value;
        this.next = next;
        if (this.value != null) {
            this.length = 0;
        }
        else {
            this.length = 1;
        }
        if (next != null) {
            this.length += next.length;
        }
    }
}