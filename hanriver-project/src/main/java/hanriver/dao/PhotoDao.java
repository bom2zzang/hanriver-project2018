package hanriver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hanriver.domain.Photo;

public interface PhotoDao {
    
    int insert(Photo photo);

    int delete(@Param("no") int no, @Param("mno")int mno);

    List<Photo> selectAll();
}
