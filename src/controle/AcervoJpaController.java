/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import controle.exceptions.NonexistentEntityException;
import entidades.Acervo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Autores;
import entidades.Categorias;
import entidades.Editoras;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hilton
 */
public class AcervoJpaController implements Serializable {

    public AcervoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acervo acervo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autores IAutor = acervo.getIAutor();
            if (IAutor != null) {
                IAutor = em.getReference(IAutor.getClass(), IAutor.getIAutor());
                acervo.setIAutor(IAutor);
            }
            Categorias ICategoria = acervo.getICategoria();
            if (ICategoria != null) {
                ICategoria = em.getReference(ICategoria.getClass(), ICategoria.getICatagoria());
                acervo.setICategoria(ICategoria);
            }
            Editoras IEditora = acervo.getIEditora();
            if (IEditora != null) {
                IEditora = em.getReference(IEditora.getClass(), IEditora.getIEditora());
                acervo.setIEditora(IEditora);
            }
            em.persist(acervo);
            if (IAutor != null) {
                IAutor.getAcervoList().add(acervo);
                IAutor = em.merge(IAutor);
            }
            if (ICategoria != null) {
                ICategoria.getAcervoList().add(acervo);
                ICategoria = em.merge(ICategoria);
            }
            if (IEditora != null) {
                IEditora.getAcervoList().add(acervo);
                IEditora = em.merge(IEditora);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acervo acervo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acervo persistentAcervo = em.find(Acervo.class, acervo.getILivro());
            Autores IAutorOld = persistentAcervo.getIAutor();
            Autores IAutorNew = acervo.getIAutor();
            Categorias ICategoriaOld = persistentAcervo.getICategoria();
            Categorias ICategoriaNew = acervo.getICategoria();
            Editoras IEditoraOld = persistentAcervo.getIEditora();
            Editoras IEditoraNew = acervo.getIEditora();
            if (IAutorNew != null) {
                IAutorNew = em.getReference(IAutorNew.getClass(), IAutorNew.getIAutor());
                acervo.setIAutor(IAutorNew);
            }
            if (ICategoriaNew != null) {
                ICategoriaNew = em.getReference(ICategoriaNew.getClass(), ICategoriaNew.getICatagoria());
                acervo.setICategoria(ICategoriaNew);
            }
            if (IEditoraNew != null) {
                IEditoraNew = em.getReference(IEditoraNew.getClass(), IEditoraNew.getIEditora());
                acervo.setIEditora(IEditoraNew);
            }
            acervo = em.merge(acervo);
            if (IAutorOld != null && !IAutorOld.equals(IAutorNew)) {
                IAutorOld.getAcervoList().remove(acervo);
                IAutorOld = em.merge(IAutorOld);
            }
            if (IAutorNew != null && !IAutorNew.equals(IAutorOld)) {
                IAutorNew.getAcervoList().add(acervo);
                IAutorNew = em.merge(IAutorNew);
            }
            if (ICategoriaOld != null && !ICategoriaOld.equals(ICategoriaNew)) {
                ICategoriaOld.getAcervoList().remove(acervo);
                ICategoriaOld = em.merge(ICategoriaOld);
            }
            if (ICategoriaNew != null && !ICategoriaNew.equals(ICategoriaOld)) {
                ICategoriaNew.getAcervoList().add(acervo);
                ICategoriaNew = em.merge(ICategoriaNew);
            }
            if (IEditoraOld != null && !IEditoraOld.equals(IEditoraNew)) {
                IEditoraOld.getAcervoList().remove(acervo);
                IEditoraOld = em.merge(IEditoraOld);
            }
            if (IEditoraNew != null && !IEditoraNew.equals(IEditoraOld)) {
                IEditoraNew.getAcervoList().add(acervo);
                IEditoraNew = em.merge(IEditoraNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = acervo.getILivro();
                if (findAcervo(id) == null) {
                    throw new NonexistentEntityException("The acervo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acervo acervo;
            try {
                acervo = em.getReference(Acervo.class, id);
                acervo.getILivro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acervo with id " + id + " no longer exists.", enfe);
            }
            Autores IAutor = acervo.getIAutor();
            if (IAutor != null) {
                IAutor.getAcervoList().remove(acervo);
                IAutor = em.merge(IAutor);
            }
            Categorias ICategoria = acervo.getICategoria();
            if (ICategoria != null) {
                ICategoria.getAcervoList().remove(acervo);
                ICategoria = em.merge(ICategoria);
            }
            Editoras IEditora = acervo.getIEditora();
            if (IEditora != null) {
                IEditora.getAcervoList().remove(acervo);
                IEditora = em.merge(IEditora);
            }
            em.remove(acervo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acervo> findAcervoEntities() {
        return findAcervoEntities(true, -1, -1);
    }

    public List<Acervo> findAcervoEntities(int maxResults, int firstResult) {
        return findAcervoEntities(false, maxResults, firstResult);
    }

    private List<Acervo> findAcervoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acervo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Acervo findAcervo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acervo.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcervoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acervo> rt = cq.from(Acervo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /* Métodos adicionais */
    public List<Acervo> findAcervoEntitiesSelecao(String titulo, String subtitulo, String isbn,
            /*String nomeAutor, String situacaoLivro, String iCategoria, String iEditora, */String etiqueta, String local, 
            String prateleira, String fileira, String estado, String reparo ,String modelo,
            String sumario, String comentario, String autorNom, String editoraNom, String categoriaNom) {
        String sql = "SELECT a FROM Acervo a "
                + "WHERE a.titulo like '%" + titulo + "%' "
                + "and a.subtitulo like '%" + subtitulo + "%' "
                + "and a.isbn like '%" + isbn + "%' "
                /*+ "and a.iAutor.nomeAutor like '%" + nomeAutor + "%' "
                + "and a.situacaoLivro like '%" + situacaoLivro + "%' "
                + "and a.iCategoria.categoriaNome = " + iCategoria + " "
                + "and a.iEditora.nomeEditora = " + iEditora + " "*/
                + "and a.etiqueta like '%" + etiqueta + "%' "
                + "and a.local like '%" + local + "%' "
                + "and a.prateleira like '%" + prateleira + "%' "
                + "and a.fileira like '%" + fileira + "%' "
                + "and a.estado like '%" + estado + "%' "
                + "and a.reparo like '%" + reparo + "%' "
                + "and a.modelo like '%" + modelo + "%' "
                + "and a.sumario like '%" + sumario + "%' "
                + "and a.comentario like '%" + comentario + "%'"
                + "and a.iAutor.nomeAutor like '%" + autorNom + "%'"
                + "and a.iEditora.nomeEditora like '%" + editoraNom + "%'"
                + "and a.iCategoria.categoriaNome like '%" + categoriaNom + "%'"
                + "order by a.titulo ASC";

        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acervo.class));
            Query q = em.createQuery(sql);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    //medoto adicional
    public List<Acervo> findAcervoEntitiesOrdenado() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acervo.class));
            Query q = em.createQuery("SELECT a FROM Acervo a ORDER BY a.iLivro DESC");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
