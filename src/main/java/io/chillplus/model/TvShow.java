package io.chillplus.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TvShow {
    private Long id;

    @NotBlank
    @NotNull
    private String title;
    private String category;

    public TvShow() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    @Override public String toString() {
        return "TvShow{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
