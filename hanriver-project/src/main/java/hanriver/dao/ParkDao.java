package hanriver.dao;

import java.util.List;
import java.util.Map;

import hanriver.domain.Park;

public interface ParkDao {

	List<Park> selectList(Map<String, Object>params);

	void insert(Park park);

	Park selectOne(String no);

	int update(Park park);

	int delete(String name);

	int countAll();


}
