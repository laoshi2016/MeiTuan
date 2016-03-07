package base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 16-3-5.
 */
public abstract class BaseAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setContentId());
        ButterKnife.bind(this);
        init();
        loaddata();
    }

    protected abstract int setContentId();


    protected void init(){}


    protected void loaddata() {}
}
