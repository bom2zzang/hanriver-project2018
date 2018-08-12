package hanriver.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanriver.dao.ParkDao;
import hanriver.domain.Park;

@Service
public class ParkService {
	
	@Autowired ParkDao parkDao;

	public List<Park> list(int page, int size) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("startIndex", (page - 1) * size);
		params.put("pageSize", size);
		return parkDao.selectList(params);
	}

	public void add(Park park) {
		parkDao.insert(park);
		
	}

	public Park get(String no) {
		return parkDao.selectOne(no);
	}

	public int update(Park park) {
		return parkDao.update(park);
	}

	public int delete(String name) {

		return parkDao.delete(name);
	}

	public Object countAll(int size) {
		int count = parkDao.countAll();
		int totalPage = count / size;
        if (count % size > 0) {
            totalPage++;
        };
        return totalPage;
    }

}
