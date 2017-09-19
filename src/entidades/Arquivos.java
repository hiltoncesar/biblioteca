/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hilton
 */
@Entity
@Table(name = "ARQUIVOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arquivos.findAll", query = "SELECT a FROM Arquivos a")
    , @NamedQuery(name = "Arquivos.findByIArquivos", query = "SELECT a FROM Arquivos a WHERE a.iArquivos = :iArquivos")
    , @NamedQuery(name = "Arquivos.findByNomeArquivo", query = "SELECT a FROM Arquivos a WHERE a.nomeArquivo = :nomeArquivo")
    , @NamedQuery(name = "Arquivos.findByDescricaoArquivo", query = "SELECT a FROM Arquivos a WHERE a.descricaoArquivo = :descricaoArquivo")
    , @NamedQuery(name = "Arquivos.findByCaminhoArquivo", query = "SELECT a FROM Arquivos a WHERE a.caminhoArquivo = :caminhoArquivo")
    , @NamedQuery(name = "Arquivos.findByFormato", query = "SELECT a FROM Arquivos a WHERE a.formato = :formato")
    , @NamedQuery(name = "Arquivos.findByITipo", query = "SELECT a FROM Arquivos a WHERE a.iTipo = :iTipo")
    , @NamedQuery(name = "Arquivos.findBySituacaoArquivo", query = "SELECT a FROM Arquivos a WHERE a.situacaoArquivo = :situacaoArquivo")})
public class Arquivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "I_ARQUIVOS")
    private Integer iArquivos;
    @Column(name = "NOME_ARQUIVO")
    private String nomeArquivo;
    @Column(name = "DESCRICAO_ARQUIVO")
    private String descricaoArquivo;
    @Column(name = "CAMINHO_ARQUIVO")
    private String caminhoArquivo;
    @Column(name = "FORMATO")
    private String formato;
    @Column(name = "I_TIPO")
    private String iTipo;
    @Column(name = "SITUACAO_ARQUIVO")
    private String situacaoArquivo;

    public Arquivos() {
    }

    public Arquivos(Integer iArquivos) {
        this.iArquivos = iArquivos;
    }

    public Integer getIArquivos() {
        return iArquivos;
    }

    public void setIArquivos(Integer iArquivos) {
        this.iArquivos = iArquivos;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getDescricaoArquivo() {
        return descricaoArquivo;
    }

    public void setDescricaoArquivo(String descricaoArquivo) {
        this.descricaoArquivo = descricaoArquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getITipo() {
        return iTipo;
    }

    public void setITipo(String iTipo) {
        this.iTipo = iTipo;
    }

    public String getSituacaoArquivo() {
        return situacaoArquivo;
    }

    public void setSituacaoArquivo(String situacaoArquivo) {
        this.situacaoArquivo = situacaoArquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iArquivos != null ? iArquivos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arquivos)) {
            return false;
        }
        Arquivos other = (Arquivos) object;
        if ((this.iArquivos == null && other.iArquivos != null) || (this.iArquivos != null && !this.iArquivos.equals(other.iArquivos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Arquivos[ iArquivos=" + iArquivos + " ]";
    }
    
}
