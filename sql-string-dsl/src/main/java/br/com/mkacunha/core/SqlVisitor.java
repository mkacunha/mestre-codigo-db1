package br.com.mkacunha.core;

public class SqlVisitor {

    private StringBuilder builder = new StringBuilder();

    public void concat(String value){
        builder.append(value);
    }

    public String toSQL() {
        return this.builder.toString();
    }
}
