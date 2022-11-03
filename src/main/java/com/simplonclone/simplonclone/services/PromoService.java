package com.simplonclone.simplonclone.services;

import com.simplonclone.simplonclone.config.Config;
import com.simplonclone.simplonclone.entity.Promos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PromoService {

        public boolean add(Promos promos)
        {
            try{
                EntityManager em = Config.getConfig().getEntityManager();
                em.getTransaction().begin();
                em.persist(promos);
                em.getTransaction().commit();
                return true;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return false;
        }

        public Promos findById(int id)
        {
            try{

                EntityManager em = Config.getConfig().getEntityManager();
                return em.find(Promos.class, id);
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

            return null;
        }

        public boolean update(Promos promos)
        {
            try{

                EntityManager em = Config.getConfig().getEntityManager();
                em.getTransaction().begin();
                em.merge(promos);
                em.getTransaction().commit();
                return true;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return false;
        }

        public boolean deleteByID(int id)
        {
            try{

                EntityManager em = Config.getConfig().getEntityManager();
                em.getTransaction().begin();
                Promos promos = em.find(Promos.class, id);
                em.remove(promos);
                em.getTransaction().commit();
                return true;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return false;
        }

        public List<Promos> getAll()
        {
            try{
                EntityManager em = Config.getConfig().getEntityManager();
                TypedQuery<Promos> query = em.createQuery("SELECT p FROM Promos p", Promos.class);
                return query.getResultList();
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return null;
        }
}
