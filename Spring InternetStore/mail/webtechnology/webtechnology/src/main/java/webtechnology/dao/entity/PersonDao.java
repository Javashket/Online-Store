package webtechnology.dao.entity;

import java.util.List;

import webtechnology.domain.entity.Person;

public interface PersonDao {

	Person load(int id);

	void deleteById(int id);

	void create(Person p);

	void update(Person p);

	List<Person> getList(String pattern);

}