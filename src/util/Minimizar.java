/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.*;
import static java.awt.Frame.ICONIFIED;
import static java.awt.Frame.NORMAL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Hilton
 */
public class Minimizar {

    TrayIcon trayIcon;
    SystemTray tray;
    JFrame frame;
    private int i_usu;

    public Minimizar(JFrame frame,int i_usu) {
        this.frame = frame;
        this.i_usu = i_usu;
        //this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone_2.png")));
        //super("SystemTray test");
    }

    public void trayH() {

        System.out.println("creating instance");
        /*try {
         System.out.println("setting look and feel");
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception e) {
         System.out.println("Unable to set LookAndFeel");
         }*/
        if (SystemTray.isSupported()) {
            System.out.println("system tray supported");
            tray = SystemTray.getSystemTray();
            

            //Image image = frame.getIconImage();//Toolkit.getDefaultToolkit().getImage("imagens/icone_2.gif");
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("/imagens/icone_2.png"));
            Image image = frame.getIconImage();
            ActionListener exitListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    System.out.println("Exiting....");
//                    System.exit(0);

                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setExtendedState(JFrame.NORMAL);
                }
            };
            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("Abrir");
            defaultItem.addActionListener(exitListener);
            popup.add(defaultItem);
            defaultItem = new MenuItem("Sair");
            defaultItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    frame.setVisible(true);
//                    frame.setLocationRelativeTo(null);
//                    frame.setExtendedState(JFrame.NORMAL);

                    System.out.println("Exiting....");
                   // new util.Status().setStatus(i_usu, "OFFLINE");27/07/2017 erro verificar
                    System.exit(0);
                }
            });
            popup.add(defaultItem);
            trayIcon = new TrayIcon(image, "Suporte CaspVale", popup);
            trayIcon.setImageAutoSize(true);
        } else {
            System.out.println("system tray not supported");
        }
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            Logger.getLogger(Minimizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == ICONIFIED) {
                    //tray.add(trayIcon);
                    frame.setVisible(false);
                    System.out.println("added to SystemTray ICON");
                }
                if (e.getNewState() == 7) {

                    //tray.add(trayIcon);
                    frame.setVisible(false);
                    System.out.println("added to SystemTray");
                }
                if (e.getNewState() == NORMAL) {
                    //tray.remove(trayIcon);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    System.out.println("Tray icon open");
                }
            }
        });

        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (frame.isVisible()) {
                    frame.setVisible(false);
                } else {
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setExtendedState(JFrame.NORMAL);
                }
            }
        });

        //Cria o listener para esconder o jframe quando mandar fechar
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                String[] str = {"Sair", "Minimizar"};
                int result = JOptionPane.showOptionDialog(null,
                        "Deseja sair da aplicação?", "Sair", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, str, str[1]);
                if (result == 0) {
                    frame.dispose();
                    new util.Config().setKey("status", "off");
                    System.exit(0);
                } else {
                    frame.setVisible(false);
                }//end else
            }//end windowClosing
        });//end WindowAdapter
        //frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Duke256.png"));

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
