/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Cadastros;

import controle.UsuariosJpaController;
import entidades.Usuarios;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hilton
 */
public final class CadastroUsuarios extends javax.swing.JInternalFrame {

    private final UsuariosJpaController emfUsuarios = new UsuariosJpaController(new util.Persistencia().emf());
    private String modoGravar, modoBuscar = "";
//    private int[] arrayEntidades, arrayCategorias;

    public CadastroUsuarios() {
        initComponents();
        tabelaUsuarios();
        setFrameIcon(new javax.swing.ImageIcon(this.getClass().getResource("/imagens/biblioteca_24.png")));
    }

    /* public Anexos() {
        initComponents();
        listarEntidades();
        listarCategorias();
        tabelaAutores();
        setFrameIcon(new javax.swing.ImageIcon(this.getClass().getResource("/imagens/icone_2_24.png")));

    }*/
    public DefaultTableModel tabelaUsuarios() {
        //<editor-fold defaultstate="collapsed" desc="Manipulação da Tabela">
        /* Método controlador da tabela*/

        //int iFun = id_funcionario[fu.getSelectedIndex()];
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{
            "CÓDIGO", "USUARIO", "NOME", "SITUAÇÃO"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            @Override
            public boolean isCellEditable(int a, int b) {
                return false;
            }
        };
        String[] linha = new String[]{null, null, null, null};
        int linhas = modelo.getRowCount();

        List<Usuarios> listaUsuarios = null;
        try {
//            listaAutores = emfAnexos.findSuporteAnexosEntitiesOrdenado();
//            listaAnexos = emfAnexos.findSuporteAnexosOrdenado();
            listaUsuarios = emfUsuarios.findUsuariosEntities();
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Erro ao recuperar informações da tabela\n"
                    + ex.getMessage(), "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        }
        while (linhas > 0) {//limpa conteudo da tabela para atulização
            for (int i = linhas; i > 0; i--) {
                modelo.removeRow(i - 1);
                linhas = modelo.getRowCount();
            }//fim do for
        }//fim do while

        int l = 0;
        if (!listaUsuarios.isEmpty()) {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                try {
                    modelo.addRow(linha);
                    try {
                        modelo.setValueAt(listaUsuarios.get(i).getIUsuario(), l, 0);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listaUsuarios.get(i).getUsuario(), l, 1);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listaUsuarios.get(i).getNomeUsuario(), l, 2);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listaUsuarios.get(i).getSituacaoUsuaruio(), l, 3);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                } catch (NullPointerException erro) {
                    System.out.println("Atenção! Campos vazios.");
                }
                l++;
            }
        }
        jTusuarios.setModel(modelo);
        //tamanhoColunas(jTautores);
        return modelo;
        //</editor-fold>
    }

