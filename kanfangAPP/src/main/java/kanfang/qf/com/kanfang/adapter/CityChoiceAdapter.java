package kanfang.qf.com.kanfang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import kanfang.qf.com.kanfang.R;
import kanfang.qf.com.kanfang.bean.CityBean;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Stickylistheaders的适配器
 */
public class CityChoiceAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    List<CityBean> data;
    LayoutInflater inflater;

    public CityChoiceAdapter(Context mContext, List<CityBean> data) {
        this.data = data;
        inflater = LayoutInflater.from(mContext);
    }

    //====================Stick..................
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_head_layout, parent, false);
            viewHolder = new HeadViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HeadViewHolder) convertView.getTag();
        }
        //关键：绑定数据
        CityBean bean = data.get(position);
        viewHolder.tv_head.setText(bean.getLetter());

        return convertView;
    }

    /*返回头部分类id*/
    @Override
    public long getHeaderId(int position) {
        return data.get(position).getTypeId();
    }

    class HeadViewHolder {
        TextView tv_head;

        public HeadViewHolder(View view) {
            tv_head = (TextView) view.findViewById(R.id.city_head);
        }
    }


    //========================================================

    //======================ListView=的Adapter===================
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeanViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_item_layout, parent, false);
            viewHolder = new BeanViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BeanViewHolder) convertView.getTag();
        }
        //绑定数据
        CityBean city = data.get(position);
        viewHolder.tv_cityname.setText(city.getCityname());
        return convertView;
    }

    class BeanViewHolder {
        TextView tv_cityname;

        public BeanViewHolder(View view) {
            tv_cityname = (TextView) view.findViewById(R.id.city_name);
        }
    }
}
