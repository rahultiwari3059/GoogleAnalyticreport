package com.bridgelabz.dao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import com.bridgelabz.model.AllElementModels;
import com.bridgelabz.model.AppOpenModel;
import com.bridgelabz.model.AppReOpenModel;



@Repository("hibernateDao")
public class HibernateDao implements UserDao 
{
	@Resource(name = "sessionFactory")
	SessionFactory sessionFactory;
	Session session;

	public void Save(ArrayList<AllElementModels> responseElementModelArrayList) {
		try {

			for (int i = 0; i < responseElementModelArrayList.size(); i++) {

				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				session.save(responseElementModelArrayList.get(i));
				tx.commit();
				session.close();
			}

		} catch (Exception e) {
			System.out.println("hello");
			e.printStackTrace();
		}
	}

	public void appOpenModelSave(ArrayList<AppOpenModel> appOpenModelArrayList) {
		try {

			for (int i = 0; i < appOpenModelArrayList.size(); i++) {
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();

				session.save(appOpenModelArrayList.get(i));
				if (i % 20 == 0) { // Same as the JDBC batch size
									// flush a batch of inserts and release
									// memory:
					session.flush();
					session.clear();
				}
				tx.commit();
				session.close();
			}

		} catch (Exception e) {
			System.out.println("hello");
			e.printStackTrace();
		}

	}

	public void appReOpenModelSave(ArrayList<AppReOpenModel> appReOpenModelArrayList) 
	{
		try 
		{
			for (int i = 0; i < appReOpenModelArrayList.size(); i++) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			session.save(appReOpenModelArrayList.get(i));
			if (i % 20 == 0) 
			{ // Same as the JDBC batch size
								// flush a batch of inserts and release
								// memory:
				session.flush();
				session.clear();
			}
			tx.commit();
			session.close();
		}

		} catch (Exception e) {
			System.out.println("hello");
			e.printStackTrace();
		}

	}
	public Collection<AppReOpenModel> getAppReOpen()
	{
		session = sessionFactory.openSession();
		Query query=(Query) session.createQuery("from AppReOpenModel");
		List list= query.list();
		return list;
	}
	public Collection<AppOpenModel> getAppOpen()
	{
		session = sessionFactory.openSession();
		Query query=(Query) session.createQuery("from AppOpenModel");
		List list= query.list();
		return list;
	}
	

} 
