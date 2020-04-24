package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipesData = new HashSet();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        assertTrue(recipesData.contains(recipe));
    }

    @Test
    public void getRecipeById() throws Exception {
        Recipe recipeId = new Recipe();
        recipeId.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipeId);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipe = recipeService.getRecipeById(1L);

        assertNotNull(recipe);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
}