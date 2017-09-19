/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author servbeta11
 */
public class Config {

    private final String caminho = "lib/config.properties";
    //private final String caminho = "config.properties";

    public String getKey(String chave) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(caminho));
            //prop.load(getClass().getResourceAsStream(caminho));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return "Arquivo de configuração não encontrado";
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return "Arquivo de configuração não encontrado";
        } catch (NullPointerException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return "Arquivo de configuração não encontrado";
        }
        return prop.getProperty(chave);
    }

    public void setKey(String chave, String valor) {
        Properties prop = new Properties();
        try {
//            File f = new File(caminho);
//            java.io.FileOutputStream out = new java.io.FileOutputStream(f.getAbsoluteFile());//            
            prop.load(new FileInputStream(caminho));
            prop.setProperty(chave, valor);
            prop.store(new FileOutputStream(caminho), "ARQUIVO DE CONFIGURAÇAO");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
