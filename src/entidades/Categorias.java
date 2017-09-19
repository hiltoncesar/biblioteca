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
@Table(name = "CATEGORIAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorias.findAll", query = "SELECT c FROM Categorias c")
    , @NamedQuery(name = "Categorias.findByICatagoria", query = "SELECT c FROM Categorias c WHERE c.iCatagoria = :iCatagoria")
    , @NamedQuery(name = "Categorias.findByCategoriaNome", query = "SELECT c FROM Categorias c WHERE c.categoriaNome = :categoriaNome")
    , @NamedQuery(name = "Categorias.findBySituacaoCatagoria", query = "SELECT c FROM Categorias c WHERE c.situacaoCatagoria = :situacaoCatagoria")})
public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "I_CATAGORIA")
    private Integer iCatagoria;
    @Basic(optional = false)
    @Column(name = "CATEGORIA_NOME")
    private String categoriaNome;
    @Column(name = "SITUACAO_CATAGORIA")
    private String situacaoCatagoria;
    @OneToMany(mappedBy = "iCategoria")
    private List<Acervo> acervoList;

    public Categorias() {
    }

    public Categorias(Integer iCatagoria) {
        this.iCatagoria = iCatagoria;
    }

    public Categorias(Integer iCatagoria, String categoriaNome) {
        this.iCatagoria = iCatagoria;
        this.categoriaNome = categoriaNome;
    }

    public Integer getICatagoria() {
        return iCatagoria;
    }

    public void setICatagoria(Integer iCatagoria) {
        this.iCatagoria = iCatagoria;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getSituacaoCatagoria() {
        return situacaoCatagoria;
    }

    public void setSituacaoCatagoria(String situacaoCatagoria) {
        this.situacaoCatagoria = situacaoCatagoria;
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
        hash += (iCatagoria != null ? iCatagoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorias)) {
            return false;
        }
        Categorias other = (Categorias) object;
        if ((this.iCatagoria == null && other.iCatagoria != null) || (this.iCatagoria != null && !this.iCatagoria.equals(other.iCatagoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Categorias[ iCatagoria=" + iCatagoria + " ]";
    }
    
}
