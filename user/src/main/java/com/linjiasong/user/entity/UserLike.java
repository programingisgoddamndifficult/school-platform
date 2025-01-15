package com.linjiasong.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author linjiasong
 * @date 2025/1/15 上午10:49
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLike {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long beLikedId;

    private LocalDateTime updateTime;

    /**
     * 关注1；黑名单2
     */
    private Short type;
}
