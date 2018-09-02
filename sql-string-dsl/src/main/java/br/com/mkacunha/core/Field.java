package br.com.mkacunha.core;

public class Field extends Element {

    public Field(Element parent) {
        super(parent);
    }

    public Operator equals(String value) {
        this.operation.append(" = '").append(value).append("'");
        return newOperator();
    }

    public Operator like(String value) {
        this.operation.append(" like '").append(value).append("'");
        return newOperator();
    }

    public Operator between(String first, String two) {
        this.operation.append(" between '").append(first).append("' and '").append(two).append("'");
        return newOperator();
    }

    private Operator newOperator() {
        return new Operator(this);
    }
}
