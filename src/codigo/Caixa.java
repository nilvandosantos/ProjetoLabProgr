
package codigo;

import java.util.*;
/**
 *  classe Caixa é responsável pelo controle monetário do restaurante.
 * Ela que vai realizar todas as tarefas referentes a balanço total e afins.
 *
 * 
 * @author Marco Lucas,Nayara,Nilvando.
 *
 */
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

    public LinkedList<Pedido> getComandas() {
        return this.pedidos;
    }

    //Método para adicionar uma LinkedList completa a classe caixa.
    public void setComandas(LinkedList<Pedido> comandas) {
        this.pedidos = comandas;
    }

    //Metodo que retorna todos os pedidos do caixa.
    public LinkedList<Pedido> retornaComandas() {
        return pedidos;
    }

    //metodo que retorna um pedido a partir do codigo.
    public Pedido retornaUmaComanda(int indice_comanda) {

        return pedidos.get(indice_comanda);

    }

    //Método para adiconar um pedido na linkedlist da classe Caixa
    public void adicionaComanda(Pedido c) {
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
    public void atualizaTotalCaixa(Double valor_nova_comanda){
        this.total_caixa = this.total_caixa + valor_nova_comanda;
    }

    public double getValor_inicial() {
        return valor_inicial;
    }

    public void setValor_inicial(double valor_inicial) {
        this.valor_inicial = valor_inicial;
    }
}