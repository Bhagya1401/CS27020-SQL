/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Module;
import entities.Student;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nwh
 */
public class StudentJpaController implements Serializable {

    public StudentJpaController() {}
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("StuModPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Student student) throws PreexistingEntityException, Exception {
        if (student.getModuleCollection() == null) {
            student.setModuleCollection(new ArrayList<Module>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Module> attachedModuleCollection = new ArrayList<Module>();
            for (Module moduleCollectionModuleToAttach : student.getModuleCollection()) {
                moduleCollectionModuleToAttach = em.getReference(moduleCollectionModuleToAttach.getClass(), moduleCollectionModuleToAttach.getCode());
                attachedModuleCollection.add(moduleCollectionModuleToAttach);
            }
            student.setModuleCollection(attachedModuleCollection);
            em.persist(student);
            for (Module moduleCollectionModule : student.getModuleCollection()) {
                moduleCollectionModule.getStudentCollection().add(student);
                moduleCollectionModule = em.merge(moduleCollectionModule);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStudent(student.getId()) != null) {
                throw new PreexistingEntityException("Student " + student + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Student student) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Student persistentStudent = em.find(Student.class, student.getId());
            Collection<Module> moduleCollectionOld = persistentStudent.getModuleCollection();
            Collection<Module> moduleCollectionNew = student.getModuleCollection();
            Collection<Module> attachedModuleCollectionNew = new ArrayList<Module>();
            for (Module moduleCollectionNewModuleToAttach : moduleCollectionNew) {
                moduleCollectionNewModuleToAttach = em.getReference(moduleCollectionNewModuleToAttach.getClass(), moduleCollectionNewModuleToAttach.getCode());
                attachedModuleCollectionNew.add(moduleCollectionNewModuleToAttach);
            }
            moduleCollectionNew = attachedModuleCollectionNew;
            student.setModuleCollection(moduleCollectionNew);
            student = em.merge(student);
            for (Module moduleCollectionOldModule : moduleCollectionOld) {
                if (!moduleCollectionNew.contains(moduleCollectionOldModule)) {
                    moduleCollectionOldModule.getStudentCollection().remove(student);
                    moduleCollectionOldModule = em.merge(moduleCollectionOldModule);
                }
            }
            for (Module moduleCollectionNewModule : moduleCollectionNew) {
                if (!moduleCollectionOld.contains(moduleCollectionNewModule)) {
                    moduleCollectionNewModule.getStudentCollection().add(student);
                    moduleCollectionNewModule = em.merge(moduleCollectionNewModule);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = student.getId();
                if (findStudent(id) == null) {
                    throw new NonexistentEntityException("The student with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Student student;
            try {
                student = em.getReference(Student.class, id);
                student.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The student with id " + id + " no longer exists.", enfe);
            }
            Collection<Module> moduleCollection = student.getModuleCollection();
            for (Module moduleCollectionModule : moduleCollection) {
                moduleCollectionModule.getStudentCollection().remove(student);
                moduleCollectionModule = em.merge(moduleCollectionModule);
            }
            em.remove(student);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Student> findStudentEntities() {
        return findStudentEntities(true, -1, -1);
    }

    public List<Student> findStudentEntities(int maxResults, int firstResult) {
        return findStudentEntities(false, maxResults, firstResult);
    }

    private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Student.class));
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

    public Student findStudent(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Student> rt = cq.from(Student.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
