package com.github.byference.data.structure.core;

/**
 * Action Functional Interface
 *
 * @author byference
 * @since 2019-09-01
 */
@FunctionalInterface
public interface Action {

    /**
     * execute command
     * @throws Exception if execute command had error
     */
    void execute() throws Exception;
}
