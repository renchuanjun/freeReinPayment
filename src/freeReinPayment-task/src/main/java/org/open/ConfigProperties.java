package org.open;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 任传君
 * @create 2018-05-23 11:56
 **/
@ConfigurationProperties(prefix="config")
public class ConfigProperties extends BasisConfigProperties{


    private boolean task;

    private Integer poolSize;

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }

    public boolean isTask() {
        return task;
    }

    public void setTask(boolean task) {
        this.task = task;
    }
}
