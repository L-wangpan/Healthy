package com.healthy.wp.Messure.IT;

import com.healthy.wp.Messure.model.UserDetails;

/**
 * Created by wan_g_000 on 2016/4/4.
 */
public interface LoadData {
        void OnSuccess(UserDetails userDetails);
        void Fail(int code);
}
