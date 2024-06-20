package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Log {
    private static final String DIRETORIO_LOG = "log";
    private static final String ARQUIVO_LOG = DIRETORIO_LOG + "/log.txt";
    private static boolean diretorioLogFoiInicializado = false;

    private static void criarDiretorioLog() {
        File logDir = new File(DIRETORIO_LOG);
        if (!logDir.exists()) {
            logDir.mkdir();
        }
        diretorioLogFoiInicializado = true;
    }

    private static List<String> lerArquivo() {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_LOG))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de log: " + e.getMessage());
        }
        return linhas;
    }

    public static void escreverNoLog(String mensagem) {
        if (!diretorioLogFoiInicializado) {
            criarDiretorioLog();
        }

        List<String> linhas = lerArquivo();
        linhas.add(mensagem);

        try (FileWriter writer = new FileWriter(ARQUIVO_LOG)) {
            for (String linha : linhas) {
                writer.write(linha + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}
