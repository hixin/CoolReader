package hixin.com.github.coolreader.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import hixin.com.github.coolreader.bean.ArticleBean;
import hixin.com.github.coolreader.R;
import hixin.com.github.coolreader.view.KnowledgeDetailActivity;

/**
 * Created by hixin on 2017/9/6.
 *
 * @version CoolReader 1.0
 */

public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder>{
    private static final String TAG = "KnowledgeAdapter";
    private Context mContext;
    private List<ArticleBean>  mArticleBeanList;

    public KnowledgeAdapter(List<ArticleBean> articleBeanList) {
        mArticleBeanList = articleBeanList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView parentView;
        TextView title;
        TextView comment;
        SimpleDraweeView image;

        public ViewHolder(View itemView) {
            super(itemView);
            parentView = (CardView) itemView;
            title = (TextView) parentView.findViewById(R.id.title);
            image = (SimpleDraweeView) parentView.findViewById(R.id.image);
            comment = (TextView) parentView.findViewById(R.id.comment);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(null == mContext){
            mContext = parent.getContext();
        }
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_knowledge, parent, false);
        final ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ArticleBean articleBean = mArticleBeanList.get(position);
        Log.d(TAG, "onBindViewHolder: "+ articleBean.toString());
        holder.title.setText(articleBean.getTitle());
        holder.image.setImageURI(Uri.parse(articleBean.getSmall_image()));
        holder.comment.setText(" "+articleBean.getReplies_count());
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });

        holder.parentView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, KnowledgeDetailActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("id_knowledge",articleBean);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticleBeanList.size();
    }
}
