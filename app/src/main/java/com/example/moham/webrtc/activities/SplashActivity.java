package com.example.moham.webrtc.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.BaseServiceException;
import com.quickblox.core.exception.QBResponseException;
import com.example.moham.webrtc.ui.activity.CoreSplashActivity;
import com.example.moham.webrtc.utils.SharedPrefsHelper;
import com.example.moham.webrtc.App;
import com.example.moham.webrtc.R;
import com.example.moham.webrtc.services.CallService;
import com.quickblox.users.model.QBUser;

/**
 * Created by tereha on 12.04.16.
 */
public class SplashActivity extends CoreSplashActivity {

    private SharedPrefsHelper sharedPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefsHelper = SharedPrefsHelper.getInstance();

        if (sharedPrefsHelper.hasQbUser()) {
            startLoginService(sharedPrefsHelper.getQbUser());
            startOpponentsActivity();
            return;
        }

        createSession();
    }

    @Override
    protected String getAppName() {
        return getString(R.string.splash_app_title);
    }

    @Override
    protected void proceedToTheNextActivity() {
        LoginActivity.start(this);
        finish();
    }

    private void createSession() {
        App.getInstance().getQbResRequestExecutor().createSession(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle params) {
                proceedToTheNextActivity();
            }

            @Override
            public void onError(QBResponseException e) {
                showSnackbarError(null, R.string.splash_create_session_error, e, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createSession();
                    }
                });
            }
        });
    }

    protected void startLoginService(QBUser qbUser) {
        CallService.start(this, qbUser);
    }

    private void startOpponentsActivity() {
        OpponentsActivity.start(SplashActivity.this, false);
        finish();
    }
}
