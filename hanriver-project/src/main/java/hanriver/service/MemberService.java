package hanriver.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanriver.dao.MemberDao;
import hanriver.domain.Member;

@Service
public class MemberService {
    
    @Autowired MemberDao memberDao;

    public void add(Member member) {
        memberDao.insert(member);
    }

    public int delete(String id) {
        return memberDao.delete(id);
        
    }

    public List<Member> list(int page, int size) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("startIndex", (page - 1) * size);
        params.put("pageSize", size);
        return memberDao.selectList(params);
    }

    public Member get(String id) {
        return memberDao.selectOne(id);
    }

    public int update(Member member) {
        return memberDao.update(member);
    }
    
    public int countAll(int size) {
        int count = memberDao.countAll();
        int totalPage = count / size;
        if (count % size > 0) {
            totalPage++;
        };
        return totalPage;
    }
    
  //로그인
    public int validMemberCheck(Member member) {
    	return memberDao.validMemberCheck(member);
    }

}
