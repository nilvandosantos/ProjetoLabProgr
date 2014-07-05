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
    private LinkedList<Comanda> comandas = new LinkedList<Comanda>();
    private String data;

    //Construtor
    public Caixa(double valor_inicial, String data) {
        this.valor_inicial = valor_inicial;
        this.data = data;
    }

    public LinkedList<Comanda> getComandas() {
        return this.comandas;
    }

    //Método para adicionar uma LinkedList completa a classe caixa
    public void setComandas(LinkedList<Comanda> comandas) {
        this.comandas = comandas;
    }

    //Metodo que retorna todas comandas do caixa
    public LinkedList<Comanda> retornaComandas() {
        return comandas;
    }

    //metodo que retorna um comanda a partir do codigo
    public Comanda retornaUmaComanda(int indice_comanda) {

        return comandas.get(indice_comanda);

    }

    //Método para adiconar uma comanda na linkedlist da classe Caixa
    public void adicionaComanda(Comanda c) {
        comandas.add(c);
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