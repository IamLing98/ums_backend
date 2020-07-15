package com.linkdoan.backend.base.dto;

public abstract class SystemDTO {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer page;
    private String id;
    private java.lang.String pathFile;

    public java.lang.String getPathFile() {
        return pathFile;
    }

    public void setPathFile(java.lang.String pathFile) {
        this.pathFile = pathFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
    private boolean isSize;

    public boolean getIsSize() {
        return isSize;
    }

    public void setIsSize(boolean isSize) {
        this.isSize = isSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Integer pageSize;
    private String keySearch1;
    private String keySearchAction;
    // thêm keysearch2
    private String keySearch2;
    private String keySearch3;
    private String keySearch4;
    private Integer keySearch5;

    public String getKeySearchAction() {
        return keySearchAction;
    }

    public void setKeySearchAction(String keySearchAction) {
        this.keySearchAction = keySearchAction;
    }

    public String getKeySearch1() {
        return keySearch1;
    }

    public void setKeySearch1(String keySearch1) {
        this.keySearch1 = keySearch1;
    }

    public String getKeySearch2() {
        return keySearch2;
    }

    public void setKeySearch2(String keySearch2) {
        this.keySearch2 = keySearch2;
    }
    public String getKeySearch3() {
        return keySearch3;
    }

    public void setKeySearch3(String keySearch3) {
        this.keySearch3 = keySearch3;
    }

    public String getKeySearch4() {
        return keySearch4;
    }

    public void setKeySearch4(String keySearch4) {
        this.keySearch4 = keySearch4;
    }

    public Integer getKeySearch5() {
        return keySearch5;
    }

    public void setKeySearch5(Integer keySearch5) {
        this.keySearch5 = keySearch5;
    }

    private Integer totalRecord;

    public java.lang.Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(java.lang.Integer totalRecord) {
        this.totalRecord = totalRecord;
    }
}
