package com.github.byference.tinyrpc.core.annotation;

import com.github.byference.tinyrpc.core.rpc.RpcServer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * enable rpc server.
 *
 * @see RpcServer
 * @author byference
 * @since 2019/04/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RpcServer.class)
public @interface EnableRpcServer {
}
