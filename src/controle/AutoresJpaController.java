/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import controle.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Acervo;
import entidades.Autores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hilton
 */
public class AutoresJpaController implements Serializable {

    public AutoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autores autores) {
        if (autores.getAcervoList() == null) {
            autores.setAcervoList(new ArrayList<Acervo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Acervo> attachedAcervoList = new ArrayList<Acervo>();
            for (Acervo acervoListAcervoToAttach : autores.getAcervoList()) {
                acervoListAcervoToAttach = em.getReference(acervoListAcervoToAttach.getClass(), acervoListAcervoToAttach.getILivro());
                attachedAcervoList.add(acervoListAcervoToAttach);
            }
            autores.setAcervoList(attachedAcervoList);
            em.persist(autores);
            for (Acervo acervoListAcervo : autores.getAcervoList()) {
                Autores oldIAutorOfAcervoListAcervo = acervoListAcervo.getIAutor();
                acervoListAcervo.setIAutor(autores);
                acervoListAcervo = em.merge(acervoListAcervo);
                if (oldIAutorOfAcervoListAcervo != null) {
                    oldIAutorOfAcervoListAcervo.getAcervoList().remove(acervoListAcervo);
                    oldIAutorOfAcervoListAcervo = em.merge(oldIAutorOfAcervoListAcervo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autores autores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autores persistentAutores = em.find(Autores.class, autores.getIAutor());
            List<Acervo> acervoListOld = persistentAutores.getAcervoList();
            List<Acervo> acervoListNew = autores.getAcervoList();
            List<Acervo> attachedAcervoListNew = new ArrayList<Acervo>();
            for (Acervo acervoListNewAcervoToAttach : acervoListNew) {
                acervoListNewAcervoToAttach = em.getReference(acervoListNewAcervoToAttach.getClass(), acervoListNewAcervoToAttach.getILivro());
                attachedAcervoListNew.add(acervoListNewAcervoToAttach);
            }
            acervoListNew = attachedAcervoListNew;
            autores.setAcervoList(acervoListNew);
            autores = em.merge(autores);
            for (Acervo acervoListOldAcervo : acervoListOld) {
                if (!acervoListNew.contains(acervoListOldAcervo)) {
                    acervoListOldAcervo.setIAutor(null);
                    acervoListOldAcervo = em.merge(acervoListOldAcervo);
                }
            }
            for (Acervo acervoListNewAcervo : acervoListNew) {
                if (!acervoListOld.contains(acervoListNewAcervo)) {
                    Autores oldIAutorOfAcervoListNewAcervo = acervoListNewAcervo.getIAutor();
                    acervoListNewAcervo.setIAutor(autores);
                    acervoListNewAcervo = em.merge(acervoListNewAcervo);
                    if (oldIAutorOfAcervoListNewAcervo != null && !oldIAutorOfAcervoListNewAcervo.equals(autores)) {
                        oldIAutorOfAcervoListNewAcervo.getAcervoList().remove(acervoListNewAcervo);
                        oldIAutorOfAcervoListNewAcervo = em.merge(oldIAutorOfAcervoListNewAcervo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = autores.getIAutor();
                if (findAutores(id) == null) {
                    throw new NonexistentEntityException("The autores with id " + id + " no longer exists.");
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
            Autores autores;
            try {
                autores = em.getReference(Autores.class, id);
                autores.getIAutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autores with id " + id + " no longer exists.", enfe);
            }
            List<Acervo> acervoList = autores.getAcervoList();
            for (Acervo acervoListAcervo : acervoList) {
                acervoListAcervo.setIAutor(null);
                acervoListAcervo = em.merge(acervoListAcervo);
            }
            em.remove(autores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autores> findAutoresEntities() {
        return findAutoresEntities(true, -1, -1);
    }

    public List<Autores> findAutoresEntities(int maxResults, int firstResult) {
        return findAutoresEntities(false, maxResults, firstResult);
    }

    private List<Autores> findAutoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autores.class));
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

    public Autores findAutores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autores.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autores> rt = cq.from(Autores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    /// Métodos adicionais
    public List<Autores> findAutoresEntitiesOrdenado() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autores.class));
            Query q = em.createQuery("SELECT a FROM Autores a ORDER BY a.nomeAutor ASC");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
