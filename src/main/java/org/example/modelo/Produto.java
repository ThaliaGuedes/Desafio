package org.example.modelo;

public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private Double valorTotal;
    private int quantidade ;

    public Produto( String nome, double preco,  int quantidade, Double valorTotal) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public Produto(int codigo, String nome, double preco, Double valorTotal, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
    }


    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", valorTotal=" + valorTotal +
                ", quantidade=" + quantidade +
                '}';
    }
}
