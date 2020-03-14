package webtechnology.domain.services;

import java.util.List;

import webtechnology.domain.entity.Person;

public interface PersonService {

	List<Person> getList(String pattern);

	String getJsonListAsString(String pattern);

	void deleteById(int id);

	Person loadById(int id);

	Person loadByIdOrCreate(Integer id);

	void update(Person p);

	void create(Person p);

	void createOrUpdate(Person p);

}