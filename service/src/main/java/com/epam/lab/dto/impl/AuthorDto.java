package com.epam.lab.dto.impl;

import com.epam.lab.dto.EntityDto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class AuthorDto extends EntityDto {

    private long id;

    @NotNull(message = "Provide author name")
    @Size(min = 2, max = 30, message = "Author name is invalid. Length must be 1 - 30")
    @Pattern(regexp = "^[a-zA-Zа-яёА-ЯЁ]+$", message = "Author name is invalid. Use a-z,A-Z,а-я,А-Я")
    private String name;

    @NotNull(message = "Provide author surname")
    @Size(min = 1, max = 30, message = "Author surname is invalid. Length must be 1 - 30")
    @Pattern(regexp = "^[a-zA-Zа-яёА-ЯЁ]+$", message = "Author name is invalid. Use a-z,A-Z,а-я,А-Я")
    private String surname;

    public AuthorDto(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public AuthorDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public AuthorDto() {}

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(id, authorDto.id) &&
                Objects.equals(name, authorDto.name) &&
                Objects.equals(surname, authorDto.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