//    public void listarEntidades() {
//        //<editor-fold defaultstate="collapsed" desc="Lista de Entidades">
//        List<SuporteEntidades> listaEntidades = emfEntidade.findSuporteEntidadesEntitiesOrdenado();
//        String[] lista = new String[listaEntidades.size() + 1];
//        lista[0] = "Selecione...";
//        arrayEntidades = new int[listaEntidades.size() + 1];
//        arrayEntidades[0] = 0;
//        for (int i = 0; i < lista.length - 1; i++) {
//            lista[i + 1] = listaEntidades.get(i).getEntidadeNome();
//            arrayEntidades[i + 1] = listaEntidades.get(i).getIEntidade();
//        }
////        new util.ComboDinamico(jCBentidades);
//        jCBentidades.setModel(new DefaultComboBoxModel(lista));
//        //</editor-fold>
//    }
//    public void listarCategorias() {
//        //<editor-fold defaultstate="collapsed" desc="Lista de Entidades">
//        List<SuporteCategorias> listarCategorias = emfCategorias.findSuporteCategoriasEntitiesOrdenado();
//        String[] lista = new String[listarCategorias.size() + 1];
//        lista[0] = "Selecione...";
//        arrayCategorias = new int[listarCategorias.size() + 1];
//        arrayCategorias[0] = 0;
//        for (int i = 0; i < lista.length - 1; i++) {
//            lista[i + 1] = listarCategorias.get(i).getCategoriaNome();
//            arrayCategorias[i + 1] = listarCategorias.get(i).getICategoria();
//        }
////        new util.ComboDinamico(jCBentidades);
//        jCBcategorias.setModel(new DefaultComboBoxModel(lista));
//        //</editor-fold>
//    }
//    public int indexCombo(int id, int[] array) {
//        //<editor-fold defaultstate="collapsed" desc="Index ComboBox">
//        for (int i = 0; i < array.length; i++) {
//            if (id == array[i]) {
//                return i;
//            }
//        }
//        return -1;
//        //</editor-fold>
//    }
//    public String dataFormatada(Date dt) {
//        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        return formato.format(dt);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jCBsituacao = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTusuarios = new javax.swing.JTable();
        jBnovo = new javax.swing.JButton();
        jBatualizar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBgravar = new javax.swing.JButton();
        jTFcodigo = new javax.swing.JTextField();
        jTFusuario = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTFnomeUsuario = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPFsenha = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Usuários");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(354, 220));
        setName("usuario"); // NOI18N

        jLabel3.setText("Código:");

        jLabel5.setText("Situação:");

        jCBsituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBsituacao.setEnabled(false);

        jLabel14.setText("Usuário:");

        jTusuarios.setAutoCreateRowSorter(true);
        jTusuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "CÓDIGO", "NOME", "SITUACAO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTusuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTusuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTusuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTusuarios);

        jBnovo.setText("Novo");
        jBnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnovoActionPerformed(evt);
            }
        });

        jBatualizar.setText("Atualizar");
        jBatualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBatualizarActionPerformed(evt);
            }
        });

        jBexcluir.setText("Excluir");
        jBexcluir.setEnabled(false);
        jBexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBexcluirActionPerformed(evt);
            }
        });

        jBgravar.setText("Gravar");
        jBgravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBgravarActionPerformed(evt);
            }
        });

        jTFcodigo.setEditable(false);

        jLabel15.setText("Nome:");

        jLabel16.setText("Senha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBnovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBatualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBexcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBgravar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jTFusuario)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPFsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTFnomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 101, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(260, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jCBsituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBatualizar, jBexcluir, jBgravar, jBnovo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jPFsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFnomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBnovo)
                    .addComponent(jBatualizar)
                    .addComponent(jBgravar)
                    .addComponent(jBexcluir))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCBsituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addContainerGap(269, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTFnomeUsuario, jTFusuario});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTusuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTusuariosMouseClicked
        selecionar();
    }//GEN-LAST:event_jTusuariosMouseClicked

    private void jBnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnovoActionPerformed
        novo();
    }//GEN-LAST:event_jBnovoActionPerformed

    private void jBatualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBatualizarActionPerformed
        atualizar();
    }//GEN-LAST:event_jBatualizarActionPerformed

    private void jBexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBexcluirActionPerformed
        excluir();
    }//GEN-LAST:event_jBexcluirActionPerformed

    private void jBgravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBgravarActionPerformed
        try {
            gravar();
        } catch (Exception ex) {
            Logger.getLogger(CadastroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBgravarActionPerformed

    /*   private class Tempo_salvarArquivo implements Runnable {

        @Override
        public void run() {
            salvarArquivo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
            return;
        }
    }

    private class Tempo_gravarArquivo implements Runnable {

        @Override
        public void run() {
            gravarArquivo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }       
            return;
        }
    }
    
    private class Tempo_tabela implements Runnable {        
        @Override
        public void run() {
            System.out.println("asdfasdfafsadf");
            tabelaAutores();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }*/
    public void limparCampos() {
        jTFcodigo.setText("");
        jTFusuario.setText("");
        jTFnomeUsuario.setText("");
        jCBsituacao.setSelectedIndex(0);
//        jCBentidades.setSelectedIndex(0);
//        jCBcategorias.setSelectedIndex(0);
    }

    public void campos(boolean estado) {
        limparCampos();
        //jTFdiretorio.setEditable(estado);
        jTFusuario.setEditable(estado);
        jCBsituacao.setEnabled(estado);
        jTFnomeUsuario.setEnabled(estado);
    }

    public void novo() {
        limparCampos();
        campos(true);
        jBnovo.setEnabled(false);
        jBgravar.setEnabled(true);
//        jBanexar.setEnabled(true);
        jBexcluir.setEnabled(false);
        jCBsituacao.setEnabled(false);
//        jBselecionar.setEnabled(false);
        jBgravar.setText("Gravar");
        modoGravar = "NOVO";
    }

    public void atualizar() {
//        jProg.setString("");
        limparCampos();
        campos(false);
        jBnovo.setEnabled(true);
        jBgravar.setEnabled(false);
        jBexcluir.setEnabled(false);
//        jBanexar.setEnabled(false);
//        jBselecionar.setEnabled(false);
        tabelaUsuarios();
    }

    public void selecionar() {
//        jProg.setString("");
        modoGravar = "EDITAR";
        modoBuscar = "";
        jBatualizar.setEnabled(true);
        int i_usuario = Integer.parseInt(String.valueOf(jTusuarios.getValueAt(jTusuarios.getSelectedRow(), 0)));
        Usuarios usuarios = emfUsuarios.findUsuarios(i_usuario);
        campos(true);
        jBnovo.setEnabled(true);
        jBgravar.setEnabled(true);
        jBexcluir.setEnabled(true);
//        jBanexar.setEnabled(false);
        jBgravar.setText("Alterar");
        jTFcodigo.setText(String.valueOf(usuarios.getIUsuario()));
        jTFusuario.setText(usuarios.getUsuario());
        jTFnomeUsuario.setText(usuarios.getNomeUsuario());
        if (!usuarios.getSituacaoUsuaruio().equals("ATIVO")) {
            jCBsituacao.setSelectedIndex(1);
        }
//        int i_entidade = autores.getIEntidade().getIEntidade();
//        jCBentidades.setSelectedIndex(indexCombo(i_entidade, arrayEntidades));
//        int i_categoria = autores.getICategoria().getICategoria();
//        jCBcategorias.setSelectedIndex(indexCombo(i_categoria, arrayCategorias));
//        nivelSecretario(nivel);

    }

    public Usuarios gravarCampos(Usuarios autores) {
        autores.setUsuario(jTFusuario.getText());
        autores.setNomeUsuario(jTFnomeUsuario.getText());
        autores.setSituacaoUsuaruio(String.valueOf(jCBsituacao.getSelectedItem()));
//        autores.setICategoria(emfCategorias.findSuporteCategorias(arrayCategorias[jCBcategorias.getSelectedIndex()]));
//        autores.setIEntidade(emfEntidade.findSuporteEntidades(arrayEntidades[jCBentidades.getSelectedIndex()]));
//        autores.setAlteracao(new java.util.Date());
        return autores;
    }

    public void gravar() throws Exception {
        if (validarCamposVazios()) {
            Usuarios usuario = null;
            jBexcluir.setEnabled(false);
            switch (modoGravar) {
                case "NOVO":
                    usuario = new Usuarios();
                    usuario = gravarCampos(usuario);
                    emfUsuarios.create(usuario);
                    jBnovo.setEnabled(true);
//                    jBanexar.setEnabled(true);
                    campos(false);
                    tabelaUsuarios();
                    break;
                case "EDITAR":
                    usuario = emfUsuarios.findUsuarios(Integer.parseInt(jTFcodigo.getText()));
                    usuario = gravarCampos(usuario);
                    try {
                        emfUsuarios.edit(usuario);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(null, "Não foi possível alterar o cadastro.",
                                "ERRO: 001", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    campos(false);
                    tabelaUsuarios();
                    break;
            }
            jBgravar.setEnabled(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Os campos precisam ser informados.", "Atenção! Campos vazios.",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }

    public void excluir() {
        if (javax.swing.JOptionPane.showConfirmDialog(null, "Deseja excluir o item selecionado?", "Excluir item", 1,javax.swing.JOptionPane.QUESTION_MESSAGE) == 0) {
            try {
                jBexcluir.setEnabled(false);
                int id = Integer.parseInt(jTFcodigo.getText());
                emfUsuarios.destroy(id);
                campos(false);
                tabelaUsuarios();
            } catch (controle.exceptions.NonexistentEntityException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Não foi possível excluir o registro.", "ERRO E002", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (java.lang.NumberFormatException ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Não foi possível excluir o registro.", "ERRO E003", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Não foi possível excluir o registro.", "ERRO E004", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean validarCamposVazios() {
        if (jTFusuario.getText() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void tamanhoColunas(javax.swing.JTable tabela) {
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
    }
    /*
    public void salvarArquivo() {
        String nome = (String) jTanexos.getValueAt(jTanexos.getSelectedRow(), 3);
        SelecionarArquivo c = new SelecionarArquivo(new Frames.Login(), rootPaneCheckingEnabled, "selecionar", nome);
        c.setVisible(true);
        this.f = c.file;
        if (f != null) {
            jProg.setIndeterminate(true);
            jProg.setString("TRABALHANDO");
            ResultSet rs = emfAnexos.recuperarArquivo(Integer.parseInt(jTFcodigo.getText()));
            try {
                if (rs.next()) {
                    byte[] bytes = rs.getBytes("arquivo");
                    //converte o array de bytes em file
                    f = new File(f.getAbsolutePath());
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(bytes);
                    fos.close();
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jProg.setIndeterminate(false);
        jProg.setString("CONCLUÍDO");
    }

    public void gravarArquivo() {
        jProg.setIndeterminate(true);
        jProg.setString("TRABALHANDO");
        try {
            gravar();
        } catch (IOException ex) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Erro ao anexar arquivo.\n"
                    + ex.getCause().getMessage(), "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
        }
        jProg.setIndeterminate(false);
        jProg.setString("CONCLUÍDO");
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBatualizar;
    private javax.swing.JButton jBexcluir;
    private javax.swing.JButton jBgravar;
    private javax.swing.JButton jBnovo;
    private javax.swing.JComboBox jCBsituacao;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPFsenha;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFcodigo;
    private javax.swing.JTextField jTFnomeUsuario;
    private javax.swing.JTextField jTFusuario;
    private javax.swing.JTable jTusuarios;
    // End of variables declaration//GEN-END:variables
}
