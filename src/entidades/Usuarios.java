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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hilton
 */
@Entity
@Table(name = "USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByIUsuario", query = "SELECT u FROM Usuarios u WHERE u.iUsuario = :iUsuario")
    , @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuarios.findBySenha", query = "SELECT u FROM Usuarios u WHERE u.senha = :senha")
    , @NamedQuery(name = "Usuarios.findByNomeUsuario", query = "SELECT u FROM Usuarios u WHERE u.nomeUsuario = :nomeUsuario")
    , @NamedQuery(name = "Usuarios.findByNivel", query = "SELECT u FROM Usuarios u WHERE u.nivel = :nivel")
    , @NamedQuery(name = "Usuarios.findBySituacaoUsuaruio", query = "SELECT u FROM Usuarios u WHERE u.situacaoUsuaruio = :situacaoUsuaruio")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "I_USUARIO")
    private Integer iUsuario;
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;
    @Column(name = "SENHA")
    private String senha;
    @Basic(optional = false)
    @Column(name = "NOME_USUARIO")
    private String nomeUsuario;
    @Basic(optional = false)
    @Column(name = "NIVEL")
    private String nivel;
    @Column(name = "SITUACAO_USUARUIO")
    private String situacaoUsuaruio;

    public Usuarios() {
    }

    public Usuarios(Integer iUsuario) {
        this.iUsuario = iUsuario;
    }

    public Usuarios(Integer iUsuario, String usuario, String nomeUsuario, String nivel) {
        this.iUsuario = iUsuario;
        this.usuario = usuario;
        this.nomeUsuario = nomeUsuario;
        this.nivel = nivel;
    }

    public Integer getIUsuario() {
        return iUsuario;
    }

    public void setIUsuario(Integer iUsuario) {
        this.iUsuario = iUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSituacaoUsuaruio() {
        return situacaoUsuaruio;
    }

    public void setSituacaoUsuaruio(String situacaoUsuaruio) {
        this.situacaoUsuaruio = situacaoUsuaruio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iUsuario != null ? iUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.iUsuario == null && other.iUsuario != null) || (this.iUsuario != null && !this.iUsuario.equals(other.iUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuarios[ iUsuario=" + iUsuario + " ]";
    }
    
}
