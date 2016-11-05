package com.swpuiot.jike;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 羊荣毅_L on 2016/11/1.
 */
public class MyAdapter extends BaseAdapter{
    private Context mycontext;

    public MyAdapter(Context context) {
        this.mycontext = context;
        inflater=LayoutInflater.from(context);

    }

    public List<My> myList = new ArrayList<My>();{
        myList.add(new My(R.drawable.ic_my_topics, "我的主题"));
        myList.add(new My(R.drawable.ic_my_likes,"我喜欢的"));
        myList.add(new My(R.drawable.ic_notification_read, "动态通知"));
        myList.add(new My(R.drawable.ic_secretary,"我的小秘"));
    }
    LayoutInflater inflater;
    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate;
        ViewHodler viewholder;
        if(convertView==null){
            inflate=inflater.inflate(R.layout.my_item, null);
            viewholder=new ViewHodler();
            viewholder.theimagbe= (ImageView) inflate.findViewById(R.id.my_image);
            viewholder.thetext= (TextView) inflate.findViewById(R.id.my_word);
            inflate.setTag(viewholder);
        }
        else{
            inflate=convertView;
            viewholder= (ViewHodler) inflate.getTag();

        }
        My c = myList.get(position);
        viewholder.thetext.setText(c.getWords());
        viewholder.theimagbe.setImageResource(c.getImageid());
        return inflate;
    }
    public class ViewHodler{
        public ImageView theimagbe;
        public TextView thetext;
    }

}
