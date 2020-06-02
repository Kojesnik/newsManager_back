package com.epam.lab.dto.impl;

import com.epam.lab.dto.EntityDto;
import com.epam.lab.validator.annotation.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class NewsDto extends EntityDto {

    private long id;

    @NotNull(message = "Provide news title")
    @Size(min = 1, max = 30, message = "News title is invalid. Length must be 1 - 30")
    private String title;

    @NotNull(message = "Provide news short text")
    @Size(min = 1, max = 100, message = "News short text is invalid. Length must be 1 - 100")
    private String shortText;

    @NotNull(message = "Provide news full text")
    @Size(min = 1, max = 2000, message = "News full text is invalid. Length must be 1 - 2000")
    private String fullText;

    @NotNull(message = "Provide news tags")
    private List<TagDto> tags;

    @NotNull(message = "Provide news author")
    @Author
    private AuthorDto author;

    private LocalDate creationDate;
    private LocalDate modificationDate;

    public NewsDto(String title, String shortText, String fullText,
                   List<TagDto> tagDto, AuthorDto authorDto, LocalDate creationDate, LocalDate modificationDate) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.author = authorDto;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.tags = tagDto;
    }

    public NewsDto(String title, String shortText, String fullText,
                   AuthorDto authorDto, LocalDate creationDate, LocalDate modificationDate) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.author = authorDto;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public NewsDto(long id, String title, String shortText, String fullText,
                   LocalDate creationDate, LocalDate modificationDate) {
        this.id = id;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public NewsDto(long id, String title, String shortText, String fullText, AuthorDto authorDto,
                   LocalDate creationDate, LocalDate modificationDate) {
        this.id = id;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.author = authorDto;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public NewsDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NewsDto newsDto = (NewsDto) obj;
        return Objects.equals(id, newsDto.id) &&
                Objects.equals(title, newsDto.title) &&
                Objects.equals(shortText, newsDto.shortText) &&
                Objects.equals(fullText, newsDto.fullText) &&
                Objects.equals(tags, newsDto.tags) &&
                Objects.equals(author, newsDto.author) &&
                Objects.equals(creationDate, newsDto.creationDate) &&
                Objects.equals(modificationDate, newsDto.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, shortText, fullText, tags, author, creationDate, modificationDate);
    }

    @Override
    public String toString() {
        return "NewsDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortText='" + shortText + '\'' +
                ", fullText='" + fullText + '\'' +
                ", tags=" + tags +
                ", author=" + author +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
}
