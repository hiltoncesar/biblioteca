/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Hilton
 */
public class Centralizar {
    public Centralizar(javax.swing.JInternalFrame iframe){
        java.awt.Dimension d = iframe.getDesktopPane().getSize();
        iframe.setLocation((d.width - iframe.getSize().width) / 2, (d.height - iframe.getSize().height) / 2);
    }    
}
