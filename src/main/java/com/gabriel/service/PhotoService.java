package com.gabriel.service;

import com.gabriel.model.PageBean;
import com.gabriel.model.Photo;

import java.util.List;

public interface PhotoService {
    public List<Photo> photoList(PageBean pageBean)throws Exception;
    public int photoCount()throws Exception;
    public int photoDelete(String delIds) throws Exception;
    public int photoSave(Photo photo) throws Exception ;
    public Photo GetPhotoById(int id) throws Exception;
}
