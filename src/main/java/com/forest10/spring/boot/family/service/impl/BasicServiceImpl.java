package com.forest10.spring.boot.family.service.impl;

import com.forest10.spring.boot.family.enums.event.RegEventEnum;
import com.forest10.spring.boot.family.enums.state.RegStatesEnum;
import com.forest10.spring.boot.family.service.BasicService;
import com.forest10.template.ServiceHandleTemplate;
import com.forest10.template.calback.BaseServiceProcessCallBackNoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Forest10
 * @date 2018/9/4 下午5:22
 */
@Slf4j
@Service
public class BasicServiceImpl implements BasicService {

    @Resource
    StateMachine<RegStatesEnum, RegEventEnum> stateMachine;

    @Override
    public void connect() {
        ServiceHandleTemplate.executeNoResult(new BaseServiceProcessCallBackNoResult() {
            @Override
            protected void processNoResult() {
                log.info("connect");
            }

            @Override
            public void afterProcess() {
                stateMachine.start();
                stateMachine.sendEvent(RegEventEnum.CONNECT);
            }
        });
    }

    @Override
    public void reg() {
        ServiceHandleTemplate.executeNoResult(new BaseServiceProcessCallBackNoResult() {
            @Override
            protected void processNoResult() {
                log.info("reg");
            }

            @Override
            public void afterProcess() {
                stateMachine.start();
                stateMachine.sendEvent(RegEventEnum.REGISTER);
            }
        });
    }
}
