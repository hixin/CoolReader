package hixin.com.github.coolreader.bean;

import java.io.Serializable;

/**
 * Created by hixin on 2017/9/6.
 *
 * @version CoolReader 1.0
 */
/*json example
    {
            "image":"",
            "is_replyable":true,
            "channels":Array[1],
            "channel_keys":Array[1],
            "preface":"",
            "id":442384,
            "subject":Object{...},
            "copyright":"owned_by_guokr",
            "author":Object{...},
            "image_description":"",
            "is_show_summary":false,
            "minisite_key":null,
            "image_info":Object{...},
            "subject_key":"others",
            "minisite":null,
            "tags":Array[0],
            "date_published":"2017-09-05T16:05:03+08:00",
            "authors":Array[1],
            "replies_count":18,
            "is_author_external":false,
            "recommends_count":1,
            "title_hide":"把鬼节办成派对，可不是老外的专利",
            "date_modified":"2017-09-05T16:58:11.246707+08:00",
            "url":"http://www.guokr.com/article/442384/",
            "title":"把鬼节办成派对，可不是老外的专利",
            "small_image":"https://1-im.guokr.com/CT475yWq2g0Bit5opyUEKbU-qDbOYlpBhQyjaDfNmEtKAQAA6wAAAEpQ.jpg",
            "summary":"中元鬼节也是个热闹的节日呢。",
            "ukey_author":"a0e7cs",
            "date_created":"2017-09-05T16:05:03+08:00",
            "resource_url":"http://apis.guokr.com/minisite/article/442384.json"
    },
*/

public class ArticleBean implements Serializable{

    /*self define begin*/
    private int is_collected = 0;
    private String Info;

    public void setIs_collected(int is_collected) {this.is_collected = is_collected;}
    public void setInfo(String info) {Info = info;}
    public int getIs_collected() {return is_collected;}
    public String getInfo() {return Info;}
    /*self define end*/

    private String date_published;
    private Author author;
    private int replies_count;
    private String url;
    private String title;
    private String small_image;
    private String summary;

    class Author implements Serializable{
        String nickname;
        public String getNickname() {
            return nickname;
        }
    }

    public String getDate_published() {
        return date_published;
    }

    public Author getAuthor() {
        return author;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getSmall_image() {
        return small_image;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String toString() {
        return "ArticleBean{" +
                "is_collected=" + is_collected +
                ", Info='" + Info + '\'' +
                ", date_published='" + date_published + '\'' +
                ", author=" + author.getNickname() +
                ", replies_count=" + replies_count +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", small_image='" + small_image + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
