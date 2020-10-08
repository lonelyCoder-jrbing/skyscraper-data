package com.skyscraper.data.api.paperapi;

import com.skyscraper.data.api.dto.PaperDTO;

import java.io.IOException;
import java.util.List;

/**
 * create by sumerian on 2020/10/8
 * <p>
 * desc: 从es中查询文章信息
 **/
public interface PaperCommentApi {

    /****
     * 使用文章id查询文章
     * @param id
     * @return
     */
    PaperDTO getPaperById(String id);

    /****
     * 使用专业名称模糊查询文章
     * @param major
     * @return
     */
    List<PaperDTO> getPaperByMajor(String major) throws IOException;


    /****
     * 使用学校名称模糊查询文章
     * @param school
     * @return
     */
    List<PaperDTO> getPaperBySchool(String school) throws IOException;


    /***
     * 使用文章名称模糊查询文章
     * @param content
     * @return
     */
    List<PaperDTO> getPaperByContent(String content);
}
