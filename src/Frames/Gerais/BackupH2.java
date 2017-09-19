/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Gerais;

import Frames.Cadastros.CadastroLivros;
import Frames.Cadastros.SelecionaCapa;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import util.Config;

/**
 *
 * @author Hilton
 */
public class BackupH2 extends javax.swing.JDialog {

    static final Runtime run = Runtime.getRuntime();
    static Process pro;
    static BufferedReader read;
    private File f;
    util.Config c = new Config();

    public BackupH2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/biblioteca_32.png")));
        jTFpastaBackup.setText(c.getKey("pastaBKP") + "\\" + new java.text.SimpleDateFormat("dd-MM-yyyy_HHmmss").format(new java.util.Date(System.currentTimeMillis())));
        jTFpastaBanco.setText(new File(c.getKey("arquivoBD")).getParent());
    }

    public void bkp() {

        List<String> comandos = new ArrayList<>();
        jTAsaida.setText("");
        String pastaBanco = jTFpastaBanco.getText();
        String novoDir = jTFpastaBackup.getText();
        new File(novoDir).mkdir();

        comandos.add("xcopy");
        comandos.add("\"" + pastaBanco + "\"");
        comandos.add("\"" + novoDir + "\"");
        ProcessBuilder pb = new ProcessBuilder(comandos);
        try {
            final Process process = pb.start();
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            /*String line = r.readLine();
            System.out.println("*" + r.ready());
            while (line != null) {
                System.err.println("**" + line);
                line = r.readLine();
                jTAsaida.setText(jTAsaida.getText() + "\n" + line);
            }*/
            javax.swing.JOptionPane.showMessageDialog(null, "Backup Realizado!");
            r.close();
            process.waitFor();
            process.destroy();
        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO IO001", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO IE001", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    public void criaPasta(String novaPasta) {
        try {
            File diretorio = new File(novaPasta);
            diretorio.mkdir();
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao criar o diretorio");
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTAsaida = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jBpastaBackup = new javax.swing.JButton();
        jBbackup = new javax.swing.JButton();
        jTFpastaBanco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTFpastaBackup = new javax.swing.JTextField();
        jBpastaBanco = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerador de Backup");

        jTAsaida.setColumns(20);
        jTAsaida.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTAsaida.setLineWrap(true);
        jTAsaida.setRows(1);
        jTAsaida.setAutoscrolls(false);
        jScrollPane1.setViewportView(jTAsaida);

        jLabel2.setText("Pasta do Banco:");

        jBpastaBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/open_16.png"))); // NOI18N
        jBpastaBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBpastaBackupActionPerformed(evt);
            }
        });

        jBbackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/exportar_16.png"))); // NOI18N
        jBbackup.setText("Gerar Backup");
        jBbackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBbackupActionPerformed(evt);
            }
        });

        jTFpastaBanco.setAutoscrolls(false);

        jLabel8.setText("Pasta de Backup:");

        jTFpastaBackup.setAutoscrolls(false);

        jBpastaBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/open_16.png"))); // NOI18N
        jBpastaBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBpastaBancoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addComponent(jTFpastaBanco)
                    .addComponent(jTFpastaBackup)
                    .addComponent(jBbackup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBpastaBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBpastaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTFpastaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBpastaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jTFpastaBackup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBpastaBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBbackup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(465, 163));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBbackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBbackupActionPerformed
        bkp();
    }//GEN-LAST:event_jBbackupActionPerformed

    private void jBpastaBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBpastaBackupActionPerformed
        try {
            selecionaPasta(jTFpastaBackup, 1, "Selecione a pasta para backup");
        } catch (IOException ex) {
            Logger.getLogger(CadastroLivros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBpastaBackupActionPerformed

    private void jBpastaBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBpastaBancoActionPerformed
        try {
            //selecionaPasta(jTFpastaBanco, 1, "Selecione a pasta para backup");

            SelecionaCapa c = new SelecionaCapa(new Frames.Gerais.TelaInicial(1,"admin","admin"), rootPaneCheckingEnabled, jTFpastaBanco.getText(), 1, "Selecione a pasta do banco de dados",1);
            c.setVisible(true);
            this.f = c.file;
            if (f != null) {
                jTFpastaBanco.setText(f.getPath());
            }
        } catch (IOException ex) {
            Logger.getLogger(CadastroLivros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBpastaBancoActionPerformed
    public void selecionaPasta(JTextField campo, int tipo, String titulo) throws IOException {
        SelecionaCapa c = new SelecionaCapa(new Frames.Gerais.TelaInicial(1,"admin","admin"), rootPaneCheckingEnabled, campo.getText(), tipo, titulo);
        c.setVisible(true);
        this.f = c.file;
        if (f != null) {
            campo.setText(f.getPath());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBbackup;
    private javax.swing.JButton jBpastaBackup;
    private javax.swing.JButton jBpastaBanco;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAsaida;
    private javax.swing.JTextField jTFpastaBackup;
    private javax.swing.JTextField jTFpastaBanco;
    // End of variables declaration//GEN-END:variables
}
