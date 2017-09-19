/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import controle.exceptions.NonexistentEntityException;
import entidades.Arquivos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 *
 * @author Hilton
 */
public class ArquivosJpaController implements Serializable {

    public ArquivosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Arquivos arquivos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(arquivos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Arquivos arquivos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            arquivos = em.merge(arquivos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = arquivos.getIArquivos();
                if (findArquivos(id) == null) {
                    throw new NonexistentEntityException("The arquivos with id " + id + " no longer exists.");
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
            Arquivos arquivos;
            try {
                arquivos = em.getReference(Arquivos.class, id);
                arquivos.getIArquivos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arquivos with id " + id + " no longer exists.", enfe);
            }
            em.remove(arquivos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Arquivos> findArquivosEntities() {
        return findArquivosEntities(true, -1, -1);
    }

    public List<Arquivos> findArquivosEntities(int maxResults, int firstResult) {
        return findArquivosEntities(false, maxResults, firstResult);
    }

    private List<Arquivos> findArquivosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arquivos.class));
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

    public Arquivos findArquivos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Arquivos.class, id);
        } finally {
            em.close();
        }
    }

    public int getArquivosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Arquivos> rt = cq.from(Arquivos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    // metodos adicionais
    public List<Arquivos> findArquivosFormatos() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arquivos.class));
            CriteriaQuery<String> q =  em.getCriteriaBuilder().createQuery(String.class);
            Root<Order> order = q.from(Order.class);
            q.select(order.get("formato").<String>get("state"));
            Query q1 = em.createQuery(cq);
            return q1.getResultList();
        } finally {
            em.close();
        }
    }

}
