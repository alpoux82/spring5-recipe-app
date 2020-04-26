package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    public static final Long ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("1");
    public static final Integer PREP_TIME = Integer.valueOf("1");
    public static final String DESCRIPTION = "Description";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("1");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CATEGORY_ID_1 = 1L;
    public static final Long CATEGORY_ID_2 = 2L;
    public static final Long INGREDIENT_ID_1 = 1L;
    public static final Long INGREDIENT_ID_2 = 2L;
    public static final Long NOTES_ID = 1L;

    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        recipeCommandToRecipe = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);

        CategoryCommand category = new CategoryCommand();
        category.setId(CATEGORY_ID_1);
        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CATEGORY_ID_2);
        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGREDIENT_ID_1);
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGREDIENT_ID_2);
        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);
        //when
        Recipe recipe  = recipeCommandToRecipe.convert(recipeCommand);
        //then
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertNotNull(recipe.getCategories());
        assertNotNull(recipe.getIngredients());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

}