package test;

import android.view.View;
import android.widget.Button;

import com.meituan.R;

import base.BaseAcitivity;
import butterknife.Bind;
import butterknife.OnClick;
import util.OkHttpClientUtil;


/**
 * 仅用于个人测试，最终会删掉
 */
public class TestAcitivity extends BaseAcitivity {
    @Bind(R.id.btn_butterknife)
    protected Button buttonknife;
    @Override
    protected int setContentId() {
        return R.layout.test_layout;
    }



    @OnClick({R.id.btn_butterknife,})
    public void butClick(View view){
        Button button= (Button) view;
        switch (view.getId()){
            case R.id.btn_butterknife:
                button.setText("ok");
                break;
        }

    }



}
