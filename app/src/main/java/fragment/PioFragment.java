package fragment;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meituan.R;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;
import custem.ViewPagerNvaView;


public class PioFragment extends BaseFragment{
    private List<TextView> datas;
    private ViewPagerNvaView viewPagerNvaView;


    @Override
    protected int getContentId() {
        return R.layout.fragment_poi;
    }

   @Override
    protected void init(View view) {
        viewPagerNvaView= (ViewPagerNvaView) view.findViewById(R.id.vpn_poi);
        datas=new ArrayList<>();
        for (int i=0;i<6;i++){
            TextView textView=new TextView(getActivity());
            textView.setText(i+"fsdfdsfdsf");
            datas.add(textView);
        }
        PagerAdapter pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView(datas.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(datas.get(position));
                return datas.get(position);
            }
        };
        viewPagerNvaView.setadapter(pagerAdapter);
    }
}
