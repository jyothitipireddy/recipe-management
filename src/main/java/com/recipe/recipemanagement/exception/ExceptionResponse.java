package com.recipe.recipemanagement.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Jyothi Tipireddy
 * This class is used to format the response in case of errors
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private int statusCode;
    private String URI;
    private String message;
    private LocalDateTime timestamp;
    private String details;
}
