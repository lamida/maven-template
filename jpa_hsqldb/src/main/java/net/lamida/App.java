package net.lamida;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("start...");
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	for(int i = 0; i < 10; i++) {
    		Person p = new Person();
    		p.setName("person " + i);
    		Phone ph = new Phone();
    		ph.setNumber("1234");
    		p.addPhone(ph);
    		em.persist(p);
    	}
    	tx.commit();
    	
    	tx.begin();
    	@SuppressWarnings("unchecked")
		List<Person> list = em.createQuery("select e from Person e").getResultList();
    	for(Person pp : list) {
    		System.out.println(pp.getName());
    		System.out.println("phone: ");
    		for(Phone phh : pp.getPhones()) {
    			System.out.println(phh.getNumber());
    		}
    	}
    	System.out.println("finnish");
    	System.out.println("total person = " + list.size());
    	tx.commit();
    	em.close();
    	emf.close();
    }
}
