package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.Query;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.model.Ngram;

public class DB {
	static Logger log = LoggerFactory.getLogger(DB.class);
	static String hql = "FROM main.java.model.Ngram n WHERE n.ngram = ?1";

	public static void save(String txt) {

		List<String> sList = breakDown(txt, 2);
		

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		for (String str : sList) {
			System.err.println(str);
			
			Query q = session.createQuery(hql).setParameter(1, str);

			@SuppressWarnings("unchecked")
			List<Ngram> rs = q.getResultList();
			
			if (rs != null && !rs.isEmpty()) {
				Ngram ngram = rs.get(0);
				ngram.setCount(ngram.getCount() + 1);
				session.saveOrUpdate(ngram);
				log.info("updated " + ngram);
			}

			else {
				System.err.println("new item");
				
				Ngram ngram = new Ngram();
				ngram.setText(str);
				ngram.setCount(1);
				
				System.err.println(ngram);
				
				session.persist(ngram);
				log.info("inserted " + ngram);
			}
		}

		session.getTransaction().commit();
		
		session.close();

	}

	private static List<String> breakDown(String txt, int n) {

		List<String> sList = new ArrayList<>();
		String[] ss = txt.split("\\s+");
		int len = ss.length;
		for (int st = 0; st < len - n; st++) {
			StringJoiner sj = new StringJoiner(" ");
			for (int j = st; j < st + n; j++) {
				sj.add(ss[j]);
			}
			sList.add(sj.toString());
		}

		return sList;
	}

}
