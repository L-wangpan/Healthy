package com.healthy.wp.SmallCircle.presenter;

import android.content.Context;

import com.healthy.wp.SmallCircle.IT.GetFreshData;
import com.healthy.wp.SmallCircle.IT.ITRefreshUI;
import com.healthy.wp.SmallCircle.model.SmallCicleModel;
import com.healthy.wp.SmallCircle.model.Talk;
import com.healthy.wp.SmallCircle.model.talkItem;

import java.util.List;

/**
 * Created by wan_g_000 on 2016/4/24.
 */
public class CiclePresentor {
    ITRefreshUI refresh;
    Context context;
    GetFreshData callback;
    SmallCicleModel smallCicleModel;
    Talk talk;
    String id;
    public CiclePresentor(Context context, ITRefreshUI smallCircle,String user_id) {
        this.context = context;
        this.refresh = smallCircle;
        this.id = user_id;
        smallCicleModel = new SmallCicleModel();
    }

    public void getfresh() {

        smallCicleModel.getData(
                callback = new GetFreshData() {
                    @Override
                    public void success(List<talkItem> talks) {
                        refresh.RefreshUi(talks);
                    }

                    @Override
                    public void fail() {

                    }
                },context,id);

    }

}
