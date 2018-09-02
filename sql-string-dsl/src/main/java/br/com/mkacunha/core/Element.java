package br.com.mkacunha.core;

import static java.util.Optional.ofNullable;

public abstract class Element {

    protected final Element parent;

    protected StringBuilder operation = new StringBuilder();

    public Element(Element parent) {
        this.parent = parent;
    }

    public void accept(SqlVisitor visitor) {
        ofNullable(parent).ifPresent(value -> parent.accept(visitor));
        ofNullable(operation).map(StringBuilder::toString).filter(value -> !value.isEmpty()).ifPresent(value -> visitor.concat(value));
    }

    public String toSql() {
        SqlVisitor visitor = new SqlVisitor();
        this.accept(visitor);
        return visitor.toSQL();
    }
}
