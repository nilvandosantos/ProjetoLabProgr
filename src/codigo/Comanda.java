/*
 * CLASSE COMANDA
 *
 * A classe Comanda é responsável pela reunião de todos os atributos
 * do programa, usando das ferramentas de agregação com as classes Pagamento e
 * caixa, e composição com as classes Garcom e Produto.
 *
 * ATRIBUTOS PRINCIPAIS:
 * *IDENTIDADE - Número inteiro que identifica a mesa;
 * *PRODUTOS - Array do tipo Produto com os produtos consumidos na mesa;
 * *QTDE - Array do tipo Int que determina a quantidade do produto que esteja
 *  localizado na posição equivalente do array PRODUTOS;
 * *GARCOM - Variável do tipo Garcom que diz o nome do garçom atendendo a mesa;
 * *PAGAMENTO - Variável do tipo Pagamento que descreve como foi feito o pagamento;
 * *DATA - String responsável por dizer a data que aquela comanda foi utilizada.
 */
package codigo;

import java.util.*;

public class Comanda {

    private static int numeroTodos = 1;
    private boolean comanda_aberta = false;
    private int numero;
    private LinkedList<Integer> qtde = new LinkedList<Integer>();
    private LinkedList<Produto> produtos = new LinkedList<Produto>();
    private Garcom garcons;
    private Pagamento pagamento;
    private String data;
    private String hora;

    public Comanda(Garcom garcons, String data) {//Construtor
        numero = numeroTodos;
        numeroTodos++;
        this.garcons = garcons;
        comanda_aberta = true;
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

    public String getDia() {//vai de 0 a 3, pois o 3 é a barra de separação
        return data.substring(0, 3);
    }

    public String getMes() {//vai de 3 a 6, pois o 3 é a barra de separação
        return data.substring(3, 6);
    }

    public String getAno() {//vai de 6 a 11, pois o 6 é a barra de separação
        return data.substring(6, 11);
    }

    public void setComandaAberta(boolean comanda_aberta) {
        this.comanda_aberta = comanda_aberta;
    }

    public boolean getComandaAberta() {
        return comanda_aberta;
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

    public void alteraQtde(int indice, Integer nova_quantidade) {//metodo que altera a quantidade de um produto
       qtde.set(indice, nova_quantidade);                        //a partir do indice do produto na comanda
    }

    public void adicionaProduto(Produto p, Integer quantidade) {//metodo para adicionar um novo produto
        produtos.add(p);
        qtde.add(quantidade);
    }

    public double getPagamentoValorComanda() {
        return pagamento.getValorComanda();
    }

    public double getPagamentoValorRecebido() {
        return pagamento.getValorRecebido();
    }

    public int getCodigoGarcon() {

        return garcons.getCodigo();

    }

    //método retorna todos os produtos da comanda
    public LinkedList<Produto> retornaProdutos() {
        return produtos;
    }

    //Método para calcular o preço da comanda
    public double calculaValorComanda() {

        double valor = 0;
        int indice = 0;

        for (Produto p : produtos) {

            valor += p.getPreco() * qtde.get(indice);

            indice++;

        }

        return valor;

    }

    //Método para imprimir a quantidade, codigo e nome de cada produto da comanda e o valor atual da mesma
    public String toString() {

        String produtos_e_quantidade = "";
        int indice = 0;


        for (Produto p : produtos) {

            if (qtde.get(indice) != 0) {

                produtos_e_quantidade = produtos_e_quantidade + "ID do produto: " + p.getCodigo();
                produtos_e_quantidade = produtos_e_quantidade + " | Nome do produto: " + p.getNome();
                produtos_e_quantidade = produtos_e_quantidade + " | Quantidade: " + qtde.get(indice);
                produtos_e_quantidade = produtos_e_quantidade + "\n";

            }

            indice++;

        }

        return "Nome do Garçom: " + garcons.getNome() + "\nID Garçom: " + garcons.getCodigo() + "\nNúmero da comanda: " + numero + "\n\n-- Produtos e quantidade --\n\n" + produtos_e_quantidade + "\nValor atual da comanda: " + calculaValorComanda();
    }
}