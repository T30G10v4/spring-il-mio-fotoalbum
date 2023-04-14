package me.matteogiovagnotti.springilmiofotoalbum.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Set;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    String title;
    @NotBlank
    String description;
    Boolean visible;

    @Lob
    @Column(length = 16777215)
    @Nullable
    private byte[] image;

    @NotBlank
    String url;

    @ManyToMany
    @JoinTable(
            name = "photo_category",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
