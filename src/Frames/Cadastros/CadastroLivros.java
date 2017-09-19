/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames.Cadastros;

import Frames.Paineis.PainelComentario;
import Frames.Paineis.PainelSumario;
import controle.AcervoJpaController;
import controle.AutoresJpaController;
import controle.CategoriasJpaController;
import controle.EditorasJpaController;
import entidades.Acervo;
import entidades.Autores;
import entidades.Categorias;
import entidades.Editoras;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hilton
 */
public final class CadastroLivros extends javax.swing.JInternalFrame {

    private final AcervoJpaController emfAcervo = new AcervoJpaController(new util.Persistencia().emf());
    private final AutoresJpaController emfAutores = new AutoresJpaController(new util.Persistencia().emf());
    private final CategoriasJpaController emfCategorias = new CategoriasJpaController(new util.Persistencia().emf());
    private final EditorasJpaController emfEditoras = new EditorasJpaController(new util.Persistencia().emf());
    private String modoGravar, modoBuscar = "", modoSelecionar = "";
    private int[] arrayAutores, arrayCategorias, arrayEditoras;
    private File f;

    public CadastroLivros() {
        initComponents();
        tabelaAcervo("");
        setFrameIcon(new javax.swing.ImageIcon(this.getClass().getResource("/imagens/biblioteca_24.png")));
        listarAutores();
        listarCategorias();
        listarEditoras();
        //jLimagem.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("D:\\Hilton\\Imagens\\o_livro_dos_espiritos.png")));
        jTFestado.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFetiqueta.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFfileira.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFisbn.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFlocal.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFmodelo.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFprateleira.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFreparo.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFsubtitulo.setDocument(new util.FormatarCampo(3, "textopadrao"));
        jTFtitulo.setDocument(new util.FormatarCampo(3, "textopadrao"));
    }

