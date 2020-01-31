package com.github.byference.tinyim.core.message;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of the off-line message store services that stores message in memory.
 *
 * @author byference
 * @since 2020-01-24
 */
public class InMemoryOffLineMessageStore implements OffLineMessageStore {

    private final Map<String, List<OffLineMessage>> messageStore = new ConcurrentHashMap<>();

    private final List<OffLineMessage> EMPTY_LIST = Collections.emptyList();

    private static volatile OffLineMessageStore INSTANCE;

    private InMemoryOffLineMessageStore() {}

    @Override
    public List<OffLineMessage> readMessage(String identify) {

        if (StringUtils.isBlank(identify) || !messageStore.containsKey(identify)) {
            return EMPTY_LIST;
        }
        return messageStore.get(identify);
    }

    @Override
    public void storeMessage(String identify, OffLineMessage offLineMessage) {

        if (StringUtils.isBlank(identify) || offLineMessage == null) {
            return;
        }

        if (messageStore.containsKey(identify)) {
            messageStore.get(identify).add(offLineMessage);
        } else {
            List<OffLineMessage> offLineMessages = new ArrayList<>();
            offLineMessages.add(offLineMessage);
            messageStore.put(identify, offLineMessages);
        }
    }

    @Override
    public void removeMessage(String identify) {
        messageStore.remove(identify);
    }

    public static OffLineMessageStore getInstance() {
        if (INSTANCE == null) {
            synchronized (OffLineMessageStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new InMemoryOffLineMessageStore();
                }
            }
        }
        return INSTANCE;
    }
}
