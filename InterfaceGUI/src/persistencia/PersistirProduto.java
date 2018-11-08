package persistencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class PersistirProduto {

    public final int NUM_COLUMNS = 1;
    public final String FILE = "C:\\bd_txt_java\\produto.csv";
    public final String MSG = "Produto";

    public void salvarNovoProduto(String novoCliente) {
        String textoDoArquivo = "";
        //Primeira coisa é ler o arquivo
        try {
            FileReader arq = new FileReader(FILE);
            BufferedReader lerArq = new BufferedReader(arq);

            String temp = lerArq.readLine();
            while (temp != null) {
                textoDoArquivo += temp + "\n";
                temp = lerArq.readLine();
            }
            arq.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        //textoDoArquivo += (textoDoArquivo.equals("")) ? textoDoArquivo : "\n";
        textoDoArquivo += novoCliente;

        try {
            FileWriter arq = new FileWriter(FILE);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.write(textoDoArquivo);
            arq.close();
            JOptionPane.showMessageDialog(null, MSG + " salvo com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Houve um erro ao acessar o arquivo " + FILE + "\n"
                    + "Certifique-se de que a pasta exista.");
        }
    }

    public Object[][] lerProdutos() {
        Object[][] vetor = null;
        try {
            FileReader arq = new FileReader(FILE);
            BufferedReader lerArq = new BufferedReader(arq);

            int contador = 0;
            String temp = lerArq.readLine();
            while (temp != null) {
                contador++;
                temp = lerArq.readLine();
            }
            arq.close();

            arq = new FileReader(FILE);
            vetor = new Object[contador][NUM_COLUMNS];

            lerArq = new BufferedReader(arq);

            contador = 0;
            temp = lerArq.readLine();
            while (temp != null) {
                String[] linha;
                linha = temp.split(";");
                vetor[contador] = linha;

                temp = lerArq.readLine();
                contador++;
            }

            arq.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return vetor;
    }

    public void atualizarProdutos(String Update) {
        try {
            FileWriter arq = new FileWriter(FILE);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.write(Update);
            arq.close();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Houve um erro ao acessar o arquivo " + FILE + "\n"
                    + "Certifique-se de que a pasta exista.");
        }
    }
}
