package eventbus.yeyu.com.library.base;

import java.lang.reflect.Method;

import eventbus.yeyu.com.library.anno.ThreadMode;

/**
 * 作者：潇湘夜雨 on 2018/8/21.
 * 邮箱：879689064@qq.com
 */
public class SubscribleMethod {
    private Method method;
    private ThreadMode threadMode;
    private Class<?>  eventType;
    public SubscribleMethod(Method method, ThreadMode threadMode, Class<?> eventType) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = eventType;
    }

    public Method getMethod() {
        return method;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public Class<?> getEventType() {
        return eventType;
    }
}
