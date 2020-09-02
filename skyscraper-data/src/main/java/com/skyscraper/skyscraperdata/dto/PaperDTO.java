package com.skyscraper.skyscraperdata.dto;

import lombok.Data;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/
@Data
public class PaperDTO {
    private long paperId;
    private String content;
    private String title;
    private String major;
    private String school;

}
