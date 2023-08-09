package com.leinaro.recipes.domain.repository


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.leinaro.recipes.Dummy
import com.leinaro.recipes.data.network.RecipeService
import com.leinaro.recipes.domain.model.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class RepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var recipeService: RecipeService

    private lateinit var repository: RepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        repository = RepositoryImpl(
            recipeService = recipeService,
        )
    }

    @Test
    fun `should return a Flow with a list of recipes when fetching recipes successfully`(): Unit =
        runBlocking {
            // given
            val recipes: List<Recipe> = Dummy.recipeList

            Mockito.`when`(recipeService.getRecipes()).thenReturn(recipes)

            // when
            val recipesResponse = repository.getRecipes().first()


            //then
            Assert.assertEquals(recipes, recipesResponse)
            Mockito.verify(recipeService, Mockito.times(1)).getRecipes()
        }
}