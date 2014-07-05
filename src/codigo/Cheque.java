
 /* CLASSE CHEQUE
 * Subclasse da classe Pagamento, é usada quando o cliente usa o cheque
 * como forma de pagamento;
 *
 * ATRIBUTOS
 * *DATA - Atributo que diz a data que o cheque vai começar a ser válido.
 */
package codigo;

public class Cheque extends Pagamento {

    private String data;

    public Cheque(double valor_comanda, double valor_recebido, String a) {
        super(valor_comanda, valor_recebido);
        data = a;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public double calculaPagamento() {

        throw new UnsupportedOperationException("Not supported yet.");

    }
}