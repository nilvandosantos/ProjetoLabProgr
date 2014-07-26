/*
 * CLASSE CAIXA
 *
 * A classe Caixa é responsável pelo controle monetário do restaurante.
 * Ela que vai realizar todas as tarefas referentes a balanço total e afins.
 *
 * ATRIBUTOS PRINCIPAIS
 * *VALOR INICIAL - Inicializa o caixa, determinando a quantidade inicial
 *  de dinheiro dentro do mesmo;
 * *COMANDAS - Array que armazena todas as comandas do dia.
 */
package codigo;

import java.util.*;

public class Caixa {

    private double valor_inicial;
    private double total_caixa;
    private LinkedList<Pedido> pedidos = new LinkedList<Pedido>();
    private String data;

    //Construtor
    public Caixa(double valor_inicial, String data) {
        this.valor_inicial = valor_inicial;
        this.data = data;
    }

    public LinkedList<Pedido> getPedidos() {
        return this.pedidos;
    }

    //Método para adicionar uma LinkedList completa a classe caixa
    public void setPedidos(LinkedList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //Metodo que retorna todas comandas do caixa
    public LinkedList<Pedido> retornaPedidos() {
        return pedidos;
    }

    //metodo que retorna um comanda a partir do codigo
    public Pedido retornaUmPedido(int indice_pedido) {

        return pedidos.get(indice_pedido);

    }

    //Método para adiconar uma comanda na linkedlist da classe Caixa
    public void adicionaPedido(Pedido c) {
        pedidos.add(c);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getTotalCaixa(){
        return total_caixa;
    }

    public void setTotalCaixa(Double total_caixa){
        this.total_caixa = total_caixa;
    }

    //metodo para atualizar o fundo de caixa
    public void atualizaTotalCaixa(Double valor_novo_pedido){
        this.total_caixa = this.total_caixa + valor_novo_pedido;
    }

    public double getValor_inicial() {
        return valor_inicial;
    }

    public void setValor_inicial(double valor_inicial) {
        this.valor_inicial = valor_inicial;
    }
    
}