package com.linjiasong.article.entity.dto;

import lombok.Data;

/**
 * @author linjiasong
 * @date 2025/2/13 下午2:08
 */
@Data
public class QianWenChatDTO {

    private String content;

    public boolean checkParam(){
        if(this.content == null || this.content.isBlank()){
            return false;
        }

        if(this.content.length() > 200){
            return false;
        }

        return true;
    }
}
