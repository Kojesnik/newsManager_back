package com.epam.lab.dto.impl;

import com.epam.lab.dto.EntityDto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class TagDto extends EntityDto {

    private long id;

    @NotNull(message = "Provide tag name")
    @Size(min = 1, max = 30, message = "Tag name is invalid. Length must be 1 - 30")
    private String name;

    public TagDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagDto(String name) {
        this.name = name;
    }

    public TagDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDto tagDto = (TagDto) o;
        return Objects.equals(id, tagDto.id) &&
                Objects.equals(name, tagDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
