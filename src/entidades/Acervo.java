/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "ACERVO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acervo.findAll", query = "SELECT a FROM Acervo a")
    , @NamedQuery(name = "Acervo.findByILivro", query = "SELECT a FROM Acervo a WHERE a.iLivro = :iLivro")
    , @NamedQuery(name = "Acervo.findByDataPublicacao", query = "SELECT a FROM Acervo a WHERE a.dataPublicacao = :dataPublicacao")
    , @NamedQuery(name = "Acervo.findByPaginas", query = "SELECT a FROM Acervo a WHERE a.paginas = :paginas")
    , @NamedQuery(name = "Acervo.findByEdicao", query = "SELECT a FROM Acervo a WHERE a.edicao = :edicao")
    , @NamedQuery(name = "Acervo.findByImagem", query = "SELECT a FROM Acervo a WHERE a.imagem = :imagem")
    , @NamedQuery(name = "Acervo.findByEstado", query = "SELECT a FROM Acervo a WHERE a.estado = :estado")
    , @NamedQuery(name = "Acervo.findByReparo", query = "SELECT a FROM Acervo a WHERE a.reparo = :reparo")
    , @NamedQuery(name = "Acervo.findByLocal", query = "SELECT a FROM Acervo a WHERE a.local = :local")
    , @NamedQuery(name = "Acervo.findByPrateleira", query = "SELECT a FROM Acervo a WHERE a.prateleira = :prateleira")
    , @NamedQuery(name = "Acervo.findByFileira", query = "SELECT a FROM Acervo a WHERE a.fileira = :fileira")
    , @NamedQuery(name = "Acervo.findByEtiqueta", query = "SELECT a FROM Acervo a WHERE a.etiqueta = :etiqueta")
    , @NamedQuery(name = "Acervo.findByEmprestimo", query = "SELECT a FROM Acervo a WHERE a.emprestimo = :emprestimo")
    , @NamedQuery(name = "Acervo.findByIsbn", query = "SELECT a FROM Acervo a WHERE a.isbn = :isbn")
    , @NamedQuery(name = "Acervo.findBySumario", query = "SELECT a FROM Acervo a WHERE a.sumario = :sumario")
    , @NamedQuery(name = "Acervo.findByComentario", query = "SELECT a FROM Acervo a WHERE a.comentario = :comentario")
    , @NamedQuery(name = "Acervo.findBySituacaoLivro", query = "SELECT a FROM Acervo a WHERE a.situacaoLivro = :situacaoLivro")
    , @NamedQuery(name = "Acervo.findByTitulo", query = "SELECT a FROM Acervo a WHERE a.titulo = :titulo")
    , @NamedQuery(name = "Acervo.findBySubtitulo", query = "SELECT a FROM Acervo a WHERE a.subtitulo = :subtitulo")
    , @NamedQuery(name = "Acervo.findByModelo", query = "SELECT a FROM Acervo a WHERE a.modelo = :modelo")
    , @NamedQuery(name = "Acervo.findByDatapublicacao", query = "SELECT a FROM Acervo a WHERE a.datapublicacao = :datapublicacao")
    , @NamedQuery(name = "Acervo.findByAnoedicao", query = "SELECT a FROM Acervo a WHERE a.anoedicao = :anoedicao")
    , @NamedQuery(name = "Acervo.findByAnopublicacao", query = "SELECT a FROM Acervo a WHERE a.anopublicacao = :anopublicacao")
    , @NamedQuery(name = "Acervo.findByMesanoedicao", query = "SELECT a FROM Acervo a WHERE a.mesanoedicao = :mesanoedicao")
    , @NamedQuery(name = "Acervo.findByMesanopublicacao", query = "SELECT a FROM Acervo a WHERE a.mesanopublicacao = :mesanopublicacao")
    , @NamedQuery(name = "Acervo.findByDataedicao", query = "SELECT a FROM Acervo a WHERE a.dataedicao = :dataedicao")})
