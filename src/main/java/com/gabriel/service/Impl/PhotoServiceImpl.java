package com.gabriel.service.Impl;

import com.gabriel.dao.PhotoDao;
import com.gabriel.model.PageBean;
import com.gabriel.model.Photo;
import com.gabriel.service.PhotoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Resource
    private PhotoDao photoDao;

    @Override
    public List<Photo> photoList(PageBean pageBean) throws Exception {
        return photoDao.photoList(pageBean);
    }

    @Override
    public int photoCount() throws Exception {
        return photoDao.photoCount();
    }

    @Override
    public int photoDelete(String delIds) throws Exception {
        return photoDao.photoDelete(delIds);
    }

    @Override
    public int photoSave(Photo photo) throws Exception {
        return photoDao.photoSave(photo);
    }

    @Override
    public Photo GetPhotoById(int id) throws Exception {
        return photoDao.GetPhotoById(id);
    }
}
