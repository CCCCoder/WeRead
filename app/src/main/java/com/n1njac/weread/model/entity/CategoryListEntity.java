package com.n1njac.weread.model.entity;
/*    
 *    Created by N1njaC on 2018/5/3.
 *    email:aiai173cc@gmail.com 
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CategoryListEntity implements Parcelable {


    /**
     * status : ok
     * datas : []
     * msg :
     * code : 0
     */

    private String status;
    private String msg;
    private int code;
    private List<DatasBean> datas;

    protected CategoryListEntity(Parcel in) {
        status = in.readString();
        msg = in.readString();
        code = in.readInt();
    }

    public static final Creator<CategoryListEntity> CREATOR = new Creator<CategoryListEntity>() {
        @Override
        public CategoryListEntity createFromParcel(Parcel in) {
            return new CategoryListEntity(in);
        }

        @Override
        public CategoryListEntity[] newArray(int size) {
            return new CategoryListEntity[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeString(msg);
        dest.writeInt(code);
    }

    public static class DatasBean implements Parcelable {
        /**
         * id : 294943
         * uid : 858097
         * name :
         * title : 手头所忙之事
         究竟是什么？
         * excerpt : 若世界全然是诱人的，那便很简单。若世界仅仅充满挑战，亦可无甚问题。但我每早总在改善世界与享受世界两种欲望的拉扯中醒来，这让规划这一天变得很困难。
         * lead :
         * model : 1
         * position : 0
         * thumbnail : https://img.owspace.com/Public/uploads/Picture/2018-04-26/5ae19ddbedc9d.jpg
         * create_time : 1525305600
         * update_time : 2018/05/03
         * tags : [{"name":""}]
         * status : 1
         * video :
         * fm :
         * link_url :
         * publish_time : 0
         * view : 5.9w
         * share : http://static.owspace.com/wap/294943.html
         * comment : 12
         * good : 14
         * bookmark : 0
         * show_uid : 858097
         * fm_play :
         * lunar_type : 1
         * hot_comments : [{"id":"39457","pid":"0","uid":"218409","content":"我们不要陷入自我的陷阱。唯有务实才是真正的办法","post_id":"292322","update_time":"2 天前","good":"6","model":"3","to_author_name":"Sying","under_id":"0","nickname":"Dolan Yves","avatar":"http://img.owspace.com//@/avatar/2016-04-19/46efbcfbfd6809f2eb2b34ab01ee874a45"}]
         * html5 : http://static.owspace.com/wap/294943.html
         * author : 任宇
         * tpl : 1
         * avatar : https://img.owspace.com/Public/static/avatar/1.png
         * category : TO READ
         * parseXML : 1
         */

        private String id;
        private String uid;
        private String name;
        private String title;
        private String excerpt;
        private String lead;
        private String model;
        private String position;
        private String thumbnail;
        private String create_time;
        private String update_time;
        private String status;
        private String video;
        private String fm;
        private String link_url;
        private String publish_time;
        private String view;
        private String share;
        private String comment;
        private String good;
        private String bookmark;
        private String show_uid;
        private String fm_play;
        private String lunar_type;
        private String html5;
        private String author;
        private int tpl;
        private String avatar;
        private String category;
        private int parseXML;
        private List<TagsBean> tags;
        private List<HotCommentsBean> hot_comments;

        protected DatasBean(Parcel in) {
            id = in.readString();
            uid = in.readString();
            name = in.readString();
            title = in.readString();
            excerpt = in.readString();
            lead = in.readString();
            model = in.readString();
            position = in.readString();
            thumbnail = in.readString();
            create_time = in.readString();
            update_time = in.readString();
            status = in.readString();
            video = in.readString();
            fm = in.readString();
            link_url = in.readString();
            publish_time = in.readString();
            view = in.readString();
            share = in.readString();
            comment = in.readString();
            good = in.readString();
            bookmark = in.readString();
            show_uid = in.readString();
            fm_play = in.readString();
            lunar_type = in.readString();
            html5 = in.readString();
            author = in.readString();
            tpl = in.readInt();
            avatar = in.readString();
            category = in.readString();
            parseXML = in.readInt();
        }

        public static final Creator<DatasBean> CREATOR = new Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel in) {
                return new DatasBean(in);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public String getLead() {
            return lead;
        }

        public void setLead(String lead) {
            this.lead = lead;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getFm() {
            return fm;
        }

        public void setFm(String fm) {
            this.fm = fm;
        }

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getGood() {
            return good;
        }

        public void setGood(String good) {
            this.good = good;
        }

        public String getBookmark() {
            return bookmark;
        }

        public void setBookmark(String bookmark) {
            this.bookmark = bookmark;
        }

        public String getShow_uid() {
            return show_uid;
        }

        public void setShow_uid(String show_uid) {
            this.show_uid = show_uid;
        }

        public String getFm_play() {
            return fm_play;
        }

        public void setFm_play(String fm_play) {
            this.fm_play = fm_play;
        }

        public String getLunar_type() {
            return lunar_type;
        }

        public void setLunar_type(String lunar_type) {
            this.lunar_type = lunar_type;
        }

        public String getHtml5() {
            return html5;
        }

        public void setHtml5(String html5) {
            this.html5 = html5;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getTpl() {
            return tpl;
        }

        public void setTpl(int tpl) {
            this.tpl = tpl;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getParseXML() {
            return parseXML;
        }

        public void setParseXML(int parseXML) {
            this.parseXML = parseXML;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<HotCommentsBean> getHot_comments() {
            return hot_comments;
        }

        public void setHot_comments(List<HotCommentsBean> hot_comments) {
            this.hot_comments = hot_comments;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(uid);
            dest.writeString(name);
            dest.writeString(title);
            dest.writeString(excerpt);
            dest.writeString(lead);
            dest.writeString(model);
            dest.writeString(position);
            dest.writeString(thumbnail);
            dest.writeString(create_time);
            dest.writeString(update_time);
            dest.writeString(status);
            dest.writeString(video);
            dest.writeString(fm);
            dest.writeString(link_url);
            dest.writeString(publish_time);
            dest.writeString(view);
            dest.writeString(share);
            dest.writeString(comment);
            dest.writeString(good);
            dest.writeString(bookmark);
            dest.writeString(show_uid);
            dest.writeString(fm_play);
            dest.writeString(lunar_type);
            dest.writeString(html5);
            dest.writeString(author);
            dest.writeInt(tpl);
            dest.writeString(avatar);
            dest.writeString(category);
            dest.writeInt(parseXML);
        }

        public static class TagsBean implements Parcelable {
            /**
             * name :
             */

            private String name;

            protected TagsBean(Parcel in) {
                name = in.readString();
            }

            public static final Creator<TagsBean> CREATOR = new Creator<TagsBean>() {
                @Override
                public TagsBean createFromParcel(Parcel in) {
                    return new TagsBean(in);
                }

                @Override
                public TagsBean[] newArray(int size) {
                    return new TagsBean[size];
                }
            };

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(name);
            }
        }

        public static class HotCommentsBean implements Parcelable{
            /**
             * id : 39457
             * pid : 0
             * uid : 218409
             * content : 我们不要陷入自我的陷阱。唯有务实才是真正的办法
             * post_id : 292322
             * update_time : 2 天前
             * good : 6
             * model : 3
             * to_author_name : Sying
             * under_id : 0
             * nickname : Dolan Yves
             * avatar : http://img.owspace.com//@/avatar/2016-04-19/46efbcfbfd6809f2eb2b34ab01ee874a45
             */

            private String id;
            private String pid;
            private String uid;
            private String content;
            private String post_id;
            private String update_time;
            private String good;
            private String model;
            private String to_author_name;
            private String under_id;
            private String nickname;
            private String avatar;

            protected HotCommentsBean(Parcel in) {
                id = in.readString();
                pid = in.readString();
                uid = in.readString();
                content = in.readString();
                post_id = in.readString();
                update_time = in.readString();
                good = in.readString();
                model = in.readString();
                to_author_name = in.readString();
                under_id = in.readString();
                nickname = in.readString();
                avatar = in.readString();
            }

            public static final Creator<HotCommentsBean> CREATOR = new Creator<HotCommentsBean>() {
                @Override
                public HotCommentsBean createFromParcel(Parcel in) {
                    return new HotCommentsBean(in);
                }

                @Override
                public HotCommentsBean[] newArray(int size) {
                    return new HotCommentsBean[size];
                }
            };

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPost_id() {
                return post_id;
            }

            public void setPost_id(String post_id) {
                this.post_id = post_id;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getGood() {
                return good;
            }

            public void setGood(String good) {
                this.good = good;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getTo_author_name() {
                return to_author_name;
            }

            public void setTo_author_name(String to_author_name) {
                this.to_author_name = to_author_name;
            }

            public String getUnder_id() {
                return under_id;
            }

            public void setUnder_id(String under_id) {
                this.under_id = under_id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(pid);
                dest.writeString(uid);
                dest.writeString(content);
                dest.writeString(post_id);
                dest.writeString(update_time);
                dest.writeString(good);
                dest.writeString(model);
                dest.writeString(to_author_name);
                dest.writeString(under_id);
                dest.writeString(nickname);
                dest.writeString(avatar);
            }
        }
    }
}
