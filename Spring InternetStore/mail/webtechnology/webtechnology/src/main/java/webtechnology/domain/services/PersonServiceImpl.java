package webtechnology.domain.services;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import webtechnology.dao.entity.PersonDao;
import webtechnology.dao.entity.PersonDaoImpl;
import webtechnology.domain.entity.Person;


public class PersonServiceImpl implements PersonService {

	private final PersonDao personDao = new PersonDaoImpl();

	@Override
	public List<Person> getList(String pattern) {
		return personDao.getList(pattern);
	}

	@Override
	public String getJsonListAsString(String pattern) {
		List<Person> peoples = personDao.getList(pattern);

		String result = "[]";
		
		try {
			JSONArray jsonArr = new JSONArray();
			for (Person people : peoples) {
				JSONObject obj = new JSONObject();
				obj.put("id", people.getId());
				obj.put("firstName", people.getFirstName());
				obj.put("lastName", people.getLastName());
				jsonArr.put(obj);
			}

			result = jsonArr.toString();
		} catch (JSONException e) {
			System.err.println("Error during json creating");
		}

		return result;
	}

	@Override
	public void deleteById(int id) {
		personDao.deleteById(id);
	}

	@Override
	public Person loadById(int id) {
		return personDao.load(id);
	}

	@Override
	public Person loadByIdOrCreate(Integer id) {
		Person people = new Person();

		if (id != null && id > 0) {
			people = personDao.load(id);
		}

		return people;
	}

	@Override
	public void update(Person p) {
		personDao.update(p);
	}

	@Override
	public void create(Person p) {
		personDao.create(p);
	}

	@Override
	public void createOrUpdate(Person p) {
		if(p.getId() > 0) {
			personDao.update(p);
		} else {
			personDao.create(p);
		}
	}
}
