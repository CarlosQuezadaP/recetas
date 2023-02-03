package com.carlosquezada.recipe_dashboard.data


import com.carlosquezada.recipe_dashboard.Result
import com.carlosquezada.recipe_dashboard.data.mapper.toDomain
import com.carlosquezada.recipe_dashboard.data.response.RecipeDto
import com.carlosquezada.recipe_dashboard.domain.RecipeDomain
import com.carlosquezada.recipe_dashboard.domain.exceptions.RecipeException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class RecipeRepositoryTest {


    private val recipeLocalData: RecipeLocalDataSource = mockk()
    private val recipeRemoteData: RecipeRemoteDataSource = mockk()

    private lateinit var recipeRepository: RecipeRepository

    private val recipeList = listOf(
        RecipeDto(
            "",
            emptyList(),
            "",
            "",
            emptyList(),
            emptyList()
        )
    ).map { it.toDomain() }


    private val resultSuccessDomainList =
        Result.Success(recipeList)

    private val resultFailure =
        Result.Failure(RecipeException.RecipeNotAvailable())

    private val emptyList = emptyList<RecipeDomain>()

    @Before
    fun setup() {
        recipeRepository = RecipeRepository(recipeLocalData, recipeRemoteData)
    }


    @Test
    fun `when getAllRecipes is called then should get Result Success with recipe list `() =
        runTest {
            every {
                recipeLocalData.getLocalRecipes()
            }.answers { recipeList }

            every {
                recipeLocalData.recipesIsEmpty()
            }.answers { false }

            val recipes = recipeRepository.getAllRecipes()

            Assert.assertEquals(recipes, resultSuccessDomainList)
        }

    @Test
    fun `when getAllRecipes is called then should get Result Failure with RecipeException RecipeNotAvailable() `() =
        runTest {
            every {
                recipeLocalData.getLocalRecipes()
            }.answers { recipeList }

            every {
                recipeLocalData.recipesIsEmpty()
            }.answers { true }

            coEvery {
                recipeRemoteData.getAllRecipes()
            }.answers { Result.Failure(RecipeException.RecipeNotAvailable()) }

            val recipes = recipeRepository.getAllRecipes()

            Assert.assertNotSame(resultFailure, recipes )
        }


}