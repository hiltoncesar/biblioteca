/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author hilton.silva
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class FormatarCampo extends PlainDocument {

    private int maxLength;
    private String forma;

    public FormatarCampo(int maxlen, String forma) {
        super();

        if (maxlen <= 0) {
            throw new IllegalArgumentException("You must specify a maximum length!");
        }
        this.forma = forma;
        maxLength = maxlen;
    }
    //@Override
    //limita texto
    /*public void insertString(int offset, String str, AttributeSet attr)
     throws BadLocationException {
     if (str == null || getLength() == maxLength) {
     return;
     }

     int totalLen = (getLength() + str.length());
     if (totalLen <= maxLength) {
     super.insertString(offset, str, attr);
     return;
     }

     String newStr = str.substring(0, (maxLength - getLength()));
     super.insertString(offset, newStr, attr);
     }*/
    //permite especiais e numeros
    /*public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{   
     if(Character.isLetter(str.charAt(0)) || str.equals("_") || str.equals(" ") ){   
     javax.swing.JOptionPane.showMessageDialog(null,str);
     } else{
     super.insertString(offs, str.toUpperCase(), a); 
     }
     }*/
    //insertString para nÃºmero 0.00 e data dd-MM-yyyy
    Character ip[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', '/', ':'};
    Set<Character> validos_ip = new HashSet<Character>(Arrays.asList(ip));
    Character numerosPontoVirgula[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', '/', '-'}; //Coloque aqui todos os caracteres vÃ¡lidos   
    Set<Character> validos_NumerosPontoVirgula = new HashSet<Character>(Arrays.asList(numerosPontoVirgula));
    Character valor[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', 'R', '$', '#'}; //Coloque aqui todos os caracteres vÃ¡lidos   
    Set<Character> validos_valor = new HashSet<Character>(Arrays.asList(valor));
    Character v[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}; //Coloque aqui todos os caracteres vÃ¡lidos   
    Set<Character> numeros = new HashSet<Character>(Arrays.asList(v));
    Character login[] = {'@','a','b','c','ç','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x','w','y','z','0','1','2','3','4','5','6','7','8','9','_','-',}; //Coloque aqui todos os caracteres vÃ¡lidos   
    Set<Character> loginletras = new HashSet<Character>(Arrays.asList(login));

    Character cnpj[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '/', '-'}; //Coloque aqui todos os caracteres vÃ¡lidos   
    Set<Character> cnpjValido = new HashSet<Character>(Arrays.asList(cnpj));

    @Override//o original Ã© sem o str.replaceAll("[^0-9,]", "")
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        if (forma.equals("numerodata")) {
            char[] chars = str.replace("/", "-").toUpperCase().toCharArray();
            StringBuilder inserted = new StringBuilder();
            for (char ch : chars) {
                if (validos_NumerosPontoVirgula.contains(ch)) {
                    inserted.append(ch); //Filtramos sÃ³ o que Ã© vÃ¡lido   
                }
            }
            super.insertString(offs, inserted.toString(), a);
        } //limita texto AZaz09

        if (forma.equals("numeros")) {
            if (str == null || getLength() == maxLength) {
                return;
            }
            int totalLen = (getLength() + str.length());
            if (totalLen <= maxLength) {
                char[] chars = str.replace("/", "-").toUpperCase().toCharArray();
                StringBuilder inserted = new StringBuilder();
                for (char ch : chars) {
                    if (numeros.contains(ch)) {
                        inserted.append(ch); //Filtramos sÃ³ o que Ã© vÃ¡lido   
                    }
                }
                super.insertString(offs, inserted.toString(), a);
            }

        }
        //limita texto AZaz09
        else if (forma.equals("limitatexto")) {
            if (str == null || getLength() == maxLength) {
                return;
            }
            int totalLen = (getLength() + str.length());
            if (totalLen <= maxLength) {
                super.insertString(offs, str, a);
                return;
            }
            String newStr = str.substring(0, (maxLength - getLength()));
            super.insertString(offs, newStr, a);
        } //texto padrao
        
        
        else if (forma.equals("textopadrao")) {
            str = str.replaceAll("[^A-Za-z0-9.,/()çÇãÃéÉíÍàÀõÕêÊáÁ -Ã§~Â´`^ÂºÂª$%@]\n", "");
            super.insertString(offs, str.toString().toUpperCase(), a);
        } 
        
        else if (forma.equals("textominimizado")) {
            str = str.replaceAll("[^A-Za-z0-9.,/()çÇãÃéÉíÍàÀõÕêÊáÁ -Ã§~Â´`^ÂºÂª$%@]\n", "");
            super.insertString(offs, str.toString().toLowerCase(), a);
        }
        
        else if (forma.equals("cnpj")) {
            if (str == null || getLength() == maxLength) {
                return;
            }
            int totalLen = (getLength() + str.length());
            if (totalLen <= maxLength) {
                char[] chars = str.toUpperCase().toCharArray();
                StringBuilder inserted = new StringBuilder();
                for (char ch : chars) {
                    if (cnpjValido.contains(ch)) {
                        inserted.append(ch); //Filtramos sÃ³ o que Ã© vÃ¡lido   
                    }
                }
                super.insertString(offs, inserted.toString(), a);
            }
        }
        
        else if (forma.equals("login")) {
            char[] chars = str.toLowerCase().toCharArray();
            StringBuilder inserted = new StringBuilder();
            for (char ch : chars) {
                if (loginletras.contains(ch)) {
                    inserted.append(ch); //Filtramos sÃ³ o que Ã© vÃ¡lido   
                }
            }
            super.insertString(offs, inserted.toString(), a);
            
            
        }
        
        else if (forma.equals("ip")) {
            char[] chars = str.toUpperCase().toCharArray();
            StringBuilder inserted = new StringBuilder();
            for (char ch : chars) {
                if (validos_ip.contains(ch)) {
                    inserted.append(ch); //Filtramos sÃ³ o que Ã© vÃ¡lido   
                }
            }
            super.insertString(offs, inserted.toString(), a);
            
            
        } else if (forma.equals("valor")) {
            char[] chars = str.replace(",", ".").toUpperCase().toCharArray();
            StringBuilder inserted = new StringBuilder();
            for (char ch : chars) {
                if (validos_valor.contains(ch)) {
                    inserted.append(ch); //Filtramos sÃ³ o que Ã© vÃ¡lido   
                }
            }
            super.insertString(offs, inserted.toString(), a);
        }

    }
    /* @Override - OpÃ§Ã£o para somente letras Ã© ideal     
     public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{     
     str = str.replaceAll ("[^A-Za-z0-9 ]", "");   
     super.insertString(offs, str.toString(), a);     
     }*/
}
