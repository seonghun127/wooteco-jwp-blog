package techcourse.myblog.dto;

import lombok.Data;

public class ArticleDto {

    @Data
    public static class Create {
        private String title;
        private String coverUrl;
        private String contents;
    }
}