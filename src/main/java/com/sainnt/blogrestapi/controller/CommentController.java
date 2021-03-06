package com.sainnt.blogrestapi.controller;

import com.sainnt.blogrestapi.dto.CommentDto;
import com.sainnt.blogrestapi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/posts/{postId}/comments")
@Tag(name="CRUD REST API's for comment resource")
@SecurityRequirement(name = "Authorization")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(description = "Create comment resource REST API")
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @Operation(description = "Get all comments by post id  REST API")
    @GetMapping
    public ResponseEntity<List<CommentDto>> getCommentsByPostId( @PathVariable(name = "postId") long postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @Operation(description = "Get comment by post and comment id REST API")
    @GetMapping(path = "/{commentId}")
    public ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable(name = "postId") long postId,
                                                            @PathVariable(name="commentId") long commentId
    ){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @Operation(description = "Update comment resource REST API")
    @PutMapping(path = "/{commentId}")
    public ResponseEntity<CommentDto> updateComment( @PathVariable(name = "postId") long postId,
                                                     @PathVariable(name="commentId") long commentId,
                                                     @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    @Operation(description = "Delete comment resource REST API")
    @DeleteMapping(path = "/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") long postId,
                                                @PathVariable(name="commentId") long commentId
    ){
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment successfully deleted");
    }

}
