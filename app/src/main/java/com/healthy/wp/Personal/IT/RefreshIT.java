package com.healthy.wp.Personal.IT;

import com.healthy.wp.Personal.model.PlanResult;
import com.healthy.wp.Personal.model.historyData;
import com.healthy.wp.UserLogin.model.UserMessage;

import java.util.List;

/**
 * Created by wan_g_000 on 2016/4/4.
 */
public interface RefreshIT {
    void Error(int code);
    void success(String path);
    void RefreshUI(PlanResult ps);
    void RefreshIdentify(UserMessage user);
    void RefreshMyId(List<historyData> datas);
}
