package codigo;

import interfacegrafica.jFmenu;

import java.util.*;
import interfacegrafica.*;
import java.text.*;

public class Balanco {

    private static double total_arrecadado = 0;

    public void setTotalArrecadado(double adiciona) {
        total_arrecadado = adiciona;
    }

    public double getTotalArrecadado() {
        return total_arrecadado;
    }

    //Método para varrer todas as comandas do caixa e registrar o movimento total
    public static String varreComandas(LinkedList<Comanda> com) {
        String movimento = "";
        DecimalFormat d = new DecimalFormat("0.00");

        for (Comanda c : com) {
            movimento = movimento + "Código da comanda: " + c.getNumero();
            movimento = movimento + " | Dia: " + c.getData();
            movimento = movimento + " | Hora: " + c.getHora();
            movimento = movimento + " | Valor recebido: " + d.format(c.getPagamentoValorComanda());
            movimento = movimento + "\n";
        }

        return movimento;

    }

    //Metodo responsável por pegar a movimentação financeira do caixa
    public static String registraBalanco(int indice_caixa) {

        total_arrecadado = 0;

        DecimalFormat d = new DecimalFormat("0.00");

        int indice = 0;
        String def = "";

        for (Caixa box : jFmenu.retornaCaixas()) {

            if (indice >= indice_caixa) {
                def = def + varreComandas(jFmenu.retornaComanda(indice));
            }

            total_arrecadado = total_arrecadado + box.getTotalCaixa();
            indice++;

        }

        if (total_arrecadado == 0) {
            return "Nenhuma movimentação financeira realizada até o momento!";
        } else {
            def = def + "\nTotal arrecadado:                     [" + d.format(total_arrecadado) + "]";
            return def;
        }
    }
}