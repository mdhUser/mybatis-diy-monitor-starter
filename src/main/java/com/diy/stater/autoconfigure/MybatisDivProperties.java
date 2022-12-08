package com.diy.stater.autoconfigure;

import com.diy.stater.constant.ConstantConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author levi
 * @Description //配置类，绑定主项目配置
 * @Param * @param null
 * @return
 **/
@ConfigurationProperties(prefix = ConstantConfig.DIY_PREFIX)
public class MybatisDivProperties {

    /**
     * 开关是否开启
     */
    private boolean enable;
    /**
     * 报警时间阈值
     */
    private long monitorTimeThreshold;

    public MybatisDivProperties() {
    }

    public MybatisDivProperties(boolean enable, long monitorTimeThreshold) {
        this.enable = enable;
        this.monitorTimeThreshold = monitorTimeThreshold;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public long getMonitorTimeThreshold() {
        return monitorTimeThreshold;
    }

    public void setMonitorTimeThreshold(long monitorTimeThreshold) {
        this.monitorTimeThreshold = monitorTimeThreshold;
    }

    @Override
    public String toString() {
        return "MybatisDivProperties{" +
                "enable='" + enable + '\'' +
                ", monitorTimeThreshold='" + monitorTimeThreshold + '\'' +
                '}';
    }
}
