package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import codigo.Caixa;
/**
 * Esta classe tem o objetivo coordenar o caixa do restaurante,criando as suas respectivas listas
 * e salvando-as em arquivos do tipo TXT.
 * 
 * @author Nayara,Nilvando e Marcos lucas.
 *
 * @version 1.0
 */

public class CoordCaixa {
	private static LinkedList<Caixa> caixas = new LinkedList<Caixa>();
	String data;
	private boolean moreRecordsCaixas;
	private DataInputStream inputCaixas;
	private DataOutputStream outputCaixas;

	public CoordCaixa() {
		//Data que foi gerado o pedido
		getData();
	}

	public static LinkedList<Caixa> getCaixas() {
		return caixas;
	}

	public static void setCaixas(LinkedList<Caixa> caixas) {
		CoordCaixa.caixas = caixas;
	}

	/**
	 *Metodo responsável por capturar a data em que o caixa está 
	 * aberto e armazená-la em uma String no formato DD/MM/AAAA
	 */
	public String getData() {
		
		Calendar temporario = Calendar.getInstance();
		data = "" + temporario.get(Calendar.DAY_OF_MONTH) + "/"
				+ (temporario.get(Calendar.MONTH) + 1) + "/"
				+ temporario.get(Calendar.YEAR);
		return data;
	}

	public void iniciarCaixa() {

		File arquivo = new File("Caixas.txt");
		try {
			if (!arquivo.exists()) {
				// cria um arquivo (vazio)
				arquivo.createNewFile();
				JOptionPane.showMessageDialog(null,
						"Arquivo criado com sucesso!");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Ler o arquivo Caixa
		try {
			inputCaixas = new DataInputStream(new FileInputStream("Caixas.txt"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha na Abertura do Arquivo para Leitura", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// InstanciarCaixas
		double valor_inicial;
		String data1;

		// Carrega os caixas do arquivo para as coleções
		try {
			while (moreRecordsCaixas) {
				valor_inicial = inputCaixas.readDouble();
				data1 = inputCaixas.readUTF();

				Caixa temporario = new Caixa(valor_inicial, data1);
				addCaixa(temporario);
			}
		} catch (EOFException eof) {
			moreRecordsCaixas = false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Erro durante leitura do arquivo", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// Fecha inputCaixas
		try {
			inputCaixas.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha no Fechamento do Arquivo durante Leitura", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	}
	public boolean isCaixaAberto() {
		boolean isAberto = false;

		for (Caixa c : caixas) {

			if (data.equals(c.getData())) {
				isAberto = true;
				break;
			}
		}
		return isAberto;
	}

	/**
	 * Metodo para adicionar uma caixa a LinkedList caixas
	 */
	public static void addCaixa(Caixa c) {
		caixas.add(c);
	}

	public static LinkedList<Caixa> retornaCaixas() {
		return caixas;
	}
/**
 * Metodo para retornar o caixa em um indice escolhido
 */
	public static Caixa retornaUmCaixa(int indice) {
        return caixas.get(indice);
    }
}
