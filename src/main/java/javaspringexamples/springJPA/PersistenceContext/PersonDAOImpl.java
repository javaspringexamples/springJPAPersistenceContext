package javaspringexamples.springJPA.PersistenceContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * 
 * @author mounir.sahrani@gmail.com
 *
 */

@Transactional
public class PersonDAOImpl implements PersonDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(Person p) {
		//EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();
		entityManager.persist(p);
		//transaction.commit();
		entityManager.close();
	}
}
