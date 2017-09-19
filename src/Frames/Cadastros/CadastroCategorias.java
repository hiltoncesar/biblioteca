/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Cadastros;

import controle.CategoriasJpaController;
import entidades.Categorias;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hilton
 */
public final class CadastroCategorias extends javax.swing.JInternalFrame {

    private final CategoriasJpaController emfCategorias = new CategoriasJpaController(new util.Persistencia().emf());
    private String modoGravar, modoBuscar = "";
//    private int[] arrayEntidades, arrayCategorias;

    public CadastroCategorias() {
        initComponents();
        tabelaCategorias();
        setFrameIcon(new javax.swing.ImageIcon(this.getClass().getResource("/imagens/categoria_24.png")));
        jTFnomeCategoria.setDocument(new util.FormatarCampo(3, "textopadrao"));
        
    }

    /* public Anexos() {
        initComponents();
        listarEntidades();
        listarCategorias();
        tabelaCategorias();
        setFrameIcon(new javax.swing.ImageIcon(this.getClass().getResource("/imagens/icone_2_24.png")));

    }*/
    public DefaultTableModel tabelaCategorias() {
        //<editor-fold defaultstate="collapsed" desc="Manipulação da Tabela">
        /* Método controlador da tabela*/

        //int iFun = id_funcionario[fu.getSelectedIndex()];
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{
            "CÓDIGO", "NOME", "SITUAÇÃO"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            @Override
            public boolean isCellEditable(int a, int b) {
                return false;
            }
        };
        String[] linha = new String[]{null, null, null};
        int linhas = modelo.getRowCount();

        List<Categorias> listaCategoriases = null;
        try {
//            listaAutores = emfAnexos.findSuporteAnexosEntitiesOrdenado();
//            listaAnexos = emfAnexos.findSuporteAnexosOrdenado();
            listaCategoriases = emfCategorias.findCategoriasEntitiesOrdenado();
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
        if (!listaCategoriases.isEmpty()) {
            for (int i = 0; i < listaCategoriases.size(); i++) {
                try {
                    modelo.addRow(linha);
                    try {
                        modelo.setValueAt(listaCategoriases.get(i).getICatagoria(), l, 0);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listaCategoriases.get(i).getCategoriaNome(), l, 1);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listaCategoriases.get(i).getSituacaoCatagoria(), l, 2);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                } catch (NullPointerException erro) {
                    System.out.println("Atenção! Campos vazios.");
                }
                l++;
            }
        }
        jTautores.setModel(modelo);
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
        jTautores = new javax.swing.JTable();
        jBnovo = new javax.swing.JButton();
        jBatualizar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBgravar = new javax.swing.JButton();
        jTFcodigo = new javax.swing.JTextField();
        jTFnomeCategoria = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Categorias");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(457, 342));
        setName("categoria"); // NOI18N

        jLabel3.setText("Código:");

        jLabel5.setText("Situação:");

        jCBsituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBsituacao.setEnabled(false);

        jLabel14.setText("Nome:");

        jTautores.setAutoCreateRowSorter(true);
        jTautores.setModel(new javax.swing.table.DefaultTableModel(
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
        jTautores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTautores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTautoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTautores);

        jBnovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_16.png"))); // NOI18N
        jBnovo.setText("Novo");
        jBnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnovoActionPerformed(evt);
            }
        });

        jBatualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/atualizar_16.png"))); // NOI18N
        jBatualizar.setText("Atualizar");
        jBatualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBatualizarActionPerformed(evt);
            }
        });

        jBexcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/deletar_16.png"))); // NOI18N
        jBexcluir.setText("Excluir");
        jBexcluir.setEnabled(false);
        jBexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBexcluirActionPerformed(evt);
            }
        });

        jBgravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/ok_16.png"))); // NOI18N
        jBgravar.setText("Gravar");
        jBgravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBgravarActionPerformed(evt);
            }
        });

        jTFcodigo.setEditable(false);

        jTFnomeCategoria.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBnovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBatualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBexcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBgravar)
                                .addGap(0, 23, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTFnomeCategoria))))
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
                    .addComponent(jTFnomeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTautoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTautoresMouseClicked
        selecionar();
    }//GEN-LAST:event_jTautoresMouseClicked

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
        gravar();
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
            tabelaCategorias();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }*/
    public void limparCampos() {
        jTFcodigo.setText("");
        jTFnomeCategoria.setText("");
        jCBsituacao.setSelectedIndex(0);
//        jCBentidades.setSelectedIndex(0);
//        jCBcategorias.setSelectedIndex(0);
    }

    public void campos(boolean estado) {
        limparCampos();
        //jTFdiretorio.setEditable(estado);
        jTFnomeCategoria.setEditable(estado);
        jCBsituacao.setEnabled(estado);
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
        tabelaCategorias();
    }

    public void selecionar() {
//        jProg.setString("");
        modoGravar = "EDITAR";
        modoBuscar = "";
        jBatualizar.setEnabled(true);
        int i_categoria = Integer.parseInt(String.valueOf(jTautores.getValueAt(jTautores.getSelectedRow(), 0)));
        Categorias categorias = emfCategorias.findCategorias(i_categoria);
        campos(true);
        jBnovo.setEnabled(true);
        jBgravar.setEnabled(true);
        jBexcluir.setEnabled(true);
//        jBanexar.setEnabled(false);
        jBgravar.setText("Alterar");
        jTFcodigo.setText(String.valueOf(categorias.getICatagoria()));
        jTFnomeCategoria.setText(categorias.getCategoriaNome());
        if (!categorias.getSituacaoCatagoria().equals("ATIVO")) {
            jCBsituacao.setSelectedIndex(1);
        }
//        int i_entidade = autores.getIEntidade().getIEntidade();
//        jCBentidades.setSelectedIndex(indexCombo(i_entidade, arrayEntidades));
//        int i_categoria = autores.getICategoria().getICategoria();
//        jCBcategorias.setSelectedIndex(indexCombo(i_categoria, arrayCategorias));
//        nivelSecretario(nivel);

    }

    public Categorias gravarCampos(Categorias categorias) {
        categorias.setCategoriaNome(jTFnomeCategoria.getText());
        categorias.setSituacaoCatagoria(String.valueOf(jCBsituacao.getSelectedItem()));
//        autores.setICategoria(emfCategorias.findSuporteCategorias(arrayCategorias[jCBcategorias.getSelectedIndex()]));
//        autores.setIEntidade(emfEntidade.findSuporteEntidades(arrayEntidades[jCBentidades.getSelectedIndex()]));
//        autores.setAlteracao(new java.util.Date());
        return categorias;
    }

    public void gravar() {
        if (validarCamposVazios()) {
            Categorias categorias = null;
            jBexcluir.setEnabled(false);
            switch (modoGravar) {
                case "NOVO":
                    categorias = new Categorias();
                    categorias = gravarCampos(categorias);
                    emfCategorias.create(categorias);
                    jBnovo.setEnabled(true);
//                    jBanexar.setEnabled(true);
                    campos(false);
                    tabelaCategorias();
                    break;
                case "EDITAR":
                    categorias = emfCategorias.findCategorias(Integer.parseInt(jTFcodigo.getText()));
                    categorias = gravarCampos(categorias);
                    try {
                        emfCategorias.edit(categorias);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(null, "Não foi possível alterar o cadastro.",
                                "ERRO: 001", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    campos(false);
                    tabelaCategorias();
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
                emfCategorias.destroy(id);
                campos(false);
                tabelaCategorias();
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
        if (jTFnomeCategoria.getText() == null) {
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFcodigo;
    private javax.swing.JTextField jTFnomeCategoria;
    private javax.swing.JTable jTautores;
    // End of variables declaration//GEN-END:variables
}
