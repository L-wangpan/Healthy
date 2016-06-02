package com.healthy.wp.SmallCircle.IT;

import com.healthy.wp.SmallCircle.model.talkItem;

import java.util.List;

/**
 * Created by wan_g_000 on 2016/4/24.
 */
public interface GetFreshData {
    void success(List<talkItem> talks);
    void fail();
}
