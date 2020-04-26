package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final  Long ID = new Long(1L);
    private static final String DESCRIPTION = "description";

    CategoryToCategoryCommand categoryToCategoryCommand;

    @Before
    public void setUp() throws Exception {
        categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    public void testNull() {
        assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryToCategoryCommand.convert(new Category()));
    }

    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);
        //when
        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);
        //then
        assertEquals(ID, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}