package com.meongmory.meongmory.domain.diary.dto.response;

import com.meongmory.meongmory.domain.diary.entity.DiaryComment;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentRes {

  private Long commentId;
  private String author;
  private String comment;

  @Builder
  public CommentRes(Long commentId, String author, String comment) {
    this.commentId = commentId;
    this.author = author;
    this.comment = comment;
  }

  public static CommentRes toDto(DiaryComment diaryComment) {
    return CommentRes.builder()
            .commentId(diaryComment.getDiaryCommentId())
            .author(diaryComment.getUser().getNickname())
            .comment(diaryComment.getComment())
            .build();
  }
}
