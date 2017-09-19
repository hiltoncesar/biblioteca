/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Gerais;

import Frames.Cadastros.SelecionaCapa;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.Config;
import util.Persistencia;

/**
 *
 * @author servbeta11
 */
public class ConfiguracoesGerais extends javax.swing.JDialog {

    private final util.Config c = new Config();

    /**
     * Creates new form ConfiguracoesGerais
     *
     * @param parent
     * @param modal
     */
    public ConfiguracoesGerais(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone_2_24.png")));
        jTFusuario.setText(c.getKey("usuario"));
        jTFsenha.setText(c.getKey("senha"));
        jTFcodigoAcesso.setText(c.getKey("codigoAcesso"));
        jTFcaminho.setText(c.getKey("arquivoBD"));
        jTFbkp.setText(c.getKey("pastaBKP"));

        String result = "";
        result = JOptionPane.showInputDialog(null, "Infome o código de acesso", "Painel de Configurações", JOptionPane.INFORMATION_MESSAGE);
        if (result.equals(new util.Config().getKey("codigoAcesso"))) {
            this.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Código inválido!", "Painel de Configurações", JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
            this.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTFusuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTFcodigoAcesso = new javax.swing.JTextField();
        jTFsenha = new javax.swing.JTextField();
        jBgravar = new javax.swing.JButton();
        jBtestar = new javax.swing.JButton();
        jTFcaminho = new javax.swing.JTextField();
        jBbanco = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTFbkp = new javax.swing.JTextField();
        jBselecionarBkp = new javax.swing.JButton();
        jTFsaida = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurações Gerais");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Usuário:");

        jLabel4.setText("Banco:");

        jLabel5.setText("Senha:");

        jLabel6.setText("Código de acesso:");

        jBgravar.setText("Gravar");
        jBgravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBgravarActionPerformed(evt);
            }
        });

        jBtestar.setText("Testar");
        jBtestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtestarActionPerformed(evt);
            }
        });

        jBbanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/open_16.png"))); // NOI18N
        jBbanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbancoActionPerformed(evt);
            }
        });

        jLabel7.setText("Backup:");

        jBselecionarBkp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/open_16.png"))); // NOI18N
        jBselecionarBkp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBselecionarBkpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtestar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBgravar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTFcodigoAcesso))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel7)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jTFusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTFsenha))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTFcaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFbkp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBselecionarBkp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBbanco, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBgravar, jBtestar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFcaminho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBbanco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTFbkp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBselecionarBkp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTFsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFcodigoAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBgravar)
                    .addComponent(jBtestar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTFsaida.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTFsaida)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFsaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(410, 221));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtestarActionPerformed
        jTFsaida.setText("");
        util.Persistencia p = new Persistencia();
        try {
            if (p.conectar() == null) {                                
                p.desconectar();                
                jTFsaida.setText("NAO");
            } else {
                jTFsaida.setText("Conexão OK!");
                p.desconectar(); 
            }
        } catch (NullPointerException ex) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Servidor não encontrado", "Erro de conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtestarActionPerformed

    private void jBgravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBgravarActionPerformed
        c.setKey("usuario", jTFusuario.getText());
        c.setKey("senha", jTFsenha.getText());
        c.setKey("codigoAcesso", jTFcodigoAcesso.getText());
        c.setKey("arquivoBD", jTFcaminho.getText());
        c.setKey("pastaBKP", jTFbkp.getText());
        javax.swing.JOptionPane.showMessageDialog(null,
                "Configurações atualizadas.", "Configurações Gerais", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jBgravarActionPerformed

    private void jBbancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbancoActionPerformed
        SelecionaCapa c;
        File f;
        try {
            c = new SelecionaCapa(new Frames.Gerais.TelaInicial(1,"admin","admin"), rootPaneCheckingEnabled, jTFcaminho.getText(),0,"Selecione o arquivo do banco de dados");
            c.setVisible(true);
            f = c.file;
            if (f != null) {
                jTFcaminho.setText(f.getPath());
            }
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracoesGerais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBbancoActionPerformed

    private void jBselecionarBkpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBselecionarBkpActionPerformed
        SelecionaCapa c;
        File f;
        try {
            c = new SelecionaCapa(new Frames.Gerais.TelaInicial(1,"admin","admin"), rootPaneCheckingEnabled, jTFbkp.getText(),1,"Selecione a pasta para backup");
            c.setVisible(true);
            f = c.file;
            if (f != null) {
                jTFbkp.setText(f.getPath());
            }
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracoesGerais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBselecionarBkpActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbanco;
    private javax.swing.JButton jBgravar;
    private javax.swing.JButton jBselecionarBkp;
    private javax.swing.JButton jBtestar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFbkp;
    private javax.swing.JTextField jTFcaminho;
    private javax.swing.JTextField jTFcodigoAcesso;
    private javax.swing.JTextField jTFsaida;
    private javax.swing.JTextField jTFsenha;
    private javax.swing.JTextField jTFusuario;
    // End of variables declaration//GEN-END:variables
}