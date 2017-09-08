package hixin.com.github.coolreader.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hixin on 2017/9/6.
 */

public class KnowledgeTitle {
    public static String [] channel_tag={"hot","frontier","review","interview","visual","brief","fact","techb"};
    public static String [] channel_title={"热点","前沿","评论","专访","视觉","速读","谣言粉碎机","商业科技"};
    public static String konwledge_channel_url="http://www.guokr.com/apis/minisite/article.json?retrieve_type=by_channel&channel_key=";

    public static Map<String,String> maps = new HashMap<String,String>();

    public static void initMap(){
        int size  = channel_tag.length;
        for(int i=0; i<size; i++){
            maps.put(channel_tag[i], channel_title[i]);
        }
    }
}
