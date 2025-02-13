package com.linjiasong.article.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/2/13 下午3:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QianWenChatReq {
    private final static String MODEL = "qwen-plus";

    private String model;

    private List<Message> messages;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Message {
        private String role;

        private String content;
    }

    public static QianWenChatReq build(String context) {
        return QianWenChatReq.builder()
                .model(MODEL)
                .messages(List.of(Message.builder()
                        .content(context)
                        .role("user")
                        .build()))
                .build();
    }
}
