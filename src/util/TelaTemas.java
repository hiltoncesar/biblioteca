/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author hilton.silva
 */
public class TelaTemas extends javax.swing.JDialog {

    /**
     * Creates new form TelaTemas
     */
    private int selectedLaf = 0;
    private String tema = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    private final util.Config c = new Config();
    private java.awt.Frame fram;

    public TelaTemas(java.awt.Frame parent, boolean modal) throws ArrayIndexOutOfBoundsException, Exception {
        super(parent, modal);
        this.fram = parent;
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/biblioteca_32.png")));
        tema = c.getKey("tema");
    }

    private void setLookAndFeel() {
        selectedLaf = list.getSelectedIndex();

        try {
            String theme = "Default";
            boolean useDarkTexture = false;
            switch (selectedLaf) {
                case 0:
                    // First set the theme of the look and feel. This must be done first because there
                    // is some static initializing (color values etc.) when calling setTheme.
                    // Another reason is that the theme variables are shared with all look and feels, so
                    // without calling the setTheme method the look and feel will look ugly (wrong colors).
                    com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(theme);
                    // Now we can set the look and feel
                    UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                    tema = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
                    break;
                case 1:
                    com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                    tema = "com.jtattoo.plaf.aero.AeroLookAndFeel";
                    break;
                case 2:
                    com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                    tema = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
                    break;
                case 3:
                    com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                    tema = "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";
                    break;
                case 4:
                    com.jtattoo.plaf.fast.FastLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                    tema = "com.jtattoo.plaf.fast.FastLookAndFeel";
                    break;
                case 5:
                    com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
                    tema = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
                    break;
                case 6:
                    useDarkTexture = true;
                    com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                    tema = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
                    break;
                case 7:
                    com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                    tema = "com.jtattoo.plaf.luna.LunaLookAndFeel";
                    break;
                case 8:
                    com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                    tema = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
                    break;
                case 9:
                    com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                    tema = "com.jtattoo.plaf.mint.MintLookAndFeel";
                    break;
                case 10:
                    com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme(theme);
                    useDarkTexture = true;
                    UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                    tema = "com.jtattoo.plaf.noire.NoireLookAndFeel";
                    break;
                case 11:
                    com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                    tema = "com.jtattoo.plaf.smart.SmartLookAndFeel";
                    break;
                case 12:
                    com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                    tema = "com.jtattoo.plaf.texture.TextureLookAndFeel";
                    break;
                case 13:
                    com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    tema = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                    break;
                case 14: //javax.swing.plaf.nimbus.NimbusLookAndFeel
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        }
                    }
                    tema = "Nimbus";
            }
            // We change the background pattern in some cases to avoid an ugly look
            // Tell all components that l&f has changed.
            Window windows[] = Window.getWindows();
            for (int i = 0; i < windows.length; i++) {
                if (windows[i].isDisplayable()) {
                    SwingUtilities.updateComponentTreeUI(windows[i]);
                }
            }
            // Maybe selected item is not visible after changing the look and feel so we correct this
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // end setLookAndFeel

    public String look() {
        return tema;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        list = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Alterar Aparência");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Selecione", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Aharoni", 1, 18))); // NOI18N

        list.setMaximumRowCount(20);
        list.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Acryl", "Aero", "Aluminuim", "Bernstein", "Fast", "Graphite", "HiFi", "Luna", "McWin", "Mint", "Noire", "Smart", "Texture", "Windows", "Nimbus" }));
        list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok_16.png"))); // NOI18N
        jButton2.setText("Gravar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/deletar_16.png"))); // NOI18N
        jButton3.setText("Sair");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(list, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2)
                .addComponent(jButton3))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton3});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(401, 113));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        c.setKey("tema", tema);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listActionPerformed
        setLookAndFeel();
    }//GEN-LAST:event_listActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox list;
    // End of variables declaration//GEN-END:variables
}
