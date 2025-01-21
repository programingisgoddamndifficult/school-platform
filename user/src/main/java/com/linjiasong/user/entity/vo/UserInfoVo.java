package com.linjiasong.user.entity.vo;

import com.linjiasong.user.entity.UserInfo;
import com.linjiasong.user.utils.DESUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoVo {
    private Long id;

    private String username;

    private String phone;

    private String email;

    private String image;

    private LocalDateTime createTime;

    /**
     * 粉丝数
     */
    private int likeNums;

    public static UserInfoVo build(UserInfo userInfo, int likeNums){
        if(userInfo.getEmail() != null){
            userInfo.setEmail(DESUtil.decrypt(userInfo.getEmail()));
        }

        return UserInfoVo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .phone(DESUtil.decrypt(userInfo.getPhone()))
                .email(userInfo.getEmail())
                .image(userInfo.getImage())
                .createTime(userInfo.getCreateTime())
                .likeNums(likeNums)
                .build();
    }
}
