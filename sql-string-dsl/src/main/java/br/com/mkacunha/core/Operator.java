package br.com.mkacunha.core;

public class Operator extends Element  {

    private StringBuilder builder;

    public Operator(Element parent) {
        super(parent);
    }

    public FieldName and() {
        this.operation.append(" and ");
        return new FieldName(this);
    }

    public FieldName or() {
        this.operation.append(" or ");
        return new FieldName(this);
    }

}
