
 /* CLASSE DINHEIRO
 *
 * Classe herdeira de Pagamento, que se responsabiliza por pagamentos em dinheiro.
 */
package codigo;

public class Dinheiro extends Pagamento {

    private double troco;

    public Dinheiro(double valor_comanda, double valor_recebido) {//Construtor
        super(valor_comanda, valor_recebido);
        troco = valor_recebido - valor_comanda;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    @Override
    public double calculaPagamento() {
        return troco =  valor_recebido - valor_comanda;
    }
}