package hanriver.dao;

import java.util.List;
import java.util.Map;

import hanriver.domain.Member;

public interface MemberDao {
    List<Member> findAll(Map<String,Object> params);
    Member findByNo(int no);
    Member findByEmailAndPassword(Map<String,Object> params);
    Member findByEmail(String email);
    int countAll();
    int insert(Member member);
    int update(Member member);
    int delete(int no);
}
