/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Hilton
 */
public final class Persistencia {

    private String host;
    private String port;
    private String banco;
    private String unitName;
    private String url;
    private String driver;
    private String user;
    private String password;
    private String arquivoBD;
    private Connection con;
    private final util.Config c = new util.Config();

    public void PersistenciaSistemaSuporteEhConstrutor() {
        //setParametros();
        host = c.getKey("host");
        port = c.getKey("port");
        banco = c.getKey("banco");
        user = c.getKey("user");
        password = c.getKey("pass");
        this.unitName = "SuporteCaspValePU";
        this.driver = "org.postgresql.Driver";
        url = "jdbc:postgresql://" + host + ":" + port + "/" + banco;
    }

    public Persistencia() {
        user = c.getKey("usuario");
        password = c.getKey("senha");
        //this.unitName = c.getKey("unitName");Biblioteca_0_0PU
        this.unitName = "Biblioteca_0_0PU";
        this.driver = "org.h2.Driver";
        arquivoBD = c.getKey("arquivoBD");
        url = "jdbc:h2:";
    }

//    public Persistencia() {
//        //setParametros();
//        host = "host";
//        port = "port";
//        banco = "banco";
//        user = "admin";
//        password = "admin";
//        this.unitName = "Biblioteca_0_0PU";
//        this.driver = "org.h2.Driver";
//        url = "jdbc:h2:C:\\03-SUPORTE_SERVBETA\\H2\\biblioteca\\biblioteca.mv.db";
//    }
    public Persistencia(String unitName, String url, String driver, String user, String password, int l) {
        this.unitName = unitName;
        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;
    }

    //<editor-fold defaultstate="collapsed" desc="SET PARÂMETROS">
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public void setParametros() {
//        this.unitName = "SuporteCaspValePU";
//        //this.url = "jdbc:postgresql://186.202.152.45:5432/servbeta11";
//        //this.url = "jdbc:postgresql://postgresql01.servbeta1.hospedagemdesites.ws:5432/servbeta11";
//        this.url = "jdbc:postgresql://186.202.152.45:5432/servbeta11";
//        this.driver = "org.postgresql.Driver";
//        this.user = "servbeta11";
//        this.password = "srvbt12";
//    }
    //</editor-fold>  
    private Map parametros() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("javax.persistence.jdbc.url", this.url + this.arquivoBD);
        map.put("javax.persistence.jdbc.user", this.user);
        map.put("javax.persistence.jdbc.driver", this.driver);
        map.put("javax.persistence.jdbc.password", this.password);
        return map;
    }

    public EntityManagerFactory emf() {
        try {
            return Persistence.createEntityManagerFactory(this.unitName, parametros());
        } catch (javax.persistence.PersistenceException ex) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    ex.getCause().getLocalizedMessage(), "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        return null;
    }

    public Connection conectar() {
        try {
            Class.forName(this.driver);
            con = DriverManager.getConnection(this.url + this.arquivoBD, this.user, this.password);
        } catch (NullPointerException ex) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    ex.getCause().getMessage(), "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ""+ex.getMessage(), "Atenção!",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (org.h2.jdbc.JdbcSQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, ""+e.getMessage(), "Erro de Conexão!",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Os campos precisam ser informados.", "Atenção! Campos vazios.",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
