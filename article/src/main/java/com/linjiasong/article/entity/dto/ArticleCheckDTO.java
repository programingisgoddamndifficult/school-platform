package com.linjiasong.article.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author linjiasong
 * @date 2025/1/23 下午5:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCheckDTO {
    private Long id;

    private String title;

    private short tag;

    private String context;

    private List<String> imageUrl;

    private short isOpen;

    public static ArticleCheckDTO build(ArticleCreateDTO articleCreateDTO, Long id){
        return ArticleCheckDTO.builder()
                .id(id)
                .title(articleCreateDTO.getTitle())
                .tag(articleCreateDTO.getTag())
                .context(articleCreateDTO.getContext())
                .imageUrl(articleCreateDTO.getImageUrl())
                .isOpen(articleCreateDTO.getIsOpen())
                .build();
    }
}
