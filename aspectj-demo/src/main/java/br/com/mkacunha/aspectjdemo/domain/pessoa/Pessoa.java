package br.com.mkacunha.aspectjdemo.domain.pessoa;

import java.util.Objects;

public class Pessoa {

    private Integer id;

    private String nome;

    private String dataNascimento;

    private Integer idade;

    protected Pessoa() {
    }

    public Pessoa(Integer id, String nome, String dataNascimento, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }


    public Integer getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pessoa{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", dataNascimento='").append(dataNascimento).append('\'');
        sb.append(", idade=").append(idade);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id) &&
                Objects.equals(nome, pessoa.nome) &&
                Objects.equals(dataNascimento, pessoa.dataNascimento) &&
                Objects.equals(idade, pessoa.idade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, dataNascimento, idade);
    }
}