public class Acervo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "I_LIVRO")
    private Integer iLivro;
    @Column(name = "DATA_PUBLICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPublicacao;
    @Column(name = "PAGINAS")
    private Integer paginas;
    @Column(name = "EDICAO")
    private Integer edicao;
    @Column(name = "IMAGEM")
    private String imagem;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "REPARO")
    private String reparo;
    @Column(name = "LOCAL")
    private String local;
    @Column(name = "PRATELEIRA")
    private String prateleira;
    @Column(name = "FILEIRA")
    private String fileira;
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @Column(name = "EMPRESTIMO")
    private String emprestimo;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "SUMARIO")
    private String sumario;
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "SITUACAO_LIVRO")
    private String situacaoLivro;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "SUBTITULO")
    private String subtitulo;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "DATAPUBLICACAO")
    @Temporal(TemporalType.DATE)
    private Date datapublicacao;
    @Column(name = "ANOEDICAO")
    private Integer anoedicao;
    @Column(name = "ANOPUBLICACAO")
    private Integer anopublicacao;
    @Column(name = "MESANOEDICAO")
    private String mesanoedicao;
    @Column(name = "MESANOPUBLICACAO")
    private String mesanopublicacao;
    @Column(name = "DATAEDICAO")
    @Temporal(TemporalType.DATE)
    private Date dataedicao;
    @JoinColumn(name = "I_AUTOR", referencedColumnName = "I_AUTOR")
    @ManyToOne
    private Autores iAutor;
    @JoinColumn(name = "I_CATEGORIA", referencedColumnName = "I_CATAGORIA")
    @ManyToOne
    private Categorias iCategoria;
    @JoinColumn(name = "I_EDITORA", referencedColumnName = "I_EDITORA")
    @ManyToOne
    private Editoras iEditora;

    public Acervo() {
    }

    public Acervo(Integer iLivro) {
        this.iLivro = iLivro;
    }

    public Integer getILivro() {
        return iLivro;
    }

    public void setILivro(Integer iLivro) {
        this.iLivro = iLivro;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReparo() {
        return reparo;
    }

    public void setReparo(String reparo) {
        this.reparo = reparo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public String getFileira() {
        return fileira;
    }

    public void setFileira(String fileira) {
        this.fileira = fileira;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(String emprestimo) {
        this.emprestimo = emprestimo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getSituacaoLivro() {
        return situacaoLivro;
    }

    public void setSituacaoLivro(String situacaoLivro) {
        this.situacaoLivro = situacaoLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getDatapublicacao() {
        return datapublicacao;
    }

    public void setDatapublicacao(Date datapublicacao) {
        this.datapublicacao = datapublicacao;
    }

    public Integer getAnoedicao() {
        return anoedicao;
    }

    public void setAnoedicao(Integer anoedicao) {
        this.anoedicao = anoedicao;
    }

    public Integer getAnopublicacao() {
        return anopublicacao;
    }

    public void setAnopublicacao(Integer anopublicacao) {
        this.anopublicacao = anopublicacao;
    }

    public String getMesanoedicao() {
        return mesanoedicao;
    }

    public void setMesanoedicao(String mesanoedicao) {
        this.mesanoedicao = mesanoedicao;
    }

    public String getMesanopublicacao() {
        return mesanopublicacao;
    }

    public void setMesanopublicacao(String mesanopublicacao) {
        this.mesanopublicacao = mesanopublicacao;
    }

    public Date getDataedicao() {
        return dataedicao;
    }

    public void setDataedicao(Date dataedicao) {
        this.dataedicao = dataedicao;
    }

    public Autores getIAutor() {
        return iAutor;
    }

    public void setIAutor(Autores iAutor) {
        this.iAutor = iAutor;
    }

    public Categorias getICategoria() {
        return iCategoria;
    }

    public void setICategoria(Categorias iCategoria) {
        this.iCategoria = iCategoria;
    }

    public Editoras getIEditora() {
        return iEditora;
    }

    public void setIEditora(Editoras iEditora) {
        this.iEditora = iEditora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iLivro != null ? iLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acervo)) {
            return false;
        }
        Acervo other = (Acervo) object;
        if ((this.iLivro == null && other.iLivro != null) || (this.iLivro != null && !this.iLivro.equals(other.iLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Acervo[ iLivro=" + iLivro + " ]";
    }
    
}
