package com.n1njac.weread.model.entity;
/*    
 *    Created by N1njaC on 2018/5/2.
 *    email:aiai173cc@gmail.com 
 */

import java.util.List;

public class SplashEntity {

    /**
     * status : ok
     * time : 1514794312
     * images : ["https://img.owspace.com/Public/uploads/Picture/2018-01-01/5a49ed1a1fccc.jpg","https://img.owspace.com/Public/uploads/Picture/2018-01-01/5a49ed18002d4.jpg","https://img.owspace.com/Public/uploads/Picture/2018-01-01/5a49ed162222d.jpg","https://img.owspace.com/Public/uploads/Picture/2018-01-01/5a49ed11cf403.jpg","https://img.owspace.com/Public/uploads/Picture/2018-01-01/5a49ed0da93d9.jpg","https://img.owspace.com/Public/uploads/Picture/2018-01-01/5a49ed09e365d.jpg"]
     * count : 6
     */

    private String status;
    private int time;
    private int count;
    private List<String> images;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
