/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hilton
 */
@Entity
@Table(name = "AUTORES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autores.findAll", query = "SELECT a FROM Autores a")
    , @NamedQuery(name = "Autores.findByIAutor", query = "SELECT a FROM Autores a WHERE a.iAutor = :iAutor")
    , @NamedQuery(name = "Autores.findByNomeAutor", query = "SELECT a FROM Autores a WHERE a.nomeAutor = :nomeAutor")
    , @NamedQuery(name = "Autores.findByNomeSobrenome", query = "SELECT a FROM Autores a WHERE a.nomeSobrenome = :nomeSobrenome")
    , @NamedQuery(name = "Autores.findBySituacaoAutores", query = "SELECT a FROM Autores a WHERE a.situacaoAutores = :situacaoAutores")})
public class Autores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "I_AUTOR")
    private Integer iAutor;
    @Basic(optional = false)
    @Column(name = "NOME_AUTOR")
    private String nomeAutor;
    @Column(name = "NOME_SOBRENOME")
    private String nomeSobrenome;
    @Column(name = "SITUACAO_AUTORES")
    private String situacaoAutores;
    @OneToMany(mappedBy = "iAutor")
    private List<Acervo> acervoList;

    public Autores() {
    }

    public Autores(Integer iAutor) {
        this.iAutor = iAutor;
    }

    public Autores(Integer iAutor, String nomeAutor) {
        this.iAutor = iAutor;
        this.nomeAutor = nomeAutor;
    }

    public Integer getIAutor() {
        return iAutor;
    }

    public void setIAutor(Integer iAutor) {
        this.iAutor = iAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getNomeSobrenome() {
        return nomeSobrenome;
    }

    public void setNomeSobrenome(String nomeSobrenome) {
        this.nomeSobrenome = nomeSobrenome;
    }

    public String getSituacaoAutores() {
        return situacaoAutores;
    }

    public void setSituacaoAutores(String situacaoAutores) {
        this.situacaoAutores = situacaoAutores;
    }

    @XmlTransient
    public List<Acervo> getAcervoList() {
        return acervoList;
    }

    public void setAcervoList(List<Acervo> acervoList) {
        this.acervoList = acervoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iAutor != null ? iAutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autores)) {
            return false;
        }
        Autores other = (Autores) object;
        if ((this.iAutor == null && other.iAutor != null) || (this.iAutor != null && !this.iAutor.equals(other.iAutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Autores[ iAutor=" + iAutor + " ]";
    }
    
}
