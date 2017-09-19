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
import entidades.Categorias;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hilton
 */
public class CategoriasJpaController implements Serializable {

    public CategoriasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categorias categorias) {
        if (categorias.getAcervoList() == null) {
            categorias.setAcervoList(new ArrayList<Acervo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Acervo> attachedAcervoList = new ArrayList<Acervo>();
            for (Acervo acervoListAcervoToAttach : categorias.getAcervoList()) {
                acervoListAcervoToAttach = em.getReference(acervoListAcervoToAttach.getClass(), acervoListAcervoToAttach.getILivro());
                attachedAcervoList.add(acervoListAcervoToAttach);
            }
            categorias.setAcervoList(attachedAcervoList);
            em.persist(categorias);
            for (Acervo acervoListAcervo : categorias.getAcervoList()) {
                Categorias oldICategoriaOfAcervoListAcervo = acervoListAcervo.getICategoria();
                acervoListAcervo.setICategoria(categorias);
                acervoListAcervo = em.merge(acervoListAcervo);
                if (oldICategoriaOfAcervoListAcervo != null) {
                    oldICategoriaOfAcervoListAcervo.getAcervoList().remove(acervoListAcervo);
                    oldICategoriaOfAcervoListAcervo = em.merge(oldICategoriaOfAcervoListAcervo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categorias categorias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categorias persistentCategorias = em.find(Categorias.class, categorias.getICatagoria());
            List<Acervo> acervoListOld = persistentCategorias.getAcervoList();
            List<Acervo> acervoListNew = categorias.getAcervoList();
            List<Acervo> attachedAcervoListNew = new ArrayList<Acervo>();
            for (Acervo acervoListNewAcervoToAttach : acervoListNew) {
                acervoListNewAcervoToAttach = em.getReference(acervoListNewAcervoToAttach.getClass(), acervoListNewAcervoToAttach.getILivro());
                attachedAcervoListNew.add(acervoListNewAcervoToAttach);
            }
            acervoListNew = attachedAcervoListNew;
            categorias.setAcervoList(acervoListNew);
            categorias = em.merge(categorias);
            for (Acervo acervoListOldAcervo : acervoListOld) {
                if (!acervoListNew.contains(acervoListOldAcervo)) {
                    acervoListOldAcervo.setICategoria(null);
                    acervoListOldAcervo = em.merge(acervoListOldAcervo);
                }
            }
            for (Acervo acervoListNewAcervo : acervoListNew) {
                if (!acervoListOld.contains(acervoListNewAcervo)) {
                    Categorias oldICategoriaOfAcervoListNewAcervo = acervoListNewAcervo.getICategoria();
                    acervoListNewAcervo.setICategoria(categorias);
                    acervoListNewAcervo = em.merge(acervoListNewAcervo);
                    if (oldICategoriaOfAcervoListNewAcervo != null && !oldICategoriaOfAcervoListNewAcervo.equals(categorias)) {
                        oldICategoriaOfAcervoListNewAcervo.getAcervoList().remove(acervoListNewAcervo);
                        oldICategoriaOfAcervoListNewAcervo = em.merge(oldICategoriaOfAcervoListNewAcervo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categorias.getICatagoria();
                if (findCategorias(id) == null) {
                    throw new NonexistentEntityException("The categorias with id " + id + " no longer exists.");
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
            Categorias categorias;
            try {
                categorias = em.getReference(Categorias.class, id);
                categorias.getICatagoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categorias with id " + id + " no longer exists.", enfe);
            }
            List<Acervo> acervoList = categorias.getAcervoList();
            for (Acervo acervoListAcervo : acervoList) {
                acervoListAcervo.setICategoria(null);
                acervoListAcervo = em.merge(acervoListAcervo);
            }
            em.remove(categorias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categorias> findCategoriasEntities() {
        return findCategoriasEntities(true, -1, -1);
    }

    public List<Categorias> findCategoriasEntities(int maxResults, int firstResult) {
        return findCategoriasEntities(false, maxResults, firstResult);
    }

    private List<Categorias> findCategoriasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categorias.class));
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

    public Categorias findCategorias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categorias.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categorias> rt = cq.from(Categorias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //Metodos adicionais
    
    public List<Categorias> findCategoriasEntitiesOrdenado() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categorias.class));
            Query q = em.createQuery("SELECT c FROM Categorias c ORDER BY c.categoriaNome ASC");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
