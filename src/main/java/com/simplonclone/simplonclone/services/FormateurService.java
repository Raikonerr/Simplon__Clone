package com.simplonclone.simplonclone.services;

import com.simplonclone.simplonclone.config.Config;
import com.simplonclone.simplonclone.entity.Formateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class FormateurService {

        public boolean add(Formateur formateur)
        {
            try{
                EntityManager em = Config.getConfig().getEntityManager();
                em.getTransaction().begin();
                em.persist(formateur);
                em.getTransaction().commit();
                return true;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return false;
        }

        public Formateur findById(int id)
        {
            try{

                EntityManager em = Config.getConfig().getEntityManager();
                return em.find(Formateur.class, id);
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

            return null;
        }

        public boolean update(Formateur formateur)
        {
            try{

                EntityManager em = Config.getConfig().getEntityManager();
                em.getTransaction().begin();
                em.merge(formateur);
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
                Formateur formateur = em.find(Formateur.class, id);
                em.remove(formateur);
                em.getTransaction().commit();
                return true;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return false;
        }

        public List<Formateur> getAll(int limit)
        {
            try{
                EntityManager em = Config.getConfig().getEntityManager();
                TypedQuery<Formateur> query = em.createQuery("SELECT f FROM Formateur f", Formateur.class);
                query.setMaxResults(limit);
                return query.getResultList();
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return null;
        }


}
