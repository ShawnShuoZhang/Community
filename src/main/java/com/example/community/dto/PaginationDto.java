package com.example.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页dto
 *
 * @author Tuoer
 * @date 2023/01/02
 */
@Data
public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 显示下面都有哪几页的表
     */
    private List<Integer> pages = new ArrayList<>();
    /**
     * 总页数
     */
    private Integer totalPage;
    private Integer size;

    /**
     * 设置分页
     *
     * @param pages 总页数
     * @param total 总条数
     * @param page  当前页
     * @param size  每页条数
     */
    public void setPagination(Integer pages, long total, Integer page, Integer size) {
        this.size = size;
        this.totalPage = pages;
        page = page < 1 ? 1 : page;
        page = page > pages ? pages : page;
        this.page = page;
        this.pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                this.pages.add(0, page - i);
            }
            if (page + i <= pages) {
                this.pages.add(page + i);
            }
        }
        //是否展示上一页
        showPrevious = page != 1;
        //是否展示下一页
        showNext = !page.equals(pages);
        //是否展示第一页
        showFirstPage = !pages.equals(0) && !this.pages.contains(1);
        //是否展示最后一页
        showEndPage = !pages.equals(0) && !this.pages.contains(pages);
    }
}
