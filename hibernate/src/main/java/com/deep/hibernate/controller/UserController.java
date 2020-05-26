package com.deep.hibernate.controller;


import com.deep.hibernate.dao.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class UserController {
	
	public static void main(String[] args) {

        //creating configuration object  
        Configuration cfg=new Configuration();  
        cfg.configure("hibernate.cfg.xml");//populates the data of the configuration file 

        SessionFactory sf = cfg.buildSessionFactory(); 
        
        Session ss = sf.openSession(); 
        
        Transaction t = ss.beginTransaction(); 
        
        User u = new User();
        //u.setUserid(1);
        u.setPassword("111111");
        u.setUsername("aaaaaa");
                
        //ss.persist(u);
        ss.save(u);

        int id = u.getUserid();
        System.out.println(id);

        t.commit();
        
        ss.close(); 
        
        
        
	}

}
