package team2.proto.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name="hashtag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@ToString(exclude = "post")
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="hashtag", nullable = false)
    private String hashtag;

    @ManyToOne
    @JoinColumn(name="post_id", referencedColumnName="id")
    private Post post;

    @Builder
    public Hashtag(String hashtag, Post post) {
        this.hashtag = hashtag;
        this.post = post;
    }

    public static Hashtag createHashtag(String hashtag, Post post) {
        return Hashtag.builder()
                .hashtag(hashtag)
                .post(post)
                .build();
    }
}
