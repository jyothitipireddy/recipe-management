package com.recipe.recipemanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.recipe.recipemanagement.enumes.ItemType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Jyothi Tipireddy
 * Model class for Recipe
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipe {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    private LocalDateTime createdOn;
    @NotBlank(message = "ItemType is mandatory")
    private ItemType itemType;
    private Integer serving;
    private String cookingInstructions;
    @NotBlank(message = "Ingredients are mandatory")
    private List<Ingredient> ingredients;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
