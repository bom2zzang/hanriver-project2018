package hanriver.dao;

import java.util.List;
import java.util.Map;

import hanriver.domain.Member;

public interface MemberDao {
    
    List<Member> selectList(Map<String, Object> params);
    
    Member selectOne(String id);
    
    int insert(Member member);
    
    int update(Member member);
    
    int delete(String id);

    int countAll();
}
