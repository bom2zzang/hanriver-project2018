package hanriver.service;

import java.util.List;

import hanriver.domain.Photo;

public interface PhotoService {
    
    public void add(Photo photo);

    public int delete(int no, int mno);

    public List<Photo> list();
}
