package com.github.byference.tinyrpc.core.annotation;

import com.github.byference.tinyrpc.core.listener.RpcApplicationContextListener;
import com.github.byference.tinyrpc.core.spring.TinyRpcScannerConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable rpc client.
 *
 * @see RpcApplicationContextListener
 * @see TinyRpcScannerConfigurer
 * @author byference
 * @since 2019/04/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TinyRpcScannerConfigurer.class})
public @interface EnableRpcClient {
}
