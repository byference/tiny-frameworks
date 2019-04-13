package com.github.byference.tinyrpc.core.annotation;

import com.github.byference.tinyrpc.core.listener.RpcApplicationContextListener;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable rpc client.
 *
 * @see RpcApplicationContextListener
 * @author byference
 * @since 2019/04/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RpcApplicationContextListener.class)
public @interface EnableRpcClient {
}
