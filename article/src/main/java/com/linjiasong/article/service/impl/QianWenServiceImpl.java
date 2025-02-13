package com.linjiasong.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.linjiasong.article.entity.dto.QianWenChatDTO;
import com.linjiasong.article.entity.dto.QianWenChatResp;
import com.linjiasong.article.excepiton.ArticleBaseResponse;
import com.linjiasong.article.excepiton.BizException;
import com.linjiasong.article.service.QianWenService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author linjiasong
 * @date 2025/2/13 上午11:31
 */
@Service
public class QianWenServiceImpl implements QianWenService {

    private final static String AUTHORIZATION = "Bearer sk-97d5309161f34a1b9d59524e2f664897";
    private final static String CHAT_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions";
    private final static String CONTENT_TYPE = "application/json";
    private final static String MODEL = "qwen-plus";
    private final static String JSON_BODY = "{\n" +
            "    \"model\": \"%s\",\n" +
            "    \"messages\": [\n" +
            "        {\n" +
            "            \"role\": \"user\",\n" +
            "            \"content\": \"%s\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Override
    public ArticleBaseResponse<?> chat(QianWenChatDTO qianWenChatDTO) {
        if (!qianWenChatDTO.checkParam()) {
            throw new BizException("参数异常，请重试");
        }

        String resultResponse = null;
        try {
            HttpURLConnection connection = buildHttpURLConnection();

            String jsonInputString = String.format(JSON_BODY, MODEL, qianWenChatDTO.getContent());

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get response code
            int responseCode = connection.getResponseCode();
            if(responseCode != 200){
                throw new BizException("server error");
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                resultResponse = response.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ArticleBaseResponse.success(Map.of("content", getQianWenRespContent(JSON.parseObject(resultResponse, QianWenChatResp.class).getChoices())));
    }

    private HttpURLConnection buildHttpURLConnection() throws IOException {
        URL url = new URL(CHAT_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set HTTP method
        connection.setRequestMethod("POST");

        // Set headers
        connection.setRequestProperty("Authorization", AUTHORIZATION);
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);

        // Enable input/output streams
        connection.setDoOutput(true);
        return connection;
    }

    private String getQianWenRespContent(List<QianWenChatResp.Choice> choices) {
        return Optional.ofNullable(choices)
                .filter(list -> !list.isEmpty())
                .map(List::getFirst)
                .filter(choice -> "stop".equals(choice.getFinishReason()))
                .map(QianWenChatResp.Choice::getMessage)
                .filter(message -> message.getContent() != null && !message.getContent().isEmpty())
                .map(QianWenChatResp.Choice.Message::getContent)
                .orElseThrow(() -> new BizException("系统异常，请稍后重试"));
    }
}
