package br.com.mkacunha.core;

public class App {

    public static void main(String[] args) {
        Select select = new Select();

        String equals = select
                .columns("name", "id", "data")
                .from("pessoa", "endereco")
                .where()
                .field("nome")
                .equals("maiko")
                .and()
                .field("loja")
                .equals("loja do joão")
                .and()
                .field("data")
                .between("01/01/2018", "01/01/2018")
                .or()
                .field("id_pessoa")
                .equals("1").toSql();


        Element from = select
                .columns("name", "id", "data")
                .from("pessoa", "endereco");

        System.out.println(equals);
    }
}
