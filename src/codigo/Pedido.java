
package codigo;

import java.util.*;
/**
 * A classe pedido é responsável pela reunião de todos os atributos
 * do programa, usando das ferramentas de agregação com as classes Pagamento e
 * caixa, e composição com as classes Garcom e Produto.
 * @author Marcos Lucas,Nayara,Nilvando
 * @version 1.0
 */

public class Pedido {

    private static int numeroTodos = 1;
    private boolean pedido_aberta = false;
    private int numero;
    private LinkedList<Integer> qtde = new LinkedList<Integer>();
    private LinkedList<Produto> produtos = new LinkedList<Produto>();
    private Garcom garcons;
    private Pagamento pagamento;
    private String data;
    private String hora;

    public Pedido(Garcom garcons, String data) {
        numero = numeroTodos;
        numeroTodos++;
        this.garcons = garcons;
        pedido_aberta = true;
        this.data = data;
    }

    public void setProdutosEQuantidade(LinkedList<Integer> i, LinkedList<Produto> p) {
        this.qtde = i;
        this.produtos = p;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public String getHora(){
        return hora;
    }

    public String getDia() {
        return data.substring(0, 3);
    }

    public String getMes() {
        return data.substring(3, 6);
    }

    public String getAno() {
        return data.substring(6, 11);
    }

    public void setPedidoAberta(boolean pedido_aberto) {
        this.pedido_aberta = pedido_aberto;
    }

    public boolean getPedidoAberto() {
        return pedido_aberta;
    }

    public Garcom getGarcons() {
        return garcons;
    }

    public void setGarcons(Garcom garcons) {
        this.garcons = garcons;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Integer getQuantiade(int indice) {
        return qtde.get(indice);
    }

    public Produto getProdutos(int indice) {
        return produtos.get(indice);
    }

    public void setProdutos(Produto produto) {
        produtos.add(produto);
    }

    public Integer getQtde(int i) {
        return qtde.get(i);
    }

    public void setQtde(Integer qtd) {
        qtde.add(qtd);
    }
/**
 * metodo que altera a quantidade de um produto a partir do indice do produto no pedido
 * @param indice codigo pedido
 * @param nova_quantidade quantidade do pedido
 */
    public void alteraQtde(int indice, Integer nova_quantidade) {
       qtde.set(indice, nova_quantidade);                        
    }
/**
 * metodo para adicionar um novo produto
 * @param p produto
 * @param quantidade produto
 */
    public void adicionaProduto(Produto p, Integer quantidade) {
        produtos.add(p);
        qtde.add(quantidade);
    }

    public double getPagamentoValorPedido() {
        return pagamento.getValorPedido();
    }

    public double getPagamentoValorRecebido() {
        return pagamento.getValorRecebido();
    }

    public int getCodigoGarcon() {

        return garcons.getCodigo();

    }

    /**
     * método retorna todos os produtos da pedido
     * @return produtos
     */
    public LinkedList<Produto> retornaProdutos() {
        return produtos;
    }

    /**
     * método para calcular preoço do pedido
     * @return produtos
     */
    public double calculaValorpedido() {

        double valor = 0;
        int indice = 0;

        for (Produto p : produtos) {

            valor += p.getPreco() * qtde.get(indice);

            indice++;

        }

        return valor;

    }

    /**
     * Método para imprimir a quantidade, codigo e nome de cada produto do pedido e o valor atual da mesma.
     */
    public String toString() {

        String produtos_e_quantidade = "";
        int indice = 0;

//For-each para varrer o Linkedlist produto 
        for (Produto p : produtos) {

            if (qtde.get(indice) != 0) {

                produtos_e_quantidade = produtos_e_quantidade + "ID do produto: " + p.getCodigo();
                produtos_e_quantidade = produtos_e_quantidade + " | Nome do produto: " + p.getNome();
                produtos_e_quantidade = produtos_e_quantidade + " | Quantidade: " + qtde.get(indice);
                produtos_e_quantidade = produtos_e_quantidade + "\n";

            }

            indice++;

        }

        return "Nome do Garçom: " + garcons.getNome() + "\nID Garçom: " + garcons.getCodigo() + "\nNúmero da pedido: " + numero + "\n\n-- Produtos e quantidade --\n\n" + produtos_e_quantidade + "\nValor atual da pedido: " + calculaValorpedido();
    }
}