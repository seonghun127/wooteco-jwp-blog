package techcourse.myblog.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import techcourse.myblog.user.exception.DuplicatedUserException;
import techcourse.myblog.user.exception.InvalidEditFormException;
import techcourse.myblog.user.exception.InvalidSignUpFormException;
import techcourse.myblog.user.exception.NotFoundUserException;
import techcourse.myblog.user.exception.NotMatchPasswordException;

@ControllerAdvice
public class UserExceptionAdvice {
    @ExceptionHandler(InvalidSignUpFormException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidSignUpFormException(InvalidSignUpFormException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "signup";
    }

    @ExceptionHandler(InvalidEditFormException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelInvalidEditFormException(InvalidEditFormException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "mypage";
    }

    @ExceptionHandler(DuplicatedUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelDuplicatedUserException(DuplicatedUserException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/signup";
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundUserException(NotFoundUserException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/login";
    }

    @ExceptionHandler(NotMatchPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotMatchPasswordException(NotMatchPasswordException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/login";
    }
}