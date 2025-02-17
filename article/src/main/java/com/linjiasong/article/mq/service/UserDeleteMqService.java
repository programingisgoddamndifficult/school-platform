package com.linjiasong.article.mq.service;

import com.alibaba.fastjson.JSON;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.entity.ArticleUserWatch;
import com.linjiasong.article.mq.dto.UserDeleteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/21 下午4:08
 */
@Service("USER_DELETE")
@Slf4j
public class UserDeleteMqService extends AbstractMqService {

    @Override
    public void consume(String mqData) {
        UserDeleteDTO userDeleteDTO = JSON.parseObject(mqData, UserDeleteDTO.class);
        List<ArticleBasicInfo> articleBasicInfoList = articleBasicInfoGateway.getByUserId(userDeleteDTO.getUserId());
        if (articleBasicInfoList == null || articleBasicInfoList.isEmpty()) {
            log.info(String.format("USER_DELETE 消费MQ消息成功 mqData:%s", mqData));
            return;
        }

        if (!articleBasicInfoGateway.deleteByUserId(userDeleteDTO.getUserId())) {
            log.info(String.format("USER_DELETE 消费MQ消息失败 mqData:%s", mqData));
            throw new RuntimeException();
        }

        if(!articleUserWatchGateway.deleteUserWatch(articleUserWatchGateway.getUserWatchList(userDeleteDTO.getUserId())
                .stream().map(ArticleUserWatch::getId).toList())){
            log.info(String.format("USER_DELETE 消费MQ消息失败 mqData:%s", mqData));
            throw new RuntimeException();
        }

        log.info(String.format("USER_DELETE 消费MQ消息成功 mqData:%s", mqData));
    }
}
