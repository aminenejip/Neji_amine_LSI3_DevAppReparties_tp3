package operation;

import java.io.Serializable;

public class Operation implements Serializable{
    private int nb1;
    private int nb2;
    private String operator;

    public Operation(int nb1, int nb2, String operator) {
        this.nb1 = nb1;
        this.nb2 = nb2;
        this.operator = operator;
    }

    public int getNb1() {
        return nb1;
    }

    public int getNb2() {
        return nb2;
    }

    public String getOperator() {
        return operator;
    }
}
