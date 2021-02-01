package com.linkdoan.backend.service;

import com.linkdoan.backend.model.PnsRequest;

public interface FCMService {
    String pushNotification(PnsRequest pnsRequest);
}
