/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Config;

/**
 *
 * @author Hilton
 */
public class Conectar {

    private String url;
    private String usuario;
    private String banco;
    private String senha;
    public Connection con;
    public Statement stm;
    public ResultSet rs;
    private util.Config conf = new Config();

    public Conectar() {
    }

    public void conectar() {
        util.Config c = new Config();
        try {
            usuario = String.valueOf(c.getKey("usuario"));
            senha = String.valueOf(c.getKey("senha"));
            banco = String.valueOf(c.getKey("arquivoBD"));
            url = "jdbc:h2:" + banco;
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage()
                    + "\nVerifique as configurações com o banco.\n",
                    "ERRO B001!", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Erro de acesso ao servidor. Verifique as configurações com o banco.\n",
                    "ERRO B001!", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void desconecta() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exeSQL(String sql) throws SQLException {
        stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stm.executeQuery(sql);
    }
//
//    public void abreRel(String getProp, String getRel) {
//        try {
//            PreparedStatement ps = con.prepareStatement(properties.getProperty(getProp));
//            ps.setString(1, "parametro1");
//            ps.setString(2, "parametro2");
//            rs = ps.executeQuery();
//
//            exeSQL(new Ler().ler().getProperty(getProp));
//            JRResultSetDataSource relatResult = new JRResultSetDataSource(rs);
//            JasperPrint jpPrint;
//            jpPrint = JasperFillManager.fillReport(getRel, new HashMap(), relatResult);
//            JasperViewer jv = new JasperViewer(jpPrint, false);
//            jv.setVisible(true);
//            jv.toFront();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void abreRelPar(String sql, String getRel) {
//        try {
//            exeSQL(sql);
//            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            rs = stm.executeQuery(sql);
//            JRResultSetDataSource relatResult = new JRResultSetDataSource(rs);
//            JasperPrint jpPrint;
//            jpPrint = JasperFillManager.fillReport(getRel, new HashMap(), relatResult);
//            JasperViewer jv = new JasperViewer(jpPrint, false);
//            jv.setVisible(true);
//            jv.toFront();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        }
//    }
}
