package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private static final Long RECIPE_ID = 1L;
    private static final Integer COOK_TIME = Integer.valueOf("1");
    private static final Integer PREP_TIME = Integer.valueOf("1");
    private static final String DESCRIPTION = "Description";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Integer SERVINGS = Integer.valueOf("1");
    private static final String SOURCE = "Source";
    private static final String URL = "Some URL";
    private static final Long CATEGORY_ID_1 = 1L;
    private static final Long CATEGORY_ID_2 = 2L;
    private static final Long INGREDIENT_ID_1 = 1L;
    private static final Long INGREDIENT_ID_2 = 2L;
    private static final Long NOTES_ID = 2L;

    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        recipeToRecipeCommand = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(recipeToRecipeCommand.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(recipeToRecipeCommand.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(CATEGORY_ID_1);
        Category category2 = new Category();
        category2.setId(CATEGORY_ID_2);
        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_ID_2);
        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);
        //when
        RecipeCommand command = recipeToRecipeCommand.convert(recipe);
        //then
        assertNotNull(command);
        assertEquals(RECIPE_ID, command.getId());
        assertEquals(COOK_TIME, command.getCookTime());
        assertEquals(PREP_TIME, command.getPrepTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVINGS, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTES_ID, command.getNotesCommand().getId());
        assertNotNull(command.getCategories());
        assertNotNull(command.getIngredients());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());
    }
}