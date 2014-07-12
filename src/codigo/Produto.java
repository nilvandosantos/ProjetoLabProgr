
package codigo;
/**
 * Esta classe tem como objetivo armazenar os dados do produto, como seu codigo,descrição,preço
 * nome e quantidade vendida.
 * 
 * @author Marcos Lucas,Nayara,Nilvando.
 *  
 * @version 1.0
 *
 */
public class Produto {

    private String nome;
    private String descricao;
    private int codigo;
    private int quantidadeVendida;
    private static int codigoDeTodosOsProdutos = 1;
    private double preco;

    //Construtor para cadastro do produto
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

    //Construtor utilizado para criar um novo objeto produto
    //para ser feita a consulta
    public Produto() {

        this.nome = "";
        this.preco = 0;
        this.descricao = "";
        this.codigo = 0;

    }
/**
 *  Metodo utilizado para retornar o codigo do produto.
 * @return int codigo
 */
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
/**
 * Metodo utilizado para retornar descrição do produto (tipo).
 * @return String descrição.
 */
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
/**
 *  Metodo utilizado para retornar o nome do produto.
 *  @return String nome.
 */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
/**
 *  Metodo utilizado para retornar o preço do produto.
 *  @return double Preco
 * 
 */
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
/**
 *  Metodo utilizado para retornar a quantidade que o respectivo produto foi vendido.
 *  @return int  QuantidadeVendida.
 */
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