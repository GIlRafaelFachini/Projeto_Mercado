package models;

public class Produto {

    private static int count = 1; // somar mais um na compra sempre
private Integer id;
private String nome;
private Double preco;


    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


    public Produto(String nome, Double preco) {
        this.id = count;
        this.nome = nome;
        this.preco = preco;
        Produto.count += 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Produto{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", preco=").append(preco);
        sb.append('}');
        return sb.toString();
    }
}
