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
@Table(name = "EDITORAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Editoras.findAll", query = "SELECT e FROM Editoras e")
    , @NamedQuery(name = "Editoras.findByIEditora", query = "SELECT e FROM Editoras e WHERE e.iEditora = :iEditora")
    , @NamedQuery(name = "Editoras.findByNomeEditora", query = "SELECT e FROM Editoras e WHERE e.nomeEditora = :nomeEditora")
    , @NamedQuery(name = "Editoras.findBySituacaoEditora", query = "SELECT e FROM Editoras e WHERE e.situacaoEditora = :situacaoEditora")})
public class Editoras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "I_EDITORA")
    private Integer iEditora;
    @Basic(optional = false)
    @Column(name = "NOME_EDITORA")
    private String nomeEditora;
    @Column(name = "SITUACAO_EDITORA")
    private String situacaoEditora;
    @OneToMany(mappedBy = "iEditora")
    private List<Acervo> acervoList;

    public Editoras() {
    }

    public Editoras(Integer iEditora) {
        this.iEditora = iEditora;
    }

    public Editoras(Integer iEditora, String nomeEditora) {
        this.iEditora = iEditora;
        this.nomeEditora = nomeEditora;
    }

    public Integer getIEditora() {
        return iEditora;
    }

    public void setIEditora(Integer iEditora) {
        this.iEditora = iEditora;
    }

    public String getNomeEditora() {
        return nomeEditora;
    }

    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }

    public String getSituacaoEditora() {
        return situacaoEditora;
    }

    public void setSituacaoEditora(String situacaoEditora) {
        this.situacaoEditora = situacaoEditora;
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
        hash += (iEditora != null ? iEditora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editoras)) {
            return false;
        }
        Editoras other = (Editoras) object;
        if ((this.iEditora == null && other.iEditora != null) || (this.iEditora != null && !this.iEditora.equals(other.iEditora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Editoras[ iEditora=" + iEditora + " ]";
    }
    
}
