package hanriver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hanriver.dao.PhotoDao;
import hanriver.domain.Photo;

@Service
public class PhotoServiceImpl implements PhotoService {
    
    @Autowired PhotoDao photoDao;
    
    @Override
    public void add(Photo photo) {
        photoDao.insert(photo);
    }

    @Override
    public int delete(int no, int mno) {
        return photoDao.delete(no, mno);
    }

    @Override
    public List<Photo> list() {
        return photoDao.selectAll();
    }

    @Override
    public int update(Photo photo) {
        return photoDao.update(photo);
    }

    @Override
    public Photo get(int no) {
        return photoDao.selectOne(no);
    }
    
}
