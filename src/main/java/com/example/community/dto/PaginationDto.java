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
public class PaginationDto<T> {
    /**
     * 数据
     */
    private List<T> data;
    /**
     * 显示之前
     */
    private boolean showPrevious;
    /**
     * 显示第一页
     */
    private boolean showFirstPage;
    /**
     * 显示下一个
     */
    private boolean showNext;
    /**
     * 显示结束页
     */
    private boolean showEndPage;
    /**
     * 页面
     */
    private Integer page;
    /**
     * 页面
     */
    private List<Integer> pages = new ArrayList<>();
    /**
     * 总页
     */
    private Integer totalPage;
    /**
     * 大小
     */
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
