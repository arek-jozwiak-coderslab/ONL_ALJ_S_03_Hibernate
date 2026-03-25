package pl.coderslab.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.entity.Book;

import java.util.stream.Collectors;

@RestController
public class ValidationController {

    private final Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping(value = "/validate", produces = "text/plain")
    public String validate() {
        Book book = new Book();
        String collect = validator.validate(book).stream()
                .map(cv ->
                        cv.getPropertyPath().toString().concat(" : ").concat(cv.getMessage())
                ).collect(Collectors.joining(", "));
        System.out.println(collect);
        return collect;
    }
}
