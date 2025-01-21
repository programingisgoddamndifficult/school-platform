package com.linjiasong.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.linjiasong.user.entity.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author linjiasong
 * @date 2025/1/13 下午4:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String image;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Short isBan;

    @TableLogic
    private Short isDelete;

    public static UserInfo userInfoDtoToUserInfo(UserInfoDTO userInfoDTO) {
        return UserInfo.builder()
                .username(userInfoDTO.getUsername())
                .password(userInfoDTO.getPassword())
                .phone(userInfoDTO.getPhone())
                .email(userInfoDTO.getEmail())
                .build();
    }
}
