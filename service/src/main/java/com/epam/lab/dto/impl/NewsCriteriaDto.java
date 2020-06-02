package com.epam.lab.dto.impl;

import com.epam.lab.type.SortType;
import java.util.List;
import java.util.Objects;

public class NewsCriteriaDto {

    private AuthorDto authorDto;
    private List<String> tagNames;
    private SortType sortType;
    private long pageNumber;
    private long pageSize;

    public NewsCriteriaDto(AuthorDto authorDto, List<String> tagNames, SortType sortType) {
        this.authorDto = authorDto;
        this.tagNames = tagNames;
        this.sortType = sortType;
    }

    public NewsCriteriaDto() {}

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
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
        NewsCriteriaDto that = (NewsCriteriaDto) o;
        return Objects.equals(authorDto, that.authorDto) &&
                Objects.equals(tagNames, that.tagNames) &&
                sortType == that.sortType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorDto, tagNames, sortType);
    }

    @Override
    public String toString() {
        return "NewsCriteriaDto{" +
                "authorDto=" + authorDto +
                ", tagNames=" + tagNames +
                ", sortType=" + sortType +
                '}';
    }
}
