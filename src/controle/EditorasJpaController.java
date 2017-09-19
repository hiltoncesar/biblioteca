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
import entidades.Editoras;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hilton
 */
public class EditorasJpaController implements Serializable {

    public EditorasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Editoras editoras) {
        if (editoras.getAcervoList() == null) {
            editoras.setAcervoList(new ArrayList<Acervo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Acervo> attachedAcervoList = new ArrayList<Acervo>();
            for (Acervo acervoListAcervoToAttach : editoras.getAcervoList()) {
                acervoListAcervoToAttach = em.getReference(acervoListAcervoToAttach.getClass(), acervoListAcervoToAttach.getILivro());
                attachedAcervoList.add(acervoListAcervoToAttach);
            }
            editoras.setAcervoList(attachedAcervoList);
            em.persist(editoras);
            for (Acervo acervoListAcervo : editoras.getAcervoList()) {
                Editoras oldIEditoraOfAcervoListAcervo = acervoListAcervo.getIEditora();
                acervoListAcervo.setIEditora(editoras);
                acervoListAcervo = em.merge(acervoListAcervo);
                if (oldIEditoraOfAcervoListAcervo != null) {
                    oldIEditoraOfAcervoListAcervo.getAcervoList().remove(acervoListAcervo);
                    oldIEditoraOfAcervoListAcervo = em.merge(oldIEditoraOfAcervoListAcervo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Editoras editoras) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Editoras persistentEditoras = em.find(Editoras.class, editoras.getIEditora());
            List<Acervo> acervoListOld = persistentEditoras.getAcervoList();
            List<Acervo> acervoListNew = editoras.getAcervoList();
            List<Acervo> attachedAcervoListNew = new ArrayList<Acervo>();
            for (Acervo acervoListNewAcervoToAttach : acervoListNew) {
                acervoListNewAcervoToAttach = em.getReference(acervoListNewAcervoToAttach.getClass(), acervoListNewAcervoToAttach.getILivro());
                attachedAcervoListNew.add(acervoListNewAcervoToAttach);
            }
            acervoListNew = attachedAcervoListNew;
            editoras.setAcervoList(acervoListNew);
            editoras = em.merge(editoras);
            for (Acervo acervoListOldAcervo : acervoListOld) {
                if (!acervoListNew.contains(acervoListOldAcervo)) {
                    acervoListOldAcervo.setIEditora(null);
                    acervoListOldAcervo = em.merge(acervoListOldAcervo);
                }
            }
            for (Acervo acervoListNewAcervo : acervoListNew) {
                if (!acervoListOld.contains(acervoListNewAcervo)) {
                    Editoras oldIEditoraOfAcervoListNewAcervo = acervoListNewAcervo.getIEditora();
                    acervoListNewAcervo.setIEditora(editoras);
                    acervoListNewAcervo = em.merge(acervoListNewAcervo);
                    if (oldIEditoraOfAcervoListNewAcervo != null && !oldIEditoraOfAcervoListNewAcervo.equals(editoras)) {
                        oldIEditoraOfAcervoListNewAcervo.getAcervoList().remove(acervoListNewAcervo);
                        oldIEditoraOfAcervoListNewAcervo = em.merge(oldIEditoraOfAcervoListNewAcervo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = editoras.getIEditora();
                if (findEditoras(id) == null) {
                    throw new NonexistentEntityException("The editoras with id " + id + " no longer exists.");
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
            Editoras editoras;
            try {
                editoras = em.getReference(Editoras.class, id);
                editoras.getIEditora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The editoras with id " + id + " no longer exists.", enfe);
            }
            List<Acervo> acervoList = editoras.getAcervoList();
            for (Acervo acervoListAcervo : acervoList) {
                acervoListAcervo.setIEditora(null);
                acervoListAcervo = em.merge(acervoListAcervo);
            }
            em.remove(editoras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Editoras> findEditorasEntities() {
        return findEditorasEntities(true, -1, -1);
    }

    public List<Editoras> findEditorasEntities(int maxResults, int firstResult) {
        return findEditorasEntities(false, maxResults, firstResult);
    }

    private List<Editoras> findEditorasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Editoras.class));
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

    public Editoras findEditoras(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Editoras.class, id);
        } finally {
            em.close();
        }
    }

    public int getEditorasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Editoras> rt = cq.from(Editoras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    // metodos adicionais
    public List<Editoras> findEditorasEntitiesOrdenado() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Editoras.class));
            Query q = em.createQuery("SELECT e FROM Editoras e ORDER BY e.nomeEditora ASC");
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
