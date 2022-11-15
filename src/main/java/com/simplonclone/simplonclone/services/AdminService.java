package com.simplonclone.simplonclone.services;

import com.simplonclone.simplonclone.config.Config;
import com.simplonclone.simplonclone.entity.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.*;





public class AdminService {
    public boolean add(Admin admin)
    {
        try{
            EntityManager em = Config.getConfig().getEntityManager();
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
            return true;
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Admin login(String email, String password)
    {
        try{
            EntityManager em = Config.getConfig().getEntityManager();
            em.getTransaction().begin();
            TypedQuery<Admin> query = em.createQuery("SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password", Admin.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            if (query.getResultList().size() > 0)
            {
                System.out.println("login success");
                return query.getResultList().get(0);
            }else{
                System.out.println("Email ou mot de passe incorrect");
            }
            em.getTransaction().commit();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }
}

