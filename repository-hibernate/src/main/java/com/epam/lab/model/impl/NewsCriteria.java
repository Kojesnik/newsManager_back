package com.epam.lab.model.impl;

import com.epam.lab.type.SortType;

import java.util.List;
import java.util.Objects;

public class NewsCriteria {

    private Author author;
    private List<String> tagNames;
    private SortType sortType;
    private long pageNumber;
    private long pageSize;

    public NewsCriteria(Author author, List<String> tagNames, SortType sortType) {
        this.author = author;
        this.tagNames = tagNames;
        this.sortType = sortType;
    }

    public NewsCriteria() {}

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsCriteria that = (NewsCriteria) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(tagNames, that.tagNames) &&
                sortType == that.sortType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, tagNames, sortType);
    }

    @Override
    public String toString() {
        return "NewsCriteria{" +
                "author=" + author +
                ", tagNames=" + tagNames +
                ", sortType=" + sortType +
                '}';
    }
}
