package com.tianji.learning.constants;

public interface RedisConstants {
    /**
     * 签到记录的Key的前缀：sign:uid:110:202301
     */
    String SIGN_RECORD_KEY_PREFIX = "sign:uid:";
    /**
     * 积分排行榜的Key的前缀：boards:202301
     */
    String POINTS_BOARD_KEY_PREFIX = "boards:";

    /**
     * 每日积分记录的Key的前缀：points:today:20230101:uid:
     */
    String POINTS_RECORD_KEY_PREFIX = "points:today:";
}
