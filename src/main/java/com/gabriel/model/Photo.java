package com.gabriel.model;

import javax.persistence.*;

@Entity
@Table(name = "t_photo", schema = "blog")
public class Photo {
    private int pid;
    private String pPath;
    private String pName;

    @Id
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "p_path")
    public String getpPath() {
        return pPath;
    }

    public void setpPath(String pPath) {
        this.pPath = pPath;
    }

    @Basic
    @Column(name = "p_name")
    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (pid != photo.pid) return false;
        if (pPath != null ? !pPath.equals(photo.pPath) : photo.pPath != null) return false;
        if (pName != null ? !pName.equals(photo.pName) : photo.pName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (pPath != null ? pPath.hashCode() : 0);
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        return result;
    }
}
