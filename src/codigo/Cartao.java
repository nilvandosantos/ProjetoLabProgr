
/* CLASSE CARTAO
 *
 * Subclasse da classe Pagamento, é usada quando o cliente usa o cartão
 * como forma de pagamento;
 *
 * ATRIBUTOS PRINCIPAIS
 * *PARCELAS - Diz a quantidade de parcelas que o cliente deseja pagar.
 * 
 */
package codigo;

public class Cartao extends Pagamento {

    private int parcelas;

    public Cartao(double valor_comanda, double valor_recebido, int a) {
        super(valor_comanda, valor_recebido);
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