package com.mrn.springbootjpa.util.paginator;
public class PageItem {
    private int numOfPage;
    private boolean isActualPage;

    public PageItem(int numOfPage, boolean isActualPage) {
        this.numOfPage = numOfPage;
        this.isActualPage = isActualPage;
    }

    public int getNumOfPage() {
        return numOfPage;
    }

    public boolean isActualPage() {
        return isActualPage;
    }
}
