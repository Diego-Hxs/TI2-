package model;

public class Carro {
    private int codigo;
    private String nome;
    private String tipo;

    public Carro() {
        this.codigo = -1;
        this.nome = "";
        this.tipo = "";
    }

    public Carro(int codigo, String nome, String tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Carro [Codigo=" + codigo + ", Nome=" + nome + ", Tipo=" + tipo + "]";
    }
}