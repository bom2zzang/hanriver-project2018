package hanriver.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import hanriver.annotation.Autowired;
import hanriver.annotation.Repository;
import hanriver.domain.Notice;

@Repository
public class NoticeDao {
    
    SqlSessionFactory sqlSessionFactory;
    
    
    
    public NoticeDao() {}

    public NoticeDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Notice> selectList(Map<String, Object> params) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("notice.selectList", params);
        }
    }
    
    public Notice selectOne(String no) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("notice.selectOne", no);
        }
    }
    
    public int insert(Notice notice) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int count = sqlSession.insert("notice.insert", notice);
            sqlSession.commit();
            return count;
        }
    }
    
    public int update(Notice notice) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int count = sqlSession.update("notice.update", notice);
            sqlSession.commit();
            return count;
        }
    }
    public int delete(String no) throws Exception {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            int count = sqlSession.delete("notice.delete", no);
            sqlSession.commit();
            return count;
        }
    }
}
