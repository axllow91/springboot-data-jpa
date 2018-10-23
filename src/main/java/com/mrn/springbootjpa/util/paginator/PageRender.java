package com.mrn.springbootjpa.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

    private List<PageItem> pages;
    private String url;
    private Page<T> page;
    private int totalPages;
    private int numbOfElemPerPage;
    private int actualPage;

    public PageRender(String url, Page<T> page) {

        this.url = url;
        this.page = page;
        this.pages = new ArrayList<>();


        totalPages = page.getTotalPages();
        numbOfElemPerPage = page.getSize();
        actualPage = page.getNumber() + 1;

        int first, last;

        if(totalPages <= numbOfElemPerPage) {
            first = 1;
            last = totalPages;
        } else {
            if(actualPage <= numbOfElemPerPage / 2) {
                first = 1;
                last = numbOfElemPerPage;
            } else if(actualPage >= totalPages - numbOfElemPerPage / 2) {
                first = totalPages - numbOfElemPerPage + 1;
                last = numbOfElemPerPage;
            } else {
                first = actualPage - numbOfElemPerPage / 2;
                last = numbOfElemPerPage;
            }
        }

        for(int i = 0; i < last; i++) {
            pages.add(new PageItem(first + i, actualPage == last + i));
        }

    }

    public String getUrl() {
        return url;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getActualPage() {
        return actualPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    //
    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean ishasNext() {
        return page.hasNext();
    }

    public boolean ishasPrevious() {
        return page.hasPrevious();
    }
}
