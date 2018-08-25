package eventbus.yeyu.com.library.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import eventbus.yeyu.com.library.EventBusService;
import eventbus.yeyu.com.library.bean.Request;
import eventbus.yeyu.com.library.bean.Responce;

/**
 * 作者：潇湘夜雨 on 2018/8/25.
 * 邮箱：879689064@qq.com
 */
public class EventbusService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    private EventBusService.Stub mBinder=new EventBusService.Stub() {
        @Override
        public Responce send(Request request) throws RemoteException {
            return null;
        }
    };

}
