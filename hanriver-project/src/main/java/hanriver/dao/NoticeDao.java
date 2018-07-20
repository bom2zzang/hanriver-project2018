package hanriver.dao;

import java.util.List;
import java.util.Map;

import hanriver.domain.Notice;

public interface NoticeDao {
    
    public List<Notice> selectList(Map<String, Object> params);
    
    public Notice selectOne(String no);
    
    public int insert(Notice notice);
    
    public int update(Notice notice);
    
    public int delete(String no);

    public int countAll();
}
