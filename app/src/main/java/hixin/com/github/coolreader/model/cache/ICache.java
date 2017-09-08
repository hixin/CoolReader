package hixin.com.github.coolreader.model.cache;

import java.util.List;

/**
 * Created by hixin on 2017/9/6.
 *
 * @version CoolReader 1.0
 */

public interface ICache<T> {
    void cache();
    void saveCache();
    void loadFromNet();
    void loadFromCache();
    List<T> getmList();
}
