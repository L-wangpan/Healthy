package com.healthy.wp.Personal.IT;

import com.healthy.wp.Personal.model.PlanResult;
import com.healthy.wp.Personal.model.historyData;

import java.util.List;

/**
 * Created by wan_g_000 on 2016/4/4.
 */
public interface RefreshIT {
    void Error(int code);
    void success(int code);
    void RefreshUI(PlanResult ps);
    void RefreshMyId(List<historyData> datas);
}
