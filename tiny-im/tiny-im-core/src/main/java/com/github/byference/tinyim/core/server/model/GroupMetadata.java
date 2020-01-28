package com.github.byference.tinyim.core.server.model;

import lombok.Data;
import java.util.Set;

/**
 * ImGroupMetadata
 *
 * @author byference
 * @since 2020-01-26
 */
@Data
public class GroupMetadata {

    /**
     * channel group holder
     */
    private String groupHolder;

    /**
     * group members
     */
    private Set<String> groupMembers;

}
