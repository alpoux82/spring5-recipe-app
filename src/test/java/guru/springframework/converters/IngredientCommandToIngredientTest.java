package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private static final Long ID = new Long(1L);
    private static final String DESCRIPTION = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(1);
    private static final Long ID_UOM_COMMAND = new Long(1L);
    private static final String DESCRIPTION_UOM_COMMAND = "descriptionUom";

    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Before
    public void setUp() throws Exception {
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(ingredientCommandToIngredient.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID_UOM_COMMAND);
        unitOfMeasureCommand.setDescription(DESCRIPTION_UOM_COMMAND);
        ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommand);
        //when
        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
        //then
        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_UOM_COMMAND, ingredient.getUnitOfMeasure().getId());
        assertEquals(DESCRIPTION_UOM_COMMAND, ingredient.getUnitOfMeasure().getDescription());
    }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        command.setUnitOfMeasureCommand(null);
        //when
        Ingredient ingredient = ingredientCommandToIngredient.convert(command);
        //then
        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertNull(ingredient.getUnitOfMeasure());
    }

}