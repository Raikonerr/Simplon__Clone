package com.simplonclone.simplonclone.services;

import com.simplonclone.simplonclone.config.Config;
import com.simplonclone.simplonclone.entity.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

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
}

