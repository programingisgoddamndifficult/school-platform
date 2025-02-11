package com.linjiasong.article.entity.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linjiasong.article.entity.ArticleBasicInfo;
import com.linjiasong.article.enums.ArticleTagEnum;
import lombok.Data;

/**
 * @author linjiasong
 * @date 2025/02/10 14:41
 */

@Data
public class ArticlePageSelectDTO {
    private boolean order;

    private short tag;

    /**
     * 0默认 1esc 2desc
     */
    private int like;

    private int collect;

    private int read;

    private int current;

    private int size;

    private Condition condition;

    @Data
    public static class Condition {
        private String title;
    }

    public boolean checkParam() {
        if (ArticleTagEnum.typeInvalid(tag)) {
            tag = 0;
        }

        if (!likeValid()) {
            like = 0;
        }

        if (!collectValid()) {
            collect = 0;
        }

        if (!readValid()) {
            read = 0;
        }

        return current >= 0 && size >= 0;
    }

    public boolean recommend() {
        if (tag == 0 && like == 0 && collect == 0 && read == 0) {
            if (condition == null) {
                return true;
            }
            //condition != null
            return condition.getTitle() == null;
        }

        return false;
    }

    public QueryWrapper<ArticleBasicInfo> buildQueryWrapper() {
        QueryWrapper<ArticleBasicInfo> wrapper = new QueryWrapper<>();

        wrapper.eq("is_check", 1);
        wrapper.eq("is_open", 1);
        wrapper.eq("is_ban", 0);

        if (tag != 0) {
            wrapper.eq("tag", tag);
        }

        if (like != 0) {
            wrapper = like == 1 ? wrapper.orderByAsc("likes_num") : wrapper.orderByDesc("likes_num");
        }

        if (collect != 0) {
            wrapper = collect == 1 ? wrapper.orderByAsc("collect_num") : wrapper.orderByDesc("collect_num");
        }

        if (read != 0) {
            wrapper = read == 1 ? wrapper.orderByAsc("read_num") : wrapper.orderByDesc("read_num");
        }

        if(condition == null){
            return wrapper;
        }

        if(condition.getTitle() != null){
            wrapper.like("article_title", condition.getTitle());
        }

        return wrapper;
    }

    private boolean likeValid() {
        return like == 0 || like == 1 || like == 2;
    }

    private boolean collectValid() {
        return collect == 0 || collect == 1 || collect == 2;
    }

    private boolean readValid() {
        return read == 0 || read == 1 || read == 2;
    }
}
