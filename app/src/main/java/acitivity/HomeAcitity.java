package acitivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.meituan.R;

import java.util.ArrayList;
import java.util.List;
import base.BaseAcitivity;
import fragment.DealFragment;
import fragment.MoreFragment;
import fragment.PioFragment;
import fragment.UserFragment;

/**
 * 展示正文的界面，由radiogroup和帧布局内嵌fragment组成，该界面已完成，无需修改
 */
public class HomeAcitity extends BaseAcitivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private int rbcheckedposition=0;
    private int showposition=0;


    @Override
    protected int setContentId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init() {
        if (fragments==null){
            fragments=new ArrayList<>();
            fragments.add(new DealFragment());
            fragments.add(new PioFragment());
            fragments.add(new UserFragment());
            fragments.add(new MoreFragment());
        }
        fragmentManager = getSupportFragmentManager();



        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i=0;i<fragments.size();i++){
            transaction.add(R.id.fl_home,fragments.get(i),""+i);
            transaction.hide(fragments.get(i));
        }
        transaction.show(fragments.get(0));
        transaction.commit();


        radioGroup = (RadioGroup) findViewById(R.id.rg_home);
        radioGroup.setOnCheckedChangeListener(this);

        RadioButton radioButton= (RadioButton) radioGroup.getChildAt(0);
        radioButton.setChecked(true);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_deal:
                rbcheckedposition=0;
                break;
            case R.id.rb_poi:
                rbcheckedposition=1;
                break;
            case R.id.rb_user:
                rbcheckedposition=2;
                break;
            case R.id.rb_more:
                rbcheckedposition=3;
                break;
        }
        FragmentTransaction transaction=fragmentManager.beginTransaction();

        if (showposition!=rbcheckedposition){
            transaction.hide(fragments.get(showposition))
                    .show(fragments.get(rbcheckedposition)).commit();
            showposition=rbcheckedposition;
        }


    }
}
