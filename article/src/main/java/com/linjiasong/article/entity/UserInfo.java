package com.linjiasong.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author linjiasong
 * @date 2025/1/15 下午4:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Short isBan;

    private Short isDelete;
}
