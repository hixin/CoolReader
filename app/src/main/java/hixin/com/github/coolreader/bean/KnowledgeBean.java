package hixin.com.github.coolreader.bean;

import java.io.Serializable;

/**
 * Created by hixin on 2017/9/6.
 *
 * @version CoolReader 1.0
 */

public class KnowledgeBean implements Serializable{
    private ArticleBean[] result;

    public ArticleBean[] getResult() {

        return result;
    }

    public void setResult(ArticleBean[] result) {

        this.result = result;

    }
}
