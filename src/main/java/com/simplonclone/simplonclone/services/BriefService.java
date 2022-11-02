package com.simplonclone.simplonclone.services;

import com.simplonclone.simplonclone.config.Config;
import com.simplonclone.simplonclone.entity.Brief;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;


public class BriefService {

        public boolean add(Brief brief)
        {
            try{
                EntityManager em = Config.getConfig().getEntityManager();
                em.getTransaction().begin();
                em.persist(brief);
                em.getTransaction().commit();
                return true;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return false;
        }

        public Brief findById(int id)
        {
            try{

                EntityManager em = Config.getConfig().getEntityManager();
                return em.find(Brief.class, id);
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

            return null;
        }

        public boolean update(Brief brief)
        {
            try{

                EntityManager em = Config.getConfig().getEntityManager();
                em.getTransaction().begin();
                em.merge(brief);
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
                Brief brief = em.find(Brief.class, id);
                em.remove(brief);
                em.getTransaction().commit();
                return true;
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return false;
        }

        public List<Brief> findAll()
        {
            try{
                EntityManager em = Config.getConfig().getEntityManager();
                TypedQuery<Brief> query = em.createQuery("SELECT b FROM Brief b", Brief.class);
                return query.getResultList();
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            return null;
        }

}
