package com.linjiasong.admin.entity.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linjiasong
 * @date 2025/2/18 下午4:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminInfoListVo {
    private long total;

    private long size;

    private long current;

    private List<AdminInfo> adminInfos;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AdminInfo {
        private Long id;

        private String username;

        private LocalDateTime createTime;

        public static AdminInfo build(com.linjiasong.admin.entity.AdminInfo adminInfo) {
            return AdminInfo.builder()
                    .id(adminInfo.getId())
                    .username(adminInfo.getUsername())
                    .createTime(adminInfo.getCreateTime())
                    .build();
        }
    }

    public static AdminInfoListVo build(Page<com.linjiasong.admin.entity.AdminInfo> page) {
        return AdminInfoListVo.builder()
                .total(page.getTotal())
                .size(page.getSize())
                .current(page.getCurrent())
                .adminInfos(AdminInfoListVo.build(page.getRecords()))
                .build();
    }

    public static List<AdminInfo> build(List<com.linjiasong.admin.entity.AdminInfo> records) {
        return records.stream().map(AdminInfo::build).toList();
    }
}
