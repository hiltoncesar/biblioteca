/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author servbeta11
 */
public class ConfigChat {

    private final String caminho;

    public ConfigChat(String caminho) {
        this.caminho = caminho;
    }

    public String abre() {
        String saida = "VAZIO";
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(caminho));
            String s = "";
            String linha = null;
            do {
                linha = entrada.readLine();
                if (linha != null) {
                    s += linha + "\n";
                }
            } while (linha != null);
            saida = s;
            entrada.close();
            return saida;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saida;

    }

    public void grava(String texto) {
        try {
            FileWriter arquivoWriter = new FileWriter(caminho, true);
            BufferedWriter saida = new BufferedWriter(arquivoWriter);
            String tx = texto + "\n";
            saida.write(tx);
            saida.flush();
            saida.close();
        } catch (IOException ex) {
            Logger.getLogger(ConfigChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpa() {
        FileOutputStream erasor;
        try {
            erasor = new FileOutputStream(caminho);
            erasor.write(new byte[0]);
            erasor.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigChat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigChat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
