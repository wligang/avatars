package com.wlgdo.avatar.dubbo.common;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/4/26 15:29
 */

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Page<E> extends ArrayList<E> implements Closeable {
    private static final long serialVersionUID = 1L;
    private int pageNum;
    private int pageSize;
    private int startRow;
    private int endRow;
    private long total;
    private int pages;
    private boolean count;
    private Boolean reasonable;
    private Boolean pageSizeZero;
    private String countColumn;
    private String orderBy;
    private boolean orderByOnly;

    public Page() {
        this.count = true;
    }

    public Page(int pageNum, int pageSize) {
        this(pageNum, pageSize, true, (Boolean) null);
    }

    public Page(int pageNum, int pageSize, boolean count) {
        this(pageNum, pageSize, count, (Boolean) null);
    }

    private Page(int pageNum, int pageSize, boolean count, Boolean reasonable) {
        super(0);
        this.count = true;
        if (pageNum == 1 && pageSize == 2147483647) {
            this.pageSizeZero = true;
            pageSize = 0;
        }

        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
        this.calculateStartAndEndRow();
        this.setReasonable(reasonable);
    }

    public Page(int[] rowBounds, boolean count) {
        super(0);
        this.count = true;
        if (rowBounds[0] == 0 && rowBounds[1] == 2147483647) {
            this.pageSizeZero = true;
            this.pageSize = 0;
        } else {
            this.pageSize = rowBounds[1];
            this.pageNum = rowBounds[1] != 0 ? (int) Math.ceil(((double) rowBounds[0] + (double) rowBounds[1]) / (double) rowBounds[1]) : 0;
        }

        this.startRow = rowBounds[0];
        this.count = count;
        this.endRow = this.startRow + rowBounds[1];
    }

    public List<E> getResult() {
        return this;
    }

    public int getPages() {
        return this.pages;
    }

    public Page<E> setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public int getEndRow() {
        return this.endRow;
    }

    public Page<E> setEndRow(int endRow) {
        this.endRow = endRow;
        return this;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public Page<E> setPageNum(int pageNum) {
        this.pageNum = this.reasonable != null && this.reasonable && pageNum <= 0 ? 1 : pageNum;
        return this;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public Page<E> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getStartRow() {
        return this.startRow;
    }

    public Page<E> setStartRow(int startRow) {
        this.startRow = startRow;
        return this;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
        if (total == -1L) {
            this.pages = 1;
        } else {
            if (this.pageSize > 0) {
                this.pages = (int) (total / (long) this.pageSize + (long) (total % (long) this.pageSize == 0L ? 0 : 1));
            } else {
                this.pages = 0;
            }

            if (this.reasonable != null && this.reasonable && this.pageNum > this.pages) {
                this.pageNum = this.pages;
                this.calculateStartAndEndRow();
            }

        }
    }

    public Boolean getReasonable() {
        return this.reasonable;
    }

    public Page<E> setReasonable(Boolean reasonable) {
        if (reasonable == null) {
            return this;
        } else {
            this.reasonable = reasonable;
            if (this.reasonable && this.pageNum <= 0) {
                this.pageNum = 1;
                this.calculateStartAndEndRow();
            }

            return this;
        }
    }

    public Boolean getPageSizeZero() {
        return this.pageSizeZero;
    }

    public Page<E> setPageSizeZero(Boolean pageSizeZero) {
        if (pageSizeZero != null) {
            this.pageSizeZero = pageSizeZero;
        }

        return this;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public <E> Page<E> setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return (Page<E>) this;
    }

    public boolean isOrderByOnly() {
        return this.orderByOnly;
    }

    public void setOrderByOnly(boolean orderByOnly) {
        this.orderByOnly = orderByOnly;
    }

    private void calculateStartAndEndRow() {
        this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
        this.endRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
    }

    public boolean isCount() {
        return this.count;
    }

    public Page<E> setCount(boolean count) {
        this.count = count;
        return this;
    }

    public Page<E> pageNum(int pageNum) {
        this.pageNum = this.reasonable != null && this.reasonable && pageNum <= 0 ? 1 : pageNum;
        return this;
    }

    public Page<E> pageSize(int pageSize) {
        this.pageSize = pageSize;
        this.calculateStartAndEndRow();
        return this;
    }

    public Page<E> count(Boolean count) {
        this.count = count;
        return this;
    }

    public Page<E> reasonable(Boolean reasonable) {
        this.setReasonable(reasonable);
        return this;
    }

    public Page<E> pageSizeZero(Boolean pageSizeZero) {
        this.setPageSizeZero(pageSizeZero);
        return this;
    }

    public Page<E> countColumn(String columnName) {
        this.countColumn = columnName;
        return this;
    }

    public PageInfo<E> toPageInfo() {
        PageInfo<E> pageInfo = new PageInfo(this);
        return pageInfo;
    }


    public String getCountColumn() {
        return this.countColumn;
    }

    public void setCountColumn(String countColumn) {
        this.countColumn = countColumn;
    }

    public String toString() {
        return "Page{count=" + this.count + ", pageNum=" + this.pageNum + ", pageSize=" + this.pageSize + ", startRow=" + this.startRow + ", endRow=" + this.endRow + ", total=" + this.total + ", pages=" + this.pages + ", reasonable=" + this.reasonable + ", pageSizeZero=" + this.pageSizeZero + '}';
    }


    @Override
    public void close() throws IOException {

    }
}
