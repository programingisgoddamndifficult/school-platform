package com.linjiasong.article.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/2/13 上午11:45
 */
@Data
public class QianWenChatResp {
    private List<Choice> choices;
    private String object;
    private Usage usage;
    private long created;
    private String systemFingerprint;
    private String model;
    private String id;

    @Data
    public static class Choice {
        private Message message;
        private String finishReason;
        private int index;
        private Object logprobs;

        @Data
        public static class Message {
            private String role;
            private String content;
        }
    }

    @Data
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
        private PromptTokensDetails promptTokensDetails;

        @Data
        public static class PromptTokensDetails {
            private int cachedTokens;
        }
    }
}
