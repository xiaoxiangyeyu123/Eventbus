# Handler产生的内存泄露
Handler是造成内存泄露的一个重要原因，Handler引用Activity会存在内存泄露。  

    public class HandlerActivity extends Activity {  
  
    private final Handler mHandler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {  
            // ...  
        }  
    };  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        mHandler.sendMessageDelayed(Message.obtain(), 60000);  
  
        //just finish this activity  
        finish();  
    }  
  
    }

在编写过程中会出现警告  
In Android, Handler classes should be static or leaks might occur.  
  class 使用静态声明否则可能出现内存泄露
  
**Handler 的生命周期与Activity 不一致**

- 当Android应用启动的时候，会先创建一个UI主线程的Looper对象，Looper实现了一个简单的消息队列，一个一个的处理里面的Message对象。主线程Looper对象在整个应用生命周期中存在。  
- 当在主线程中初始化Handler时，该Handler和Looper的消息队列关联（没有关联会报错的）。发送到消息队列的Message会引用发送该消息的Handler对象，这样系统可以调用 Handler#handleMessage(Message) 来分发处理该消息。    

**handler 引用 Activity 阻止了GC对Acivity的回收**

- 在Java中，非静态(匿名)内部类会默认隐性引用外部类对象。而静态内部类不会引用外部类对象。如果外部类是Activity，则会引起Activity泄露 。
- 当Activity finish后，延时消息会继续存在主线程消息队列中1分钟，然后处理消息。而该消息引用了Activity的Handler对象，然后这个Handler又引用了这个Activity。这些引用对象会保持到该消息被处理完，这样就导致该Activity对象无法被回收，从而导致了上面说的 Activity泄露。  
  
**如何避免Handler产生的内存泄露？**  

     private static class MyHandler extends Handler {  
        private final WeakReference<Context> context;  
  
        public MyHandler(Context context) {  
            context = new WeakReference<Context>(context);  
        }  
  
        @Override  
        public void handleMessage(Message msg) {  
            
        }  
    }  
  
在Activity onStop或者onDestroy的时候，取消掉该Handler对象的Message和Runnable。
  

    @Override  
     public void onDestroy() {  
     mHandler.removeMessages(MESSAGE_1);  
     mHandler.removeCallbacks(mRunnable);  
    }     
  
     
     
使用Handler.Callback接口并且重写Handler
public class WeakRefHandler extends Handler {
    private WeakReference<Callback> mWeakReference;

    public WeakRefHandler(Callback callback) {
        mWeakReference = new WeakReference<Handler.Callback>(callback);
    }

    public WeakRefHandler(Callback callback, Looper looper) {
        super(looper);
        mWeakReference = new WeakReference<Handler.Callback>(callback);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mWeakReference != null && mWeakReference.get() != null) {
            Callback callback = mWeakReference.get();
            callback.handleMessage(msg);
        }
     }
    }
由于是弱引用，当该类需要被回收时，就可以直接被回收掉。 
WeakRefHandler的使用时如下：  

    private Handler.Callback mCallback = new Handler.Callback() {
         @Override
        public boolean handleMessage(Message msg) {
            switch(msg.what){
            }
            return true;
        }
    };  
    private Handler mHandler = new WeakRefHandler(mCallback);


  

参考文章 [http://www.jianshu.com/p/cb9b4b71a820](http://www.jianshu.com/p/cb9b4b71a820)