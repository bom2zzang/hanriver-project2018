package hanriver.dao;

import java.util.List;

import hanriver.domain.Park;

public interface ParkDao {

	List<Park> selectList();

	void insert(Park park);

	Park selectOne(String name);

	int update(Park park);

	int delete(String name);

	int countAll();


}
