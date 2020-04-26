package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static final  Long ID = new Long(1L);
    private static final  String DESCRIPTION = "description";
    private static final  BigDecimal AMOUNT = new BigDecimal(1);
    private static final  Long ID_UOM = new Long(1L);
    private static final String DESCRIPTION_UOM = "descriptionUom";

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Before
    public void setUp() {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNull() {
        assertNull(ingredientToIngredientCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(ingredientToIngredientCommand.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID_UOM);
        unitOfMeasure.setDescription(DESCRIPTION_UOM);
        ingredient.setUnitOfMeasure(unitOfMeasure);
        //when
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
        //then
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(ID_UOM, ingredientCommand.getUnitOfMeasure().getId());
        assertEquals(DESCRIPTION_UOM, ingredientCommand.getUnitOfMeasure().getDescription());
    }

    @Test
    public void convertNullUnitOfMeasure() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setUnitOfMeasure(null);
        //when
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
        //then
        assertEquals(ID, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertNull(ingredientCommand.getUnitOfMeasure());
    }
}
