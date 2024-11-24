package ru.itmo.web.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ru.itmo.web.entities.ResultEntity;

import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class ResultService implements Serializable {

    @PersistenceContext(unitName = "StudsPU")
    private EntityManager em;

    @Transactional
    public void save(ResultEntity result) {
        em.persist(result);
    }

    @Transactional
    public List<ResultEntity> findAll() {
        return em.createQuery("SELECT r FROM ResultEntity r", ResultEntity.class).getResultList();
    }

    @Transactional
    public ResultEntity findById(Long id) {
        return em.find(ResultEntity.class, id);
    }

    @Transactional
    public void deleteById(Long id) {
        ResultEntity result = findById(id);
        if (result != null) {
            em.remove(result);
        }
    }

    @Transactional
    public void deleteAll() {
        em.createQuery("DELETE FROM ResultEntity r").executeUpdate();
    }
}
