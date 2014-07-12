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

public class CadGarcon {

	private static LinkedList<Garcom> garcons = new LinkedList<Garcom>();
	private DataOutputStream outputGarcom;
	private DataInputStream inputGarcom;
	private boolean moreRecordsGarcom = true;
	
	public CadGarcon(){
		iniciarGarcom();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*public void abrirArquivo() {
		File arquivo = new File("Garcons.txt");
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
	}*/

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
			// TODO Auto-generated catch block
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

		// InstanciarGarcom
		String nome;
		int codigo;
		double gorjeta;
		double totalGorjeta;

		// Carrega os garcons do arquivo para as coleÃ§Ãµes
		try {
			while (moreRecordsGarcom) {
				nome = inputGarcom.readUTF();
				codigo = inputGarcom.readInt();
				gorjeta = inputGarcom.readDouble();
				totalGorjeta = inputGarcom.readDouble();

				Garcom temporario = new Garcom(nome, codigo, gorjeta,
						totalGorjeta);
				adicionaGarcom(temporario);
				System.out.println(temporario);
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

	// Metodo para adicionar um objeto Garcom a LinkedList "garcons"
	public static void adicionaGarcom(Garcom g) {
		garcons.add(g);
	}

	public void gravarGarcom() {
		// Abre arquivo para gravar
		try {
			outputGarcom = new DataOutputStream(new FileOutputStream(
					"Garcons.txt", false));
		//	JOptionPane.showMessageDialog(null,	"Garçon salvo com sucesso!"+" Nome: "+  +" \n\nCodigo: " );
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

}
