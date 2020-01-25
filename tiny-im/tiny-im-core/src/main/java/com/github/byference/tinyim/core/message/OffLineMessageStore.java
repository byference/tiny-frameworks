package com.github.byference.tinyim.core.message;

import java.util.List;

/**
 * Persistence interface for the off-line message.
 *
 * @author byference
 * @since 2020-01-24
 */
public interface OffLineMessageStore {

    List<OffLineMessage> readMessage(String identify);

    void storeMessage(String identify, OffLineMessage offLineMessage);

    void removeMessage(String identify);

}
