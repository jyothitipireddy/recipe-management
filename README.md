# Getting Started

# Recipe management application
This application provides REST APIs to manage different recipes and ingredients. An ingredient can be added independently, then include in the favourite recipes.

## REST APIs

### Recipes
The following REST APIs are created to manage recipes.

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| POST    | /recipes | Create a new recipe | [JSON](#Create recipe) |
| PUT    | /recipes/{recipe_id} | Update an existing recipe |[JSON](#Update recipe) |
| GET    | /recipes/{recipe_id} | Get recipe details of the given recipe id ||
| GET    | /recipes | Get all recipies available | |
| DELETE    | /recipes/{recipe_id} | Delete a recipe| |


### Ingredients

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| POST    | /ingredients | Create anew ingredient | [JSON](#create ingredient) |
| PUT    | /ingredients/{ingredient_id} | Update an existing ingredient | [JSON](#update ingredient) |
| GET    | /ingredients | Get all ingredients | |
| GET    | /igredients/{ingredient_id} | Get the details of the ingredient id| |
| DELETE    | /igredients/{ingredient_id} | Delete an ingredient| |

## Sample requests


##### <a id="recipe">Create recipe</a>
```json
{
   "name":"pizza",
   "description":"Pizza",
   "itemType":"NON_VEG",
   "cookingInstructions":"Mix the ingredients, bake the pizza,etc...",
   "ingredients":[
      {
         "id":29102,
         "name":"Cheese",
         "quantity":10
      }
   ]
}
```
##### <a id="recipe">Update recipe</a>
```json
{
   "id": 1111,
   "name":"pizza",
   "description":"Pizza",
   "itemType":"NON_VEG",
   "cookingInstructions":"Mix the ingredients, bake the pizza,etc...",
   "ingredients":[
      {
         "id":29102,
         "name":"Cheese",
         "quantity":10
      }
   ]
}
```
##### <a id="ingredient">create ingredient</a>
```json
{
"name": "cheese",
"description": "cheese"
}
```

##### <a id="ingredient">update ingredient</a>
```json
{
"id": 1,
"name": "cheese",
"description": "cheese"
}
```

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/jyothitipireddy/recipe-management.git
```

**2. Run the following command.**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>

