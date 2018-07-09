package hanriver.service;

import java.util.List;
import java.util.Map;

import hanriver.domain.Member;

public interface MemberService {
    List<Member> list(int pageNo, int pageSize, Map<String,Object> options);
    Member get(int no);
    Member get(String email, String password);
    Member get(String email);
    int getTotalCount();
    int add(Member member);
    int update(Member member);
    int delete(int no);
}
