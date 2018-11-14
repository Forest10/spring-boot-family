package com.forest10.spring.boot.family.conf;

import com.forest10.spring.boot.family.enums.event.RegEventEnum;
import com.forest10.spring.boot.family.enums.state.RegStatesEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @author Forest10
 * @date 2018/11/14 16:28
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<RegStatesEnum, RegEventEnum> {

    @Override
    public void configure(StateMachineStateConfigurer<RegStatesEnum, RegEventEnum> states) throws Exception {
        states.withStates()
            // 定义初始状态
            .initial(RegStatesEnum.UNCONNECTED)
            // 定义状态机状态
            .states(EnumSet.allOf(RegStatesEnum.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<RegStatesEnum, RegEventEnum> transitions) throws Exception {
        transitions
            // 1.连接事件
            // 未连接 -> 已连接
            .withExternal()
            .source(RegStatesEnum.UNCONNECTED)
            .target(RegStatesEnum.CONNECTED)
            .event(RegEventEnum.CONNECT)
            .and()

            // 2.注册事件   
            // 已连接 -> 注册中
            .withExternal()
            .source(RegStatesEnum.CONNECTED)
            .target(RegStatesEnum.REGISTERING)
            .event(RegEventEnum.REGISTER)
            .and()

            // 3.注册成功事件   
            // 注册中 -> 已注册
            .withExternal()
            .source(RegStatesEnum.REGISTERING)
            .target(RegStatesEnum.REGISTERED)
            .event(RegEventEnum.REGISTER_SUCCESS)
            .and()

            // 5.注销事件
            // 已连接 -> 未连接
            .withExternal()
            .source(RegStatesEnum.CONNECTED)
            .target(RegStatesEnum.UNCONNECTED)
            .event(RegEventEnum.UN_REGISTER)
            .and()
            // 注册中 -> 未连接
            .withExternal()
            .source(RegStatesEnum.REGISTERING)
            .target(RegStatesEnum.UNCONNECTED)
            .event(RegEventEnum.UN_REGISTER)
            .and()
            // 已注册 -> 未连接
            .withExternal()
            .source(RegStatesEnum.REGISTERED)
            .target(RegStatesEnum.UNCONNECTED)
            .event(RegEventEnum.UN_REGISTER);
    }

}
