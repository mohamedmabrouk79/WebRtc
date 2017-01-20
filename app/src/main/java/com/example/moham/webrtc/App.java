package com.example.moham.webrtc;

import com.example.moham.webrtc.util.QBResRequestExecutor;
import com.example.moham.webrtc.CoreApp;
import com.example.moham.webrtc.utils.Consts;
import com.example.moham.webrtc.util.QBResRequestExecutor;

public class App extends CoreApp {
    private static App instance;
    private QBResRequestExecutor qbResRequestExecutor;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }

    private void initApplication(){
        instance = this;
        super.initCredentials(Consts.APP_ID, Consts.AUTH_KEY, Consts.AUTH_SECRET, Consts.ACCOUNT_KEY);

    }

    public synchronized QBResRequestExecutor getQbResRequestExecutor() {
        return qbResRequestExecutor == null
                ? qbResRequestExecutor = new QBResRequestExecutor()
                : qbResRequestExecutor;
    }
}
