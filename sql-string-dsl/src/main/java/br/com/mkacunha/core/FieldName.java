package br.com.mkacunha.core;

public class FieldName extends Element {

    public FieldName(Element parent) {
        super(parent);
    }

    public Field field(String field) {
        this.operation.append(field);
        return new Field(this);
    }

}
