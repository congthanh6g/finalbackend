package com.example.finalapi.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Lob
    private String description;

    private int duration;

    private int view;

    private String image;

    @Column(updatable = false)
    @CreationTimestamp
    private Date create_at;

    @UpdateTimestamp
    private Date update_at;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_categories" ,
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_casts" ,
               joinColumns = @JoinColumn(name = "movie_id"),
                inverseJoinColumns = @JoinColumn(name = "cast_id"))
    private Set<Cast> casts = new HashSet<Cast>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_directors",
               joinColumns = @JoinColumn(name = "movie_id"),
               inverseJoinColumns = @JoinColumn(name = "director_id"))
    private Set<Director> directors = new HashSet<Director>();

    public Movie() {
    }

    public Set<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Director> directors) {
        this.directors = directors;
    }

    public Set<Cast> getCasts() {
        return casts;
    }

    public void setCasts(Set<Cast> casts) {
        this.casts = casts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category)
    {
        this.categories.add(category);
        category.getMovies().add(this);
    }

    public void removeCategory(Category category)
    {
        this.getCategories().remove(category);
        category.getMovies().remove(this);
    }

    public void removeCategory()
    {
        for (Category category : new HashSet<>(categories))
        {
            removeCategory(category);
        }
    }

    public void addCast(Cast cast)
    {
        this.casts.add(cast);
        cast.getMovies().add(this);
    }

    public void removeCast(Cast cast)
    {
        this.getCasts().remove(cast);
        cast.getMovies().remove(this);
    }
    public void removeCast()
    {
        for (Cast cast : new HashSet<>(casts))
        {
            removeCast(cast);
        }
    }

    public void addDirector(Director director)
    {
        this.directors.add(director);
        director.getMovies().add(this);
    }

    public void removeDirector(Director director)
    {
        this.getDirectors().remove(director);
        director.getMovies().remove(this);
    }

    public void removeDirector()
    {
        for (Director director : new HashSet<>(directors))
        {
            removeDirector(director);
        }
    }
}
