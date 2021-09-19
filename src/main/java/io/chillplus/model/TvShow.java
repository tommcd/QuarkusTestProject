package io.chillplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tv_show")
public class TvShow extends PanacheEntity {

    @NotBlank
    @Column(name = "title", nullable = false)
    public String title;

    @Column(name = "category")
    public String category;

    @Override public String toString() {
        return "TvShow{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
