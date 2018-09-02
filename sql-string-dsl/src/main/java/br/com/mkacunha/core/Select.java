package br.com.mkacunha.core;

public class Select extends Element {

    public Select() {
        super(null);
        this.operation.append(" select ");
    }

    public static Select start() {
        return new Select();
    }

    public Select all() {
        this.operation.append(" * ");
        return this;
    }

    public Select columns(String... collumn) {
        this.operation.append(String.join(", ", collumn));
        return this;
    }

    public From from(String... from) {
        return new From(this, from);
    }

}
