package io.github.byference.mapper.model;

import io.github.byference.mapper.core.FieldName;
import io.github.byference.mapper.core.Id;
import io.github.byference.mapper.core.TableName;
import lombok.Data;

/**
 * UserInfo
 *
 * @author byference
 * @since 2020-08-29
 */
@Data
@TableName("user_info")
public class UserInfo {

    /**
     * user id
     */
    @Id
    @FieldName("ID")
    private Integer id;

    /**
     * username
     */
    @FieldName("USERNAME")
    private String username;

    /**
     * password
     */
    @FieldName("PASSWORD")
    private String password;

}