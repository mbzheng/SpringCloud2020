package com.atgui.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public interface IMessageProvider {
    String send();
}
