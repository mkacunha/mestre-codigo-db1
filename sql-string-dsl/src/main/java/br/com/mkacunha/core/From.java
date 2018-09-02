package br.com.mkacunha.core;

public class From extends Element {

    public From(Element parent, String[] from) {
        super(parent);
        this.operation.append(" from ").append(String.join(", ", from));
    }

    public FieldName where() {
        this.operation.append(" where ");
        return new FieldName(this);
    }
}
