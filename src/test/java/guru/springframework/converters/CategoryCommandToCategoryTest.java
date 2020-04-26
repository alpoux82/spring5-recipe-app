package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private static final  Long ID = new Long(1L);
    private static final  String DESCRIPTION = "description";

    CategoryCommandToCategory categoryCommandToCategory;

    @Before
    public void setUp() throws Exception {
        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(categoryCommandToCategory.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);
        //when
        Category category = categoryCommandToCategory.convert(categoryCommand);
        //then
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}