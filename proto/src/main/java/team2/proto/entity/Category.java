package team2.proto.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name="category")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="category_name", nullable = false, unique = true)
    private String categoryName;

    @OneToMany
    @JoinColumn(name="category_id")
    private List<Post> postList = new ArrayList<Post>();
}

