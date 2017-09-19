/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Cadastros;

import Frames.Paineis.PainelDescricao;
import controle.ArquivosJpaController;
import entidades.Arquivos;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hilton
 */
public final class CadastroArquivos extends javax.swing.JInternalFrame {

    private final ArquivosJpaController emfArquivos = new ArquivosJpaController(new util.Persistencia().emf());
    private String modoGravar, modoBuscar = "", modoSelecionar = "";
    private File f;

    public CadastroArquivos() {
        initComponents();
        tabelaArquivos("");
        setFrameIcon(new javax.swing.ImageIcon(this.getClass().getResource("/imagens/biblioteca_24.png")));
        listarFormatos();
        listarTipos();
        //jLimagem.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("D:\\Hilton\\Imagens\\o_livro_dos_espiritos.png")));
        jTFnome.setDocument(new util.FormatarCampo(3, "textopadrao"));
    }
    
    public DefaultTableModel tabelaArquivos(String modoSelecionar) {
        //<editor-fold defaultstate="collapsed" desc="Manipulação da Tabela">
        /* Método controlador da tabela*/

        //int iFun = id_funcionario[fu.getSelectedIndex()];
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{
            "CÓDIGO", "NOME", "DESCRIÇÃO", "FORMATO", "TIPO"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            @Override
            public boolean isCellEditable(int a, int b) {
                return false;
            }
        };
        String[] linha = new String[]{null, null, null, null, null};
        int linhas = modelo.getRowCount();

        List<Arquivos> listarArquivos = null;

        try {
//            listaAnexos = emfAnexos.findSuporteAnexosOrdenado();
            listarArquivos = emfArquivos.findArquivosEntities();

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
        if (!listarArquivos.isEmpty()) {
            for (int i = 0; i < listarArquivos.size(); i++) {
                try {
                    modelo.addRow(linha);
                    try {
                        modelo.setValueAt(listarArquivos.get(i).getIArquivos(), l, 0);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listarArquivos.get(i).getNomeArquivo(), l, 1);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listarArquivos.get(i).getDescricaoArquivo(), l, 2);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listarArquivos.get(i).getFormato(), l, 3);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listarArquivos.get(i).getITipo(), l, 4);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                } catch (NullPointerException erro) {
                    System.out.println("Atenção! Campos vazios.");
                }
                l++;
            }
        }
        jTacervo.setModel(modelo);
        jTFtotalCadastros.setText(String.valueOf(listarArquivos.size()));
        //tamanhoColunas(jTautores);
        return modelo;
        //</editor-fold>
    }

    public DefaultTableModel tabelaArquivosSelecionar(String nomeArq, String descricao, String formato, String tipo) {
        //<editor-fold defaultstate="collapsed" desc="Manipulação da Tabela">
        /* Método controlador da tabela*/

        //int iFun = id_funcionario[fu.getSelectedIndex()];
        DefaultTableModel modeloSelecionar = new DefaultTableModel(null, new String[]{
            "CÓDIGO", "NOME", "DESCRIÇÃO", "FORMATO", "TIPO"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            @Override
            public boolean isCellEditable(int a, int b) {
                return false;
            }
        };
        String[] linha = new String[]{null, null, null, null, null};
        int linhas = modeloSelecionar.getRowCount();

        List<Arquivos> listarArquivos = null;

        try {
//            listaAnexos = emfAnexos.findSuporteAnexosOrdenado();

            if (modoSelecionar.equals("")) {
                listarArquivos = emfArquivos.findArquivosEntities();
            } else if (modoSelecionar.equals("s")) {
                /*if (autorNome.equals("Selecione...")) {
                    autorNome = "";
                }
                if (categoriaNome.equals("Selecione...")) {
                    categoriaNome = "";
                }
                if (editoraNome.equals("Selecione...")) {
                    editoraNome = "";
                }   */
                
                if(formato.equals("Selecione...")){
                    formato = "";
                }
                if(tipo.equals("Selecione...")){
                    tipo = "";
                }
                listarArquivos = emfArquivos.findArquivosEntities();
            }

        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Erro ao recuperar informações da tabela\n"
                    + ex.getMessage(), "Erro de Conexão", javax.swing.JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        }
        while (linhas > 0) {//limpa conteudo da tabela para atulização
            for (int i = linhas; i > 0; i--) {
                modeloSelecionar.removeRow(i - 1);
                linhas = modeloSelecionar.getRowCount();
            }//fim do for
        }//fim do while

        int l = 0;
        if (!listarArquivos.isEmpty()) {
            for (int i = 0; i < listarArquivos.size(); i++) {
                try {
                    modeloSelecionar.addRow(linha);
                    try {
                        modeloSelecionar.setValueAt(listarArquivos.get(i).getIArquivos(), l, 0);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modeloSelecionar.setValueAt(listarArquivos.get(i).getNomeArquivo(), l, 1);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modeloSelecionar.setValueAt(listarArquivos.get(i).getDescricaoArquivo(), l, 2);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modeloSelecionar.setValueAt(listarArquivos.get(i).getFormato(), l, 3);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modeloSelecionar.setValueAt(listarArquivos.get(i).getITipo(), l, 4);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                } catch (NullPointerException erro) {
                    System.out.println("Atenção! Campos vazios.");
                }
                l++;
            }
        }
        jTacervo.setModel(modeloSelecionar);
        jTFtotalCadastros.setText(String.valueOf(listarArquivos.size()));
        //tamanhoColunas(jTautores);
        return modeloSelecionar;
        //</editor-fold>
    }
    
    public void listarFormatos() {
        List<Arquivos> listarArquivos = emfArquivos.findArquivosEntities();
        String[] lista = new String[listarArquivos.size() + 1];
        lista[0] = "Selecione...";
        for (int i = 0; i < lista.length - 1; i++) {
            lista[i + 1] = listarArquivos.get(i).getFormato();
        }
       // new util.ComboDinamico(jCBtipo);
        jCBformato.setModel(new DefaultComboBoxModel(lista));
    }
    
    public void listarTipos() {
        List<Arquivos> listarArquivos = emfArquivos.findArquivosEntities();
        String[] lista = new String[listarArquivos.size() + 1];
        lista[0] = "Selecione...";
        for (int i = 0; i < lista.length - 1; i++) {
            lista[i + 1] = listarArquivos.get(i).getITipo();
        }
       // new util.ComboDinamico(jCBtipo);
        jCBtipo.setModel(new DefaultComboBoxModel(lista));
    }

    public int indexCombo(int id, int[] array) {
        //<editor-fold defaultstate="collapsed" desc="Index ComboBox">
        for (int i = 0; i < array.length; i++) {
            if (id == array[i]) {
                return i;
            }
        }
        return -1;
        //</editor-fold>
    }

    public String dataFormatada(Date dt) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(dt);
    }

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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTacervo = new javax.swing.JTable();
        jBnovo = new javax.swing.JButton();
        jBatualizar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBgravar = new javax.swing.JButton();
        jTFcodigo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTFnome = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCBformato = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jCBtipo = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAdescricao = new javax.swing.JTextArea();
        jLabel30 = new javax.swing.JLabel();
        jBdescricao = new javax.swing.JButton();
        jTFpasta = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jBselecionarArquivo = new javax.swing.JButton();
        jBselecionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTFtotalCadastros = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Arquivos");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(525, 437));
        setName("arquivo"); // NOI18N

        jLabel3.setText("Código:");

        jLabel5.setText("Situação:");

        jCBsituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBsituacao.setEnabled(false);

        jTacervo.setAutoCreateRowSorter(true);
        jTacervo.setModel(new javax.swing.table.DefaultTableModel(
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
        jTacervo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTacervo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTacervoMouseClicked(evt);
            }
        });
        jTacervo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTacervoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTacervo);

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

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTFnome.setEditable(false);
        jTFnome.setMaximumSize(new java.awt.Dimension(6, 20));
        jTFnome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFnomeKeyReleased(evt);
            }
        });

        jLabel16.setText("Nome:");

        jLabel6.setText("Formato:");

        jCBformato.setEditable(true);
        jCBformato.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBformato.setEnabled(false);
        jCBformato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBformatoActionPerformed(evt);
            }
        });

        jLabel7.setText("Tipo:");

        jCBtipo.setEditable(true);
        jCBtipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBtipo.setEnabled(false);
        jCBtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBtipoActionPerformed(evt);
            }
        });

        jTAdescricao.setEditable(false);
        jTAdescricao.setColumns(1);
        jTAdescricao.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTAdescricao.setRows(1);
        jTAdescricao.setTabSize(1);
        jTAdescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTAdescricaoKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTAdescricao);

        jLabel30.setText("Descrição:");

        jBdescricao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add_16.png"))); // NOI18N
        jBdescricao.setEnabled(false);
        jBdescricao.setPreferredSize(new java.awt.Dimension(90, 23));
        jBdescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBdescricaoActionPerformed(evt);
            }
        });

        jTFpasta.setEditable(false);

        jLabel29.setText("Pasta:");

        jBselecionarArquivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/open_16.png"))); // NOI18N
        jBselecionarArquivo.setEnabled(false);
        jBselecionarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBselecionarArquivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel29)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCBtipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCBformato, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jTFpasta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBselecionarArquivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBdescricao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTFnome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBdescricao, jBselecionarArquivo});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTFnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(jTFpasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jBselecionarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)))
                    .addComponent(jBdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jCBformato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCBtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBselecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/buscar_16.png"))); // NOI18N
        jBselecionar.setText("Buscar");
        jBselecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBselecionarActionPerformed(evt);
            }
        });

        jLabel1.setText("Total de cadastros:");

        jTFtotalCadastros.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBnovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBatualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBexcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBgravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBselecionar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCBsituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFtotalCadastros, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBatualizar, jBexcluir, jBgravar, jBnovo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jCBsituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTFtotalCadastros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBnovo)
                    .addComponent(jBatualizar)
                    .addComponent(jBgravar)
                    .addComponent(jBexcluir)
                    .addComponent(jBselecionar))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Cadastro Arquivos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTacervoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTacervoMouseClicked
        selecionar();
    }//GEN-LAST:event_jTacervoMouseClicked

    private void jBnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnovoActionPerformed
        novo("novo");
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

    private void jBdescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBdescricaoActionPerformed
        PainelDescricao p = new PainelDescricao(jTAdescricao.getText());
        p.setVisible(true);
        jTAdescricao.setText(p.sumario);
    }//GEN-LAST:event_jBdescricaoActionPerformed

    private void jBselecionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBselecionarArquivoActionPerformed
        try {
            salvarCapa();
        } catch (IOException ex) {
            Logger.getLogger(CadastroArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBselecionarArquivoActionPerformed

    private void jBselecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBselecionarActionPerformed
        novo("s");
        modoSelecionar = "s";
    }//GEN-LAST:event_jBselecionarActionPerformed

    private void jTFnomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnomeKeyReleased
        pesquisar(jTFnome);
    }//GEN-LAST:event_jTFnomeKeyReleased

    private void jTAdescricaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAdescricaoKeyReleased
        pesquisar2(jTAdescricao);
    }//GEN-LAST:event_jTAdescricaoKeyReleased

    private void jTacervoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTacervoKeyReleased
        selecionar();
    }//GEN-LAST:event_jTacervoKeyReleased

    private void jCBformatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBformatoActionPerformed
        pesquisarCombo();
    }//GEN-LAST:event_jCBformatoActionPerformed

    private void jCBtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBtipoActionPerformed
        pesquisarCombo();
    }//GEN-LAST:event_jCBtipoActionPerformed

    public void pesquisar(javax.swing.JTextField componente) {
        if (modoSelecionar.equals("s")) {
            tabelaArquivos(modoSelecionar);
            int i = componente.getText().length();
            if (i > 1) {
                tabelaArquivosSelecionar("","","","");
            }
        }
    }
    
    public void pesquisar2(javax.swing.JTextArea componente) {
        if (modoSelecionar.equals("s")) {
            tabelaArquivos(modoSelecionar);
            int i = componente.getText().length();
            if (i > 3) {
                tabelaArquivosSelecionar("","","","");
            }
        }
    }
    
    public void pesquisarCombo() {
        if (modoSelecionar.equals("s")) {
            tabelaArquivos(modoSelecionar);
                tabelaArquivosSelecionar("","","","");
            
        }
    }
    
     public void salvarCapa() throws IOException {
        SelecionaCapa c = new SelecionaCapa(new Frames.Gerais.TelaInicial(1,"admin","admin"), rootPaneCheckingEnabled, jTFpasta.getText(),0,"Selecione o local do arquivo");
        c.setVisible(true);
        this.f = c.file;
        if (f != null) {
            jTFpasta.setText(f.getPath());
            //jLimagem.setIcon(new javax.swing.ImageIcon(jTFcaminho.getText()));
        }
    }

    public void limparCampos() {
        jTFcodigo.setText("");
        jTFnome.setText("");
        jTFpasta.setText("");
        jTAdescricao.setText("");
        jCBsituacao.setSelectedIndex(0);
        jCBformato.setSelectedIndex(0);
        jCBtipo.setSelectedIndex(0);       

//        try {
//            jLimagem.setIcon(null);
//        } catch (java.lang.NullPointerException ex) {
//            jLimagem.setText("Não foi possível carregar a imagem.");
//        }
    }

    public void campos(boolean estado) {
        limparCampos();
        jTFnome.setEditable(estado);
        jTFpasta.setEditable(estado);
        jTAdescricao.setEditable(estado);
        jCBsituacao.setEnabled(estado);
        jCBformato.setEnabled(estado);
        jCBtipo.setEnabled(estado);
        jBselecionarArquivo.setEnabled(estado);
        jBdescricao.setEnabled(estado);
    }

    public void novo(String modo) {
        limparCampos();
        campos(true);
        if (modo.equals("novo")) {
            jBnovo.setEnabled(false);
            jBgravar.setEnabled(true);
            jBexcluir.setEnabled(false);
            jBselecionar.setEnabled(false);
            jCBsituacao.setEnabled(false);
            jBgravar.setText("Gravar");
            modoGravar = "NOVO";
            modoSelecionar = "";
        } else {
            jBnovo.setEnabled(true);
            jBgravar.setEnabled(false);
            jBexcluir.setEnabled(false);
            jCBsituacao.setEnabled(true);
        }
    }

    public void atualizar() {
        modoSelecionar = "";
//        jProg.setString("");
        limparCampos();
        campos(false);
        jBnovo.setEnabled(true);
        jBselecionar.setEnabled(true);
        jBgravar.setEnabled(false);
        jBexcluir.setEnabled(false);
//        jBanexar.setEnabled(false);
//        jBselecionar.setEnabled(false);
        tabelaArquivos("");
    }

    public void selecionar() {
//        jProg.setString("");
        modoGravar = "EDITAR";
        modoBuscar = "";
        modoSelecionar = "";
        jBatualizar.setEnabled(true);
        int i_arquivo = Integer.parseInt(String.valueOf(jTacervo.getValueAt(jTacervo.getSelectedRow(), 0)));
        Arquivos arquivo = emfArquivos.findArquivos(i_arquivo);
        campos(true);
        jBnovo.setEnabled(true);
        jBgravar.setEnabled(true);
        jBexcluir.setEnabled(true);
        jBselecionar.setEnabled(true);
//        jBanexar.setEnabled(false);
        jBgravar.setText("Alterar");

        jTFcodigo.setText(String.valueOf(arquivo.getIArquivos()));
        jTFnome.setText(arquivo.getNomeArquivo());
        jTAdescricao.setText(arquivo.getDescricaoArquivo());

        jTFpasta.setText(arquivo.getCaminhoArquivo());
//        try {
//            jLimagem.setIcon(new javax.swing.ImageIcon(jTFpasta.getText()));
//        } catch (java.lang.NullPointerException ex) {
//            jLimagem.setText("Não foi possível carregar a imagem.");
//        }

        
        jCBformato.setSelectedItem(arquivo.getFormato());
        jCBtipo.setSelectedItem(arquivo.getITipo());

        if (!arquivo.getSituacaoArquivo().equals("ATIVO")) {
            jCBsituacao.setSelectedIndex(1);
        }

//        int i_entidade = autores.getIEntidade().getIEntidade();
//        jCBentidades.setSelectedIndex(indexCombo(i_entidade, arrayEntidades));
//        int i_categoria = autores.getICategoria().getICategoria();
//        jCBcategorias.setSelectedIndex(indexCombo(i_categoria, arrayCategorias));
//        nivelSecretario(nivel);
    }

    public Arquivos gravarCampos(Arquivos arquivo) {
        arquivo.setSituacaoArquivo(String.valueOf(jCBsituacao.getSelectedItem()));
        arquivo.setFormato(jCBformato.getSelectedItem().toString());
        arquivo.setITipo(jCBtipo.getSelectedItem().toString());
        arquivo.setNomeArquivo(jTFnome.getText());
        arquivo.setDescricaoArquivo(jTAdescricao.getText());
        arquivo.setCaminhoArquivo(jTFpasta.getText());
        return arquivo;
    }

    public void gravar() {
        if (validarCamposVazios()) {
            Arquivos arquivo = null;
            jBexcluir.setEnabled(false);
            switch (modoGravar) {
                case "NOVO":
                    arquivo = new Arquivos();
                    arquivo = gravarCampos(arquivo);
                    emfArquivos.create(arquivo);
                    jBnovo.setEnabled(true);
//                    jBanexar.setEnabled(true);
                    campos(false);
                    tabelaArquivos("");
                    break;
                case "EDITAR":
                    arquivo = emfArquivos.findArquivos(Integer.parseInt(jTFcodigo.getText()));
                    arquivo = gravarCampos(arquivo);
                    try {
                        emfArquivos.edit(arquivo);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(null, "Não foi possível alterar o cadastro.",
                                "ERRO: 001", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    campos(false);
                    tabelaArquivos("");
                    break;
            }
            jBgravar.setEnabled(false);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Os campos precisam ser informados.", "Atenção! Campos vazios.",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }

    public void excluir() {
        if (javax.swing.JOptionPane.showConfirmDialog(null, "Deseja excluir o item selecionado?", "Excluir item", 1, javax.swing.JOptionPane.QUESTION_MESSAGE) == 0) {
            try {
                jBexcluir.setEnabled(false);
                int id = Integer.parseInt(jTFcodigo.getText());
                emfArquivos.destroy(id);
                campos(false);
                tabelaArquivos("");
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
        if (jTFnome.getText() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void tamanhoColunas(javax.swing.JTable tabela) {
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBatualizar;
    private javax.swing.JButton jBdescricao;
    private javax.swing.JButton jBexcluir;
    private javax.swing.JButton jBgravar;
    private javax.swing.JButton jBnovo;
    private javax.swing.JButton jBselecionar;
    private javax.swing.JButton jBselecionarArquivo;
    private javax.swing.JComboBox jCBformato;
    private javax.swing.JComboBox jCBsituacao;
    private javax.swing.JComboBox jCBtipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAdescricao;
    private javax.swing.JTextField jTFcodigo;
    private javax.swing.JTextField jTFnome;
    private javax.swing.JTextField jTFpasta;
    private javax.swing.JTextField jTFtotalCadastros;
    private javax.swing.JTable jTacervo;
    // End of variables declaration//GEN-END:variables
}
