package com.enjoytrip.model;

import com.enjoytrip.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "board")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    int boardId;
    String title;
    int hit;
    String content;
    @Column(name="del_yn")
    boolean delYn;

    @Column(name="member_id")
    int memberId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", updatable = false)
    private List<CommentBoard> commentBoardList;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", updatable = false)
    private List<Image> imageList;

    public void deleteBoard() {
        this.delYn=true;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void increaseHit() {
        this.hit++;

    }
}