    /* public Anexos() {
        initComponents();
        listarEntidades();
        listarCategorias();
        tabelaAutores();
        setFrameIcon(new javax.swing.ImageIcon(this.getClass().getResource("/imagens/icone_2_24.png")));
    
    

    }*/
    public DefaultTableModel tabelaAcervo(String modoSelecionar) {
        //<editor-fold defaultstate="collapsed" desc="Manipulação da Tabela">
        /* Método controlador da tabela*/

        //int iFun = id_funcionario[fu.getSelectedIndex()];
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{
            "CÓDIGO", "TÍTULO", "AUTOR", "CATEGORIA", "PÁGINAS"}) {
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

        List<Acervo> listaAcervo = null;

        try {
//            listaAnexos = emfAnexos.findSuporteAnexosOrdenado();
            listaAcervo = emfAcervo.findAcervoEntitiesOrdenado();

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
        if (!listaAcervo.isEmpty()) {
            for (int i = 0; i < listaAcervo.size(); i++) {
                try {
                    modelo.addRow(linha);
                    try {
                        modelo.setValueAt(listaAcervo.get(i).getILivro(), l, 0);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(listaAcervo.get(i).getTitulo() + " - " + listaAcervo.get(i).getSubtitulo(), l, 1);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(emfAutores.findAutores(listaAcervo.get(i).getIAutor().getIAutor()).getNomeAutor(), l, 2);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(emfCategorias.findCategorias(listaAcervo.get(i).getICategoria().getICatagoria()).getCategoriaNome(), l, 3);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modelo.setValueAt(emfAcervo.findAcervo(listaAcervo.get(i).getILivro()).getPaginas(), l, 4);
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
        jTFtotalCadastros.setText(String.valueOf(listaAcervo.size()));
        //tamanhoColunas(jTautores);
        return modelo;
        //</editor-fold>
    }

    public DefaultTableModel tabelaAcervoSelecionar(String titulo, String subtitulo, String isbn,/* String autorNome, String situacaoLivro,
            String categoriaNome, String editoraNome,*/ String etiqueta, String local, String prateleira, String fileira,
            String estado, String reparo, String modelo, String sumario, String comentario, String autorNom, String editoraNom, String categoriaNom) {
        //<editor-fold defaultstate="collapsed" desc="Manipulação da Tabela">
        /* Método controlador da tabela*/

        //int iFun = id_funcionario[fu.getSelectedIndex()];
        DefaultTableModel modeloSelecionar = new DefaultTableModel(null, new String[]{
            "CÓDIGO", "TÍTULO", "AUTOR", "CATEGORIA", "PÁGINAS"}) {
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

        List<Acervo> listaAcervo = null;

        try {
//            listaAnexos = emfAnexos.findSuporteAnexosOrdenado();

            if (modoSelecionar.equals("")) {
                listaAcervo = emfAcervo.findAcervoEntities();
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
                
                if(autorNom.equals("Selecione...")){
                    autorNom = "";
                }
                if(editoraNom.equals("Selecione...")){
                    editoraNom = "";
                }
                if(categoriaNom.equals("Selecione...")){
                    categoriaNom = "";
                }
                listaAcervo = emfAcervo.findAcervoEntitiesSelecao(titulo, subtitulo, isbn, /*autorNome, situacaoLivro,
                        categoriaNome, editoraNome,*/ etiqueta, local, prateleira, fileira,
                        estado, reparo, modelo, sumario, comentario, autorNom, editoraNom, categoriaNom);
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
        if (!listaAcervo.isEmpty()) {
            for (int i = 0; i < listaAcervo.size(); i++) {
                try {
                    modeloSelecionar.addRow(linha);
                    try {
                        modeloSelecionar.setValueAt(listaAcervo.get(i).getILivro(), l, 0);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modeloSelecionar.setValueAt(listaAcervo.get(i).getTitulo() + " - " + listaAcervo.get(i).getSubtitulo(), l, 1);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modeloSelecionar.setValueAt(emfAutores.findAutores(listaAcervo.get(i).getIAutor().getIAutor()).getNomeAutor(), l, 2);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modeloSelecionar.setValueAt(emfCategorias.findCategorias(listaAcervo.get(i).getICategoria().getICatagoria()).getCategoriaNome(), l, 3);
                    } catch (NullPointerException erro) {
                        System.out.println("Atenção! Campos vazios.");
                    }
                    try {
                        modeloSelecionar.setValueAt(emfAcervo.findAcervo(listaAcervo.get(i).getILivro()).getPaginas(), l, 4);
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
        jTFtotalCadastros.setText(String.valueOf(listaAcervo.size()));
        //tamanhoColunas(jTautores);
        return modeloSelecionar;
        //</editor-fold>
    }

    public void listarAutores() {
        //<editor-fold defaultstate="collapsed" desc="Lista de Entidades">
        List<Autores> listarAutores = emfAutores.findAutoresEntitiesOrdenado();
        String[] lista = new String[listarAutores.size() + 1];
        lista[0] = "Selecione...";
        arrayAutores = new int[listarAutores.size() + 1];
        arrayAutores[0] = 0;
        for (int i = 0; i < lista.length - 1; i++) {
            lista[i + 1] = listarAutores.get(i).getNomeAutor();
            arrayAutores[i + 1] = listarAutores.get(i).getIAutor();
        }
//        new util.ComboDinamico(jCBentidades);
        jCBautor.setModel(new DefaultComboBoxModel(lista));
        //</editor-fold>
    }

    public void listarCategorias() {
        //<editor-fold defaultstate="collapsed" desc="Lista de Entidades">
        List<Categorias> listarCategorias = emfCategorias.findCategoriasEntitiesOrdenado();
        String[] lista = new String[listarCategorias.size() + 1];
        lista[0] = "Selecione...";
        arrayCategorias = new int[listarCategorias.size() + 1];
        arrayCategorias[0] = 0;
        for (int i = 0; i < lista.length - 1; i++) {
            lista[i + 1] = listarCategorias.get(i).getCategoriaNome();
            arrayCategorias[i + 1] = listarCategorias.get(i).getICatagoria();
        }
//        new util.ComboDinamico(jCBentidades);
        jCBcategoria.setModel(new DefaultComboBoxModel(lista));
        //</editor-fold>
    }

    public void listarEditoras() {
        //<editor-fold defaultstate="collapsed" desc="Lista de Entidades">
        List<Editoras> listarEditoras = emfEditoras.findEditorasEntitiesOrdenado();
        String[] lista = new String[listarEditoras.size() + 1];
        lista[0] = "Selecione...";
        arrayEditoras = new int[listarEditoras.size() + 1];
        arrayEditoras[0] = 0;
        for (int i = 0; i < lista.length - 1; i++) {
            lista[i + 1] = listarEditoras.get(i).getNomeEditora();
            arrayEditoras[i + 1] = listarEditoras.get(i).getIEditora();
        }
//        new util.ComboDinamico(jCBentidades);
        jCBeditora.setModel(new DefaultComboBoxModel(lista));
        //</editor-fold>
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

//    public void salvarArquivo() throws URISyntaxException {
///       // String nome = (String) jTacervo.getValueAt(jTacervo.getSelectedRow(), 3);
//        SelecionaCapa c = new SelecionaCapa(new Frames.Gerais.TelaLogin(), rootPaneCheckingEnabled, "selecionar", jTFcaminho.getText());
//        c.setVisible(true);
//        this.f = c.file;
//        if (f != null) {
//            jProg.setIndeterminate(true);
//            jProg.setString("TRABALHANDO");
//            /*ResultSet rs = emfAnexos.recuperarArquivo(Integer.parseInt(jTFcodigo.getText()));
//            try {
//                if (rs.next()) {
//                    byte[] bytes = rs.getBytes("arquivo");
//                    //converte o array de bytes em file
//                    f = new File(f.getAbsolutePath());
//                    FileOutputStream fos = new FileOutputStream(f);
//                    fos.write(bytes);
//                    fos.close();
//                    rs.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
//            }*/
//        }
//        jProg.setIndeterminate(false);
//        jProg.setString("CONCLUÍDO");
//        jTFcaminho.setText(f.getPath());
//        jLimagem.setIcon(new javax.swing.ImageIcon(jTFcaminho.getText()));
//    }/       // String nome = (String) jTacervo.getValueAt(jTacervo.getSelectedRow(), 3);
//        SelecionaCapa c = new SelecionaCapa(new Frames.Gerais.TelaLogin(), rootPaneCheckingEnabled, "selecionar", jTFcaminho.getText());
//        c.setVisible(true);
//        this.f = c.file;
//        if (f != null) {
//            jProg.setIndeterminate(true);
//            jProg.setString("TRABALHANDO");
//            /*ResultSet rs = emfAnexos.recuperarArquivo(Integer.parseInt(jTFcodigo.getText()));
//            try {
//                if (rs.next()) {
//                    byte[] bytes = rs.getBytes("arquivo");
//                    //converte o array de bytes em file
//                    f = new File(f.getAbsolutePath());
//                    FileOutputStream fos = new FileOutputStream(f);
//                    fos.write(bytes);
//                    fos.close();
//                    rs.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(Anexos.class.getName()).log(Level.SEVERE, null, ex);
//            }*/
//        }
//        jProg.setIndeterminate(false);
//        jProg.setString("CONCLUÍDO");
//        jTFcaminho.setText(f.getPath());
//        jLimagem.setIcon(new javax.swing.ImageIcon(jTFcaminho.getText()));
//    }
//    public void gravarArquivo() {
//        jProg.setIndeterminate(true);
//        jProg.setString("TRABALHANDO");
//        try {
//            gravar();
//        } catch (Exception ex) {
//            javax.swing.JOptionPane.showMessageDialog(null,
//                    "Erro ao anexar arquivo.\n"
//                    + ex.getCause().getMessage(), "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
//            Logger.getLogger(CadastroLivros.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        jProg.setIndeterminate(false);
//        jProg.setString("CONCLUÍDO");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTacervo = new javax.swing.JTable();
        jBnovo = new javax.swing.JButton();
        jBatualizar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBgravar = new javax.swing.JButton();
        jTFcodigo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jYCanoPublicacao = new com.toedter.calendar.JYearChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jYCanoEdicao = new com.toedter.calendar.JYearChooser();
        jLabel24 = new javax.swing.JLabel();
        jSpaginas = new javax.swing.JSpinner();
        jDCdtPublicacao = new com.toedter.calendar.JDateChooser();
        jDCedicao = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jSedicao = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jCBcategoria = new javax.swing.JComboBox();
        jTFtitulo = new javax.swing.JTextField();
        jTFisbn = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTFsubtitulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jCBautor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCBeditora = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jTFfileira = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTFetiqueta = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTFlocal = new javax.swing.JTextField();
        jTFprateleira = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTFestado = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTFreparo = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTFmodelo = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTFcaminho = new javax.swing.JTextField();
        jBselecionarCapa = new javax.swing.JButton();
        jProg = new javax.swing.JProgressBar();
        jLimagem = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jBsumario = new javax.swing.JButton();
        jBcomentario = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAsumario = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTAcomentario = new javax.swing.JTextArea();
        jBselecionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTFtotalCadastros = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadastro de Livros");
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(696, 550));
        setName("acervo"); // NOI18N

        jLabel3.setText("Código:");

        jLabel5.setText("Situação:");

        jCBsituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBsituacao.setEnabled(false);

        jTacervo.setAutoCreateRowSorter(true);
        jTacervo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel19.setText("Edição:");

        jYCanoPublicacao.setEnabled(false);

        jLabel20.setText("Publicação:");

        jLabel21.setText("Ano P.:");

        jLabel22.setText("Ano E.:");

        jYCanoEdicao.setEnabled(false);

        jLabel24.setText("Páginas:");

        jSpaginas.setEditor(new javax.swing.JSpinner.NumberEditor(jSpaginas, "####"));
        jSpaginas.setEnabled(false);

        jDCdtPublicacao.setEnabled(false);

        jDCedicao.setEnabled(false);

        jLabel23.setText("Edição nº:");

        jSedicao.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDCdtPublicacao, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jDCedicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYCanoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jYCanoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSedicao))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jYCanoEdicao, jYCanoPublicacao});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDCdtPublicacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jYCanoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jSpaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jYCanoEdicao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSedicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDCedicao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMaximumSize(new java.awt.Dimension(651, 98));

        jLabel8.setText("Categoria:");

        jCBcategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBcategoria.setEnabled(false);
        jCBcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBcategoriaActionPerformed(evt);
            }
        });

        jTFtitulo.setEditable(false);
        jTFtitulo.setMaximumSize(new java.awt.Dimension(6, 20));
        jTFtitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFtituloKeyReleased(evt);
            }
        });

        jTFisbn.setEditable(false);
        jTFisbn.setMaximumSize(new java.awt.Dimension(6, 20));
        jTFisbn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFisbnKeyReleased(evt);
            }
        });

        jLabel16.setText("Título:");

        jLabel17.setText("Subtítulo:");

        jTFsubtitulo.setEditable(false);
        jTFsubtitulo.setMaximumSize(new java.awt.Dimension(6, 20));
        jTFsubtitulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFsubtituloKeyReleased(evt);
            }
        });

        jLabel6.setText("Autor:");

        jCBautor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBautor.setEnabled(false);
        jCBautor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBautorActionPerformed(evt);
            }
        });

        jLabel14.setText("ISBN:");

        jLabel7.setText("Editora:");

        jCBeditora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ATIVO", "DESATIVADO" }));
        jCBeditora.setEnabled(false);
        jCBeditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBeditoraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFsubtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCBcategoria, 0, 288, Short.MAX_VALUE)
                    .addComponent(jTFtitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCBautor, 0, 231, Short.MAX_VALUE)
                    .addComponent(jTFisbn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCBeditora, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jCBcategoria, jTFtitulo});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTFtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTFisbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTFsubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBautor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jCBeditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jCBcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTFfileira.setEditable(false);
        jTFfileira.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFfileiraKeyReleased(evt);
            }
        });

        jLabel18.setText("Etiqueta:");

        jTFetiqueta.setEditable(false);
        jTFetiqueta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFetiquetaKeyReleased(evt);
            }
        });

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Local / Prateleira / Fileira:");

        jTFlocal.setEditable(false);
        jTFlocal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFlocalKeyReleased(evt);
            }
        });

        jTFprateleira.setEditable(false);
        jTFprateleira.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFprateleiraKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFetiqueta))
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTFlocal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFprateleira)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFfileira)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTFetiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFlocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFprateleira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFfileira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel26.setText("Estado:");

        jTFestado.setEditable(false);
        jTFestado.setMaximumSize(new java.awt.Dimension(6, 20));
        jTFestado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFestadoKeyReleased(evt);
            }
        });

        jLabel27.setText("Reparo:");

        jTFreparo.setEditable(false);
        jTFreparo.setMaximumSize(new java.awt.Dimension(6, 20));
        jTFreparo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFreparoKeyReleased(evt);
            }
        });

        jLabel28.setText("Modelo:");

        jTFmodelo.setEditable(false);
        jTFmodelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFmodeloKeyReleased(evt);
            }
        });

        jLabel29.setText("Capa:");

        jTFcaminho.setEditable(false);

        jBselecionarCapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/open_16.png"))); // NOI18N
        jBselecionarCapa.setEnabled(false);
        jBselecionarCapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBselecionarCapaActionPerformed(evt);
            }
        });

        jProg.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jProg.setForeground(new java.awt.Color(255, 102, 0));
        jProg.setRequestFocusEnabled(false);
        jProg.setString("");
        jProg.setStringPainted(true);

        jLimagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLimagem.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jLimagem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jTFcaminho, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBselecionarCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTFestado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFreparo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFmodelo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLimagem, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTFestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTFreparo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTFmodelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBselecionarCapa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(jTFcaminho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProg, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLimagem, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBsumario.setText("Sumário");
        jBsumario.setEnabled(false);
        jBsumario.setPreferredSize(new java.awt.Dimension(90, 23));
        jBsumario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsumarioActionPerformed(evt);
            }
        });

        jBcomentario.setText("Comentário");
        jBcomentario.setEnabled(false);
        jBcomentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcomentarioActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTAsumario.setEditable(false);
        jTAsumario.setColumns(1);
        jTAsumario.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTAsumario.setRows(1);
        jTAsumario.setTabSize(1);
        jTAsumario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTAsumarioKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTAsumario);

        jTAcomentario.setEditable(false);
        jTAcomentario.setColumns(1);
        jTAcomentario.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jTAcomentario.setTabSize(0);
        jTAcomentario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTAcomentarioKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTAcomentario);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBsumario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBcomentario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBsumario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBcomentario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))))
                .addContainerGap())
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBnovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBatualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBexcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBgravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBselecionar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBnovo)
                    .addComponent(jBatualizar)
                    .addComponent(jBgravar)
                    .addComponent(jBexcluir)
                    .addComponent(jBselecionar)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel4, jPanel5});

        getAccessibleContext().setAccessibleName("Cadastro Livros");

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

    private void jBsumarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsumarioActionPerformed
        PainelSumario p = new PainelSumario(jTAsumario.getText());
        p.setVisible(true);
        jTAsumario.setText(p.sumario);
    }//GEN-LAST:event_jBsumarioActionPerformed

    private void jBcomentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcomentarioActionPerformed
        PainelComentario p = new PainelComentario(jTAcomentario.getText());
        p.setVisible(true);
        jTAcomentario.setText(p.comentario);
    }//GEN-LAST:event_jBcomentarioActionPerformed

    private void jBselecionarCapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBselecionarCapaActionPerformed
        try {
            salvarCapa();
        } catch (IOException ex) {
            Logger.getLogger(CadastroLivros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBselecionarCapaActionPerformed

    private void jBselecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBselecionarActionPerformed
        novo("s");
        modoSelecionar = "s";
    }//GEN-LAST:event_jBselecionarActionPerformed

    private void jTFtituloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFtituloKeyReleased
        pesquisar(jTFtitulo);
    }//GEN-LAST:event_jTFtituloKeyReleased

    private void jTFsubtituloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFsubtituloKeyReleased
        pesquisar(jTFsubtitulo);
    }//GEN-LAST:event_jTFsubtituloKeyReleased

    private void jTFisbnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFisbnKeyReleased
        pesquisar(jTFisbn);
    }//GEN-LAST:event_jTFisbnKeyReleased

    private void jTFetiquetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFetiquetaKeyReleased
        pesquisar(jTFetiqueta);
    }//GEN-LAST:event_jTFetiquetaKeyReleased

    private void jTFlocalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFlocalKeyReleased
        pesquisar(jTFlocal);
    }//GEN-LAST:event_jTFlocalKeyReleased

    private void jTFprateleiraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFprateleiraKeyReleased
        pesquisar(jTFprateleira);
    }//GEN-LAST:event_jTFprateleiraKeyReleased

    private void jTFfileiraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFfileiraKeyReleased
        pesquisar(jTFfileira);
    }//GEN-LAST:event_jTFfileiraKeyReleased

    private void jTFestadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFestadoKeyReleased
        pesquisar(jTFestado);
    }//GEN-LAST:event_jTFestadoKeyReleased

    private void jTFreparoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFreparoKeyReleased
        pesquisar(jTFmodelo);
    }//GEN-LAST:event_jTFreparoKeyReleased

    private void jTFmodeloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFmodeloKeyReleased
        pesquisar(jTFreparo);
    }//GEN-LAST:event_jTFmodeloKeyReleased

    private void jTAsumarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAsumarioKeyReleased
        pesquisar2(jTAsumario);
    }//GEN-LAST:event_jTAsumarioKeyReleased

    private void jTAcomentarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAcomentarioKeyReleased
        pesquisar2(jTAcomentario);
    }//GEN-LAST:event_jTAcomentarioKeyReleased

    private void jTacervoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTacervoKeyReleased
        selecionar();
    }//GEN-LAST:event_jTacervoKeyReleased

    private void jCBautorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBautorActionPerformed
        pesquisarCombo();
    }//GEN-LAST:event_jCBautorActionPerformed

    private void jCBeditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBeditoraActionPerformed
        pesquisarCombo();
    }//GEN-LAST:event_jCBeditoraActionPerformed

    private void jCBcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBcategoriaActionPerformed
        pesquisarCombo();
    }//GEN-LAST:event_jCBcategoriaActionPerformed

    public void pesquisar(javax.swing.JTextField componente) {
        if (modoSelecionar.equals("s")) {
            tabelaAcervo(modoSelecionar);
            int i = componente.getText().length();
            if (i > 1) {
                tabelaAcervoSelecionar(jTFtitulo.getText(), jTFsubtitulo.getText(), jTFisbn.getText(),/* autor, situacao, categoria, editora,*/
                        jTFetiqueta.getText(), jTFlocal.getText(), jTFprateleira.getText(), jTFfileira.getText(), jTFestado.getText(),
                        jTFreparo.getText(), jTFmodelo.getText(), jTAsumario.getText(), jTAcomentario.getText(),
                        jCBautor.getSelectedItem().toString(), jCBeditora.getSelectedItem().toString(), jCBcategoria.getSelectedItem().toString());
            }
        }
    }
    
    public void pesquisar2(javax.swing.JTextArea componente) {
        if (modoSelecionar.equals("s")) {
            tabelaAcervo(modoSelecionar);
            int i = componente.getText().length();
            if (i > 3) {
                tabelaAcervoSelecionar(jTFtitulo.getText(), jTFsubtitulo.getText(), jTFisbn.getText(),/* autor, situacao, categoria, editora,*/
                        jTFetiqueta.getText(), jTFlocal.getText(), jTFprateleira.getText(), jTFfileira.getText(), jTFestado.getText(),
                        jTFreparo.getText(), jTFmodelo.getText(), jTAsumario.getText(), jTAcomentario.getText(),
                        jCBautor.getSelectedItem().toString(), jCBeditora.getSelectedItem().toString(),jCBcategoria.getSelectedItem().toString());
            }
        }
    }
    
    public void pesquisarCombo() {
        if (modoSelecionar.equals("s")) {
            tabelaAcervo(modoSelecionar);
                tabelaAcervoSelecionar(jTFtitulo.getText(), jTFsubtitulo.getText(), jTFisbn.getText(),/* autor, situacao, categoria, editora,*/
                        jTFetiqueta.getText(), jTFlocal.getText(), jTFprateleira.getText(), jTFfileira.getText(), jTFestado.getText(),
                        jTFreparo.getText(), jTFmodelo.getText(), jTAsumario.getText(), jTAcomentario.getText(), 
                        jCBautor.getSelectedItem().toString(), jCBeditora.getSelectedItem().toString(),jCBcategoria.getSelectedItem().toString());
            
        }
    }

    public void salvarCapa() throws IOException {
        SelecionaCapa c = new SelecionaCapa(new Frames.Gerais.TelaInicial(1,"admin","admin"), rootPaneCheckingEnabled, jTFcaminho.getText(),0,"Selecione o local da capa");
        c.setVisible(true);
        this.f = c.file;
        if (f != null) {
            jTFcaminho.setText(f.getPath());
            jLimagem.setIcon(new javax.swing.ImageIcon(jTFcaminho.getText()));
        }
    }

    public void limparCampos() {
        jTFcodigo.setText("");
        jTFtitulo.setText("");
        jTFisbn.setText("");
        jTFcaminho.setText("");
        jTFestado.setText("");
        jTFetiqueta.setText("");
        jTFfileira.setText("");
        jTFlocal.setText("");
        jTFmodelo.setText("");
        jTFprateleira.setText("");
        jTFreparo.setText("");
        jTFsubtitulo.setText("");
        jTAsumario.setText("");
        jTAcomentario.setText("");
        jDCdtPublicacao.setDate(new Date());
        jDCedicao.setDate(new Date());
        jYCanoEdicao.setValue(new Date().getYear());
        jYCanoPublicacao.setValue(new Date().getYear());
        jSpaginas.setValue(Integer.parseInt("1"));
        jSedicao.setValue(Integer.parseInt("1"));
        jCBsituacao.setSelectedIndex(0);
        jCBautor.setSelectedIndex(0);
        jCBcategoria.setSelectedIndex(0);
        jCBeditora.setSelectedIndex(0);       

        try {
            jLimagem.setIcon(null);
        } catch (java.lang.NullPointerException ex) {
            jLimagem.setText("Não foi possível carregar a imagem.");
        }
    }

    public void campos(boolean estado) {
        limparCampos();
        jTFtitulo.setEditable(estado);
        jTFisbn.setEditable(estado);
        jTFcaminho.setEditable(estado);
        jTFestado.setEditable(estado);
        jTFetiqueta.setEditable(estado);
        jTFfileira.setEditable(estado);
        jTFlocal.setEditable(estado);;
        jTFmodelo.setEditable(estado);
        jTFprateleira.setEditable(estado);
        jTFreparo.setEditable(estado);
        jTFsubtitulo.setEditable(estado);
        jTAsumario.setEditable(estado);
        jTAcomentario.setEditable(estado);
        jDCdtPublicacao.setEnabled(estado);
        jDCedicao.setEnabled(estado);
        jYCanoEdicao.setEnabled(estado);
        jYCanoPublicacao.setEnabled(estado);
        jSpaginas.setEnabled(estado);
        jSedicao.setEnabled(estado);
        jCBsituacao.setEnabled(estado);
        jCBautor.setEnabled(estado);
        jCBcategoria.setEnabled(estado);
        jCBeditora.setEnabled(estado);
        jBselecionarCapa.setEnabled(estado);
        jBsumario.setEnabled(estado);
        jBcomentario.setEnabled(estado);
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
        tabelaAcervo("");
    }

    public void selecionar() {
//        jProg.setString("");
        modoGravar = "EDITAR";
        modoBuscar = "";
        modoSelecionar = "";
        jBatualizar.setEnabled(true);
        int i_acervo = Integer.parseInt(String.valueOf(jTacervo.getValueAt(jTacervo.getSelectedRow(), 0)));
        Acervo acervo = emfAcervo.findAcervo(i_acervo);
        campos(true);
        jBnovo.setEnabled(true);
        jBgravar.setEnabled(true);
        jBexcluir.setEnabled(true);
        jBselecionar.setEnabled(true);
//        jBanexar.setEnabled(false);
        jBgravar.setText("Alterar");

        jTFcodigo.setText(String.valueOf(acervo.getILivro()));
        jTFtitulo.setText(acervo.getTitulo());
        jTFisbn.setText(acervo.getIsbn());
        jTFestado.setText(acervo.getEstado());
        jTFetiqueta.setText(acervo.getEtiqueta());
        jTFfileira.setText(acervo.getFileira());
        jTFlocal.setText(acervo.getLocal());;
        jTFmodelo.setText(acervo.getModelo());
        jTFprateleira.setText(acervo.getPrateleira());
        jTFreparo.setText(acervo.getReparo());
        jTFsubtitulo.setText(acervo.getSubtitulo());
        jTAsumario.setText(acervo.getSumario());
        jTAcomentario.setText(acervo.getComentario());
        jDCdtPublicacao.setDate(acervo.getDataPublicacao());
        jDCedicao.setDate(acervo.getDataedicao());
        jYCanoEdicao.setValue(acervo.getAnoedicao());
        jYCanoPublicacao.setValue(acervo.getAnoedicao());
        jSpaginas.setValue(acervo.getPaginas());
        jSedicao.setValue(acervo.getEdicao());

        jTFcaminho.setText(acervo.getImagem());
        try {
            jLimagem.setIcon(new javax.swing.ImageIcon(jTFcaminho.getText()));
        } catch (java.lang.NullPointerException ex) {
            jLimagem.setText("Não foi possível carregar a imagem.");
        }

        int i_autor = acervo.getIAutor().getIAutor();
        jCBautor.setSelectedIndex(indexCombo(i_autor, arrayAutores));
        int i_categoria = acervo.getICategoria().getICatagoria();
        jCBcategoria.setSelectedIndex(indexCombo(i_categoria, arrayCategorias));
        int i_editora = acervo.getIEditora().getIEditora();
        jCBeditora.setSelectedIndex(indexCombo(i_editora, arrayEditoras));

        if (!acervo.getSituacaoLivro().equals("ATIVO")) {
            jCBsituacao.setSelectedIndex(1);
        }

//        int i_entidade = autores.getIEntidade().getIEntidade();
//        jCBentidades.setSelectedIndex(indexCombo(i_entidade, arrayEntidades));
//        int i_categoria = autores.getICategoria().getICategoria();
//        jCBcategorias.setSelectedIndex(indexCombo(i_categoria, arrayCategorias));
//        nivelSecretario(nivel);
    }

    public Acervo gravarCampos(Acervo acervo) {
        acervo.setSituacaoLivro(String.valueOf(jCBsituacao.getSelectedItem()));
        acervo.setIAutor(emfAutores.findAutores(arrayAutores[jCBautor.getSelectedIndex()]));
        acervo.setICategoria(emfCategorias.findCategorias(arrayCategorias[jCBcategoria.getSelectedIndex()]));
        acervo.setIEditora(emfEditoras.findEditoras(arrayEditoras[jCBeditora.getSelectedIndex()]));

        acervo.setTitulo(jTFtitulo.getText());
        acervo.setIsbn(jTFisbn.getText());
        acervo.setImagem(jTFcaminho.getText());
        acervo.setEstado(jTFestado.getText());
        acervo.setEtiqueta(jTFetiqueta.getText());
        acervo.setFileira(jTFfileira.getText());
        acervo.setLocal(jTFlocal.getText());
        acervo.setModelo(jTFmodelo.getText());
        acervo.setPrateleira(jTFprateleira.getText());
        acervo.setReparo(jTFreparo.getText());
        acervo.setSubtitulo(jTFsubtitulo.getText());
        acervo.setSumario(jTAsumario.getText());
        acervo.setComentario(jTAcomentario.getText());
        acervo.setDataPublicacao(jDCdtPublicacao.getDate());
        acervo.setDataedicao(jDCedicao.getDate());
        acervo.setAnopublicacao(jYCanoPublicacao.getYear());
        acervo.setAnoedicao(jYCanoEdicao.getYear());
        acervo.setPaginas(Integer.parseInt(String.valueOf(jSpaginas.getValue())));
        acervo.setEdicao(Integer.parseInt(String.valueOf(jSedicao.getValue())));

//        autores.setICategoria(emfCategorias.findSuporteCategorias(arrayCategorias[jCBcategorias.getSelectedIndex()]));
//        autores.setIEntidade(emfEntidade.findSuporteEntidades(arrayEntidades[jCBentidades.getSelectedIndex()]));
//        autores.setAlteracao(new java.util.Date());
        return acervo;
    }

    public void gravar() {
        if (validarCamposVazios()) {
            Acervo acervo = null;
            jBexcluir.setEnabled(false);
            switch (modoGravar) {
                case "NOVO":
                    acervo = new Acervo();
                    acervo = gravarCampos(acervo);
                    emfAcervo.create(acervo);
                    jBnovo.setEnabled(true);
//                    jBanexar.setEnabled(true);
                    campos(false);
                    tabelaAcervo("");
                    break;
                case "EDITAR":
                    acervo = emfAcervo.findAcervo(Integer.parseInt(jTFcodigo.getText()));
                    acervo = gravarCampos(acervo);
                    try {
                        emfAcervo.edit(acervo);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        javax.swing.JOptionPane.showMessageDialog(null, "Não foi possível alterar o cadastro.",
                                "ERRO: 001", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                    campos(false);
                    tabelaAcervo("");
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
                emfAcervo.destroy(id);
                campos(false);
                tabelaAcervo("");
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
        if (jTFtitulo.getText() == null) {
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
    private javax.swing.JButton jBcomentario;
    private javax.swing.JButton jBexcluir;
    private javax.swing.JButton jBgravar;
    private javax.swing.JButton jBnovo;
    private javax.swing.JButton jBselecionar;
    private javax.swing.JButton jBselecionarCapa;
    private javax.swing.JButton jBsumario;
    private javax.swing.JComboBox jCBautor;
    private javax.swing.JComboBox jCBcategoria;
    private javax.swing.JComboBox jCBeditora;
    private javax.swing.JComboBox jCBsituacao;
    private com.toedter.calendar.JDateChooser jDCdtPublicacao;
    private com.toedter.calendar.JDateChooser jDCedicao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLimagem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProg;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSedicao;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpaginas;
    private javax.swing.JTextArea jTAcomentario;
    private javax.swing.JTextArea jTAsumario;
    private javax.swing.JTextField jTFcaminho;
    private javax.swing.JTextField jTFcodigo;
    private javax.swing.JTextField jTFestado;
    private javax.swing.JTextField jTFetiqueta;
    private javax.swing.JTextField jTFfileira;
    private javax.swing.JTextField jTFisbn;
    private javax.swing.JTextField jTFlocal;
    private javax.swing.JTextField jTFmodelo;
    private javax.swing.JTextField jTFprateleira;
    private javax.swing.JTextField jTFreparo;
    private javax.swing.JTextField jTFsubtitulo;
    private javax.swing.JTextField jTFtitulo;
    private javax.swing.JTextField jTFtotalCadastros;
    private javax.swing.JTable jTacervo;
    private com.toedter.calendar.JYearChooser jYCanoEdicao;
    private com.toedter.calendar.JYearChooser jYCanoPublicacao;
    // End of variables declaration//GEN-END:variables
}
