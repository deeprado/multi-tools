package com.deep.hibernate.controller;

import com.deep.hibernate.dao.NUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class PersonController {

	public static void main(String[] args) {
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build(); 
		SessionFactory sf  = new MetadataSources(registry).buildMetadata().buildSessionFactory(); 
		
		Session s = sf.openSession(); 
		
		Transaction t = s.beginTransaction(); 
		
		//Person p = new Person(); 
		NUser p = new NUser();
		
		p.setUsername("aaaaaa");
		p.setPassword("111111");


		s.save(p);
		
		t.commit(); 
		s.close();
	}
}
