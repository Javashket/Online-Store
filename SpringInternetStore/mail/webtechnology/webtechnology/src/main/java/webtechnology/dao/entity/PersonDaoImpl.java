package webtechnology.dao.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import webtechnology.domain.entity.Person;

public class PersonDaoImpl extends BaseDao implements PersonDao {

	@Override
	public Person load(int id) {

		Session session = this.getSession();
		Person obj = null;

		try {
			session.beginTransaction();
			obj = (Person) session.get(Person.class.getName(), id);
			session.getTransaction().commit();
		} finally {
		}

		return obj;
	}

	@Override
	public void deleteById(int id) {
		Session session = this.getSession();
		Person obj = null;

		try {
			session.beginTransaction();
			obj = (Person) session.get(Person.class.getName(), id);
			session.delete(obj);
			session.getTransaction().commit();
		} finally {
		}

	}

	@Override
	public void create(Person p) {
		Session session = this.getSession();
		try {
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
		} finally {
		}
	}

	@Override
	public void update(Person p) {
		Session session = this.getSession();
		try {
			session.beginTransaction();
			session.update(p);
			session.getTransaction().commit();
		} finally {
		}
	}

	@Override
	public List<Person> getList(String pattern) {
		Session session = this.getSession();
		List<Person> result = null;

		try {
			session.beginTransaction();
			String sql = "select bean from " + Person.class.getName() + " bean ";
			if (pattern != null && pattern.length() > 0) {
				sql += "WHERE bean.firstName like :firstNameLike OR bean.lastName like :lastNameLike";
			}

			Query<Person> query = session.createQuery(sql, Person.class);

			if (pattern != null && pattern.length() > 0) {
				query.setParameter("firstNameLike", "%" + pattern + "%");
				query.setParameter("lastNameLike", "%" + pattern + "%");
			}

			result = query.list();
			session.getTransaction().commit();
		} finally {
		}

		return result;
	}

}
