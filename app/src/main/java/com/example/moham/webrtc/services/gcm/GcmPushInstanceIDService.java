package com.example.moham.webrtc.services.gcm;

import com.example.moham.webrtc.services.gcm.CoreGcmPushInstanceIDService;
import com.example.moham.webrtc.utils.Consts;

public class GcmPushInstanceIDService extends CoreGcmPushInstanceIDService {
    @Override
    protected String getSenderId() {
        return Consts.GCM_SENDER_ID;
    }
}
