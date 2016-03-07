package custem;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.meituan.R;


/**
 * 把viewpager和原点导航组合在一起的控件，用法参考PioFragment
 */
public class ViewPagerNvaView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "print";
    private int uncheckedimgId;
    private int checkedimgId;
    private int pagecount;
    private ViewPager viewPager;
    private LinearLayout llchecked;
    private LinearLayout llunchecked;
    private PagerAdapter adapter;
    private LinearLayout.LayoutParams layoutParams;
    private ImageView imageView;
    private ImageView unimageView;
    private boolean isFirst=true;

    public ViewPagerNvaView(Context context) {
        super(context);
        if(pagecount>0){
            initView();
        }

    }

    public ViewPagerNvaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        priseattrs(context, attrs);
        if(pagecount>0){
            initView();
        }
    }

    /**
     * 解析配置文件设置的属性值，获得图片的样子和个数，也是viewpager的item个数
     * @param context
     * @param attrs
     */
    private void priseattrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.viewpagernavview);
        checkedimgId=typedArray.getResourceId(R.styleable.viewpagernavview_checkedimg,0);
        uncheckedimgId=typedArray.getResourceId(R.styleable.viewpagernavview_uncheckedimg,0);
        pagecount=typedArray.getInteger(R.styleable.viewpagernavview_count, 0);
        typedArray.recycle();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.viewpagernvaview_layout,this,true);
        viewPager= (ViewPager) findViewById(R.id.vp_vpn);
        llchecked= (LinearLayout) findViewById(R.id.ll_checked_vpn);
        llunchecked= (LinearLayout) findViewById(R.id.ll_unchecked_vpn);
        loadView();

    }

    private void loadView() {

        Log.d(TAG, "loadView: "+pagecount);
        layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i=0;i<pagecount;i++){
            unimageView=new ImageView(getContext());
            unimageView.setImageResource(uncheckedimgId);
            unimageView.setLayoutParams(layoutParams);
            unimageView.setPadding(5,0,5,10);
            llunchecked.addView(unimageView);
        }
        unimageView.measure(0,0);
        imageView=new ImageView(getContext());
        imageView.setImageResource(checkedimgId);
        imageView.setLayoutParams(layoutParams);
        imageView.setPadding(5, 0, unimageView.getMeasuredWidth()*(pagecount-1)+5,10);
        llchecked.addView(imageView);
        if (adapter!=null){
            Log.d(TAG, "loadView: "+"adpter");
            viewPager.setAdapter(adapter);
        }

    }

    /**
     * 可控件内的viewpager设置adapter
     * @param adapter
     */
    public void setadapter(PagerAdapter adapter){
        this.removeAllViews();
        this.adapter=adapter;
        pagecount=adapter.getCount();
        if(pagecount>0){
            initView();
        }
        viewPager.addOnPageChangeListener(this);
    }


    /**
     * 通过代码方式设置选中图片，一般是个圆点,布局文件可以通过属性设置，那么不需要再在代码中设置
     * @param uncheckedimgId
     */
    public void setUncheckedimgId(int uncheckedimgId) {
        this.uncheckedimgId = uncheckedimgId;
    }

    /**
     * 通过代码方式设置未选中图片，一般是个圆点，那么不需要再在代码中设置
     * @param checkedimgId
     */
    public void setCheckedimgId(int checkedimgId) {
        this.checkedimgId = checkedimgId;
    }

    /**
     //     * 结合ViewPager使用的方法
     //     * @param position
     //     * @param pyl
     //     */
    public void setNavAddress(int position, float pyl){
        imageView.setPadding((int) (unimageView.getWidth() * (position + pyl))+5,0,(int)(unimageView.getWidth()*(pagecount-1-position-pyl)),10);
    }


    /**
     * viewpager事件，和导航图标关联起来
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (!isFirst){
            setNavAddress(position,positionOffset);
        }else {
            isFirst=false;
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
