/*
 * CLASSE PRODUTO
 * Tests
 */
package codigo;

public class Produto {

    private String nome;
    private String descricao;
    private int codigo;
    private int quantidadeVendida;
    private static int codigoDeTodosOsProdutos = 1;
    private double preco;

    //Construtor
    public Produto(String nome, double preco, String descricao) {

        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        codigo = codigoDeTodosOsProdutos;
        codigoDeTodosOsProdutos++;

    }

    public Produto(String nome, String descricao, int codigo, int quantidadeVendida, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.quantidadeVendida = quantidadeVendida;
        this.preco = preco;
        codigoDeTodosOsProdutos = codigo + 1;
    }

    //Construtor utiliza para criar um novo objeto produto
    //para ser feita a consulta
    public Produto() {

        this.nome = "";
        this.preco = 0;
        this.descricao = "";
        this.codigo = 0;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    //metodo para atualizar o produto que teve mais unidades vendidas
    public void atualizaQuantidadeVendida(int novaQuantidadeVendida) {
        this.quantidadeVendida = this.quantidadeVendida + novaQuantidadeVendida;
    }

    //metodo para imprimir o produto mais vendido
    public String toString() {
        return "Nome: " + nome + "\nCódigo: " + codigo + "\nQuantidade vendida: " + quantidadeVendida + "\nDescrição: " + descricao;
    }
}