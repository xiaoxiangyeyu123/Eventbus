// EventBusService.aidl
package eventbus.yeyu.com.library;

// Declare any non-default types here with import statements
import eventbus.yeyu.com.library.bean.Request;
import eventbus.yeyu.com.library.bean.Responce;

interface EventBusService {
Responce send(in Request request);
}
