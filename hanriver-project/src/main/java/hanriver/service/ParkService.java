package hanriver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanriver.dao.ParkDao;
import hanriver.domain.Park;

@Service
public class ParkService {
	
	@Autowired ParkDao parkDao;

	public List<Park> list() {

		return parkDao.selectList();
	}

	public void add(Park park) {
		parkDao.insert(park);
		
	}

	public Park get(String name) {
		return parkDao.selectOne(name);
	}

	public int update(Park park) {
		return parkDao.update(park);
	}

	public int delete(String name) {

		return parkDao.delete(name);
	}

}
