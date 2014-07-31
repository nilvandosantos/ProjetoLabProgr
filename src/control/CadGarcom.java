package control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import codigo.Garcom;

/**
 * Esta classe tem como objetivo cadastrar o garçom e verificar se o mesmo ja exite na lista, na qual
 * será armazenado apos a conclusão do cadastro.
 * 
 * @author Marcos Lucas,Nayara,Nilvando.
 * 
 * @version 1.0
 *
 */

public class CadGarcom {

	private static LinkedList<Garcom> garcons = new LinkedList<Garcom>();
	private DataOutputStream outputGarcom;
	private DataInputStream inputGarcom;
	private boolean moreRecordsGarcom = true;
	
	public static LinkedList<Garcom> getGarcons() {
		return garcons;
	}
	public static void setGarcons(LinkedList<Garcom> garcons) {
		CadGarcom.garcons = garcons;
	}
	public CadGarcom(){
		
	}
	public static void main(String[] args) {

	}

	/**
	 * Metodo criado para gerar um novo arquivo do tipo TXT,caso o mesmo ja tenha sido gerado ele 
	 * apenas irá ler o arquivo e istanciará o objeto.
	 * @see- class Garcom.
	 */
		
	public void iniciarGarcom() {

		File arquivo = new File("Garcons.txt");
		try {
			if (!arquivo.exists()) {
				// cria um arquivo (vazio)
				arquivo.createNewFile();
				JOptionPane.showMessageDialog(null,
						"Arquivo criado com sucesso!");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Ler o arquivo garcom
		try {
			inputGarcom = new DataInputStream(new FileInputStream("Garcons.txt"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha na Abertura do Arquivo para Leitura", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// Instancia Garcom
		String nome;
		int codigo;
		double gorjeta;
		double totalGorjeta;

		// Carrega os garcons do arquivo para as coleções
		try {
			while (moreRecordsGarcom) {
				nome = inputGarcom.readUTF();
				codigo = inputGarcom.readInt();
				gorjeta = inputGarcom.readDouble();
				totalGorjeta = inputGarcom.readDouble();

				Garcom temporario = new Garcom(nome, codigo, gorjeta,
						totalGorjeta);
				adicionaGarcom(temporario);
			}
		} catch (EOFException eof) {
			moreRecordsGarcom = false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Erro durante leitura do arquivo", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// Fecha inputGarcom
		try {
			inputGarcom.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha no Fechamento do Arquivo durante Leitura", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	}

	/**
	 * Metodo para adicionar um objeto Garcom a LinkedList "garcons"
	 */
	public static void adicionaGarcom(Garcom g) {
		garcons.add(g);
	}

	public void gravarGarcom() {
		// Abre arquivo para gravar
		try {
			outputGarcom = new DataOutputStream(new FileOutputStream(
					"Garcons.txt", false));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha na Abertura do Arquivo para Gravação", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// Carrega toda a coleção no arquivo
		try {
			for (Garcom a : garcons) {
				outputGarcom.writeUTF(a.getNome());
				outputGarcom.writeInt(a.getCodigo());
				outputGarcom.writeDouble(a.getGorjeta());
				outputGarcom.writeDouble(a.getTotalGorjeta());
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Erro durante gravação no arquivo", "Erro",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		// Fecha os arquivos Output garcom
		try {
			outputGarcom.flush();
			outputGarcom.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Falha no Fechamento do Arquivo - Durante Gravação!!",
					"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	/**
	 * Este metodo tem como objetivo exibir em tela os garçons que foram salvos 
	 */
	public String ajuda() {
		String ajuda="";
		for (Garcom a : garcons) {
			ajuda = ajuda + (a.getCodigo()+ " "+ a.getNome()+"\n");
		}
		return ajuda;
	}
	public static Garcom retornaUmGarcom(int indice_garcom) {

        return garcons.get(indice_garcom);

    }
}
