/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author hilton.silva
 */
public class Md5 {
    
    //Função para criar hash da senha informada   
    public String md5(String senha) {
        String sen = "";
        try {
            java.math.BigInteger hash = new java.math.BigInteger(1, java.security.MessageDigest.getInstance("MD5").digest(senha.getBytes()));
            sen = hash.toString(16);
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "NoSuchAlgorithmException", "CRIPTOGRAFAR SENHA", javax.swing.JOptionPane.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Exception", "CRIPTOGRAFAR SENHA", javax.swing.JOptionPane.ERROR);
        }
        return sen;
    }
}
