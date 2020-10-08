package com.skyscraper.data.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/
@Data
public class PaperDTO implements Serializable {
    private static final long serialVersionUID = 1084899868919390227L;
    private String paperId;
    private String content;
    private String title;
    private String major;
    private String school;

}
