package techcourse.myblog.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import techcourse.myblog.argumentresolver.UserSession;
import techcourse.myblog.comment.dto.CommentCreateDto;
import techcourse.myblog.comment.dto.CommentUpdateDto;
import techcourse.myblog.comment.exception.InvalidCommentLengthException;
import techcourse.myblog.comment.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments/{commentId}/edit")
    public String renderCommentEditPage(@PathVariable long commentId, Model model) {
        model.addAttribute("comments", commentService.findById(commentId));
        return "comment-edit";
    }

    @PostMapping("/articles/{articleId}/comments")
    public RedirectView createComment(@PathVariable long articleId, UserSession userSession, CommentCreateDto commentDto) {
        commentService.save(articleId, userSession.getId(), commentDto);
        return new RedirectView("/articles/" + articleId);
    }

    @PutMapping("/articles/{articleId}/comments/{commentId}")
    public RedirectView updateComment(@PathVariable long articleId, @PathVariable long commentId, UserSession userSession,
                                      @Valid CommentUpdateDto commentDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            request.setAttribute("articleId", articleId);
            throw new InvalidCommentLengthException();
        }
        commentService.update(commentId, userSession.getId(), commentDto);
        return new RedirectView("/articles/" + articleId);
    }

    @DeleteMapping("/articles/{articleId}/comments/{commentId}")
    public RedirectView deleteComment(@PathVariable long articleId, @PathVariable long commentId, UserSession userSession) {
        commentService.delete(commentId, userSession.getId());
        return new RedirectView("/articles/" + articleId);
    }
}