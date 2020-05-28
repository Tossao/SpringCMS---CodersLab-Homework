package pl.coderslab.app.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank
    @Size(min = 5)
    private String name;

    @Column(nullable = false)
    private String description;

//    @JsonIgnore
//    @ManyToMany(mappedBy = "category", fetch = FetchType.EAGER)
//    private List<Article> articleList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

//    public List<Article> getArticleList() {
//        return articleList;
//    }
//
//    public void setArticleList(List<Article> articleList) {
//        this.articleList = articleList;
//    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                //  ", articleList=" + articleList +
                '}';
    }
}
