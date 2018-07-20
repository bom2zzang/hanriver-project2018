package hanriver.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanriver.dao.NoticeDao;
import hanriver.domain.Notice;


@Service
public class NoticeService {
    
    @Autowired NoticeDao noticeDao;
    
    public void add(Notice notice) {
        noticeDao.insert(notice);
    }

    public Notice get(String no) {
        return noticeDao.selectOne(no);
    }

    public int update(Notice notice) {
        return noticeDao.update(notice);
    }

    public List<Notice> list(int page, int size) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("startIndex", (page - 1) * size);
        params.put("pageSize", size);
        return noticeDao.selectList(params);
    }

    public int delete(String no) {
        return noticeDao.delete(no);
    }

    public int countAll(int size) {
        int count = noticeDao.countAll();
        int totalPage = count / size;
        if (count % size > 0) {
            totalPage++;
        };
        return totalPage;
    }

}
