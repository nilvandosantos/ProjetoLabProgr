
package codigo;

/**
 * Esta � uma subclasse da classe Pagamento, usada quando o cliente usa o cart�o
 * como forma de pagamento;
 *@author Marcos Lucas,Nayara,Nilvando
 * 
 */
public class Cartao extends Pagamento {

    private int parcelas;

    public Cartao(double valor_pedido, double valor_recebido, int a) {
        super(valor_pedido, valor_recebido);
        parcelas = a;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public double calculaPagamento() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}