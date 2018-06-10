package com.gabriel.dao.Impl;

import com.gabriel.dao.PhotoDao;
import com.gabriel.model.PageBean;
import com.gabriel.model.Photo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Repository
public class PhotoDaoImpl implements PhotoDao {

    private SessionFactory sessionFactory;
    private static Logger logger=LogManager.getLogger(PhotoDaoImpl.class.getName());
    @Override
    public List<Photo> photoList(PageBean pageBean) throws Exception {
        List<Photo> photoList=null;
        Session session=this.getSession();
        StringBuffer stringBuffer=new StringBuffer("from Photo order by pid desc");
        Query query = session.createQuery(stringBuffer.toString().replaceFirst("and", "where"));
        if (pageBean != null) {
            query.setFirstResult(pageBean.getStart());
            query.setMaxResults(pageBean.getRows());
        }
        photoList=(List<Photo>)query.list();
        logger.info("查询到"+photoList.size()+"张照片！");
        return photoList;
    }

    @Override
    public int photoCount() throws Exception {
        StringBuffer stringBuffer=new StringBuffer("select count(*) as total from t_photo");
        Session session=this.getSession();
        Query query = session.createSQLQuery(stringBuffer.toString().replaceFirst("and", "where"));
        return ((BigInteger) query.uniqueResult()).intValue();

    }

    @Override
    public int photoDelete(String delIds) throws Exception {
        Session session = this.getSession();
        Query query = session.createSQLQuery("delete from t_photo where pid in(" + delIds + ")");
        int count = query.executeUpdate();
        logger.info("删除照片，Id为："+delIds);
        return count;
    }

    @Override
    public int photoSave(Photo photo) throws Exception {
        Session session=this.getSession();
        session.merge(photo);
        logger.info("保存照片："+photo.getpName());
        return 1;
    }

    @Override
    public Photo GetPhotoById(int id) throws Exception {
        Session session=this.getSession();
        Photo photo=(Photo)session.get(Photo.class,id);
        return photo;
    }

    @Resource
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
