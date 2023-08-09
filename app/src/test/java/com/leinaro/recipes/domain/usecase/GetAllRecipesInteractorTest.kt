package com.leinaro.recipes.domain.usecase

import com.leinaro.recipes.Dummy
import com.leinaro.recipes.domain.model.Recipe
import com.leinaro.recipes.domain.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
internal class GetAllRecipesInteractorTest {
    @Mock
    private lateinit var repository: Repository

    private lateinit var interactor: GetAllRecipesInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        interactor = GetAllRecipesInteractor(repository)
    }

    @Test
    fun `should return a Flow with a list of categories when fetching categories successfully`(): Unit = runBlocking{
        // given
        val flow: Flow<List<Recipe>> = flow {
            emit(Dummy.recipeList)
            delay(10)
            emit(listOf())
        }

        `when`(repository.getRecipes()).thenReturn(flow)

        // when
        val recipesResponse = interactor.invoke().first()

        //then
        verify(repository, times(1)).getRecipes()
        assertEquals(Dummy.recipeList,recipesResponse)
    }
}