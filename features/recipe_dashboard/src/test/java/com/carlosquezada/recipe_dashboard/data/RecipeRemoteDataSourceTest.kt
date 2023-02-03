package com.carlosquezada.recipe_dashboard.data


import com.carlosquezada.core.domain.ErrorHandler
import com.carlosquezada.core.domain.NetworkException
import com.carlosquezada.recipe_dashboard.Result
import com.carlosquezada.recipe_dashboard.data.api.RecipeApi
import com.carlosquezada.recipe_dashboard.data.mapper.toDomain
import com.carlosquezada.recipe_dashboard.data.response.RecipeDto
import com.carlosquezada.recipe_dashboard.data.response.RecipeResponse
import com.carlosquezada.recipe_dashboard.domain.exceptions.RecipeException
import com.carlosquezada.recipe_dashboard.relaxedMockk
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class RecipeRemoteDataSourceTest {

    private val recipeApi: RecipeApi = mockk()
    private val errorHandler = relaxedMockk<ErrorHandler>()


    private val recipeDtosList = listOf(
        RecipeDto(
            "",
            emptyList(),
            "",
            "",
            emptyList(),
            emptyList()
        )
    )

    private val recipeResponse = RecipeResponse(1, recipeDtosList)
    private val resultSuccessDomainList =
        Result.Success(recipeResponse.recipeDtos.map { it.toDomain() })


    private lateinit var recipeRemoteDataSource: RecipeRemoteDataSource


    @Before
    fun setup() {
        recipeRemoteDataSource = RecipeRemoteDataSource(recipeApi, errorHandler)
    }


    @Test
    fun `when getAllRecipes is called then should get Result Success with recipe list `() =
        runTest {
            coEvery {
                recipeApi.getRecipes()
            }.answers { recipeResponse }
            val response = recipeRemoteDataSource.getAllRecipes()
            Assert.assertEquals(resultSuccessDomainList, response)
        }


    @Test
    fun `when getAllRecipes is called then should get Result Failure with RecipeException RecipeNotAvailable`() =
        runTest {
            coEvery {
                recipeApi.getRecipes()
            }.answers { throw NetworkException.ServerError("Internal error") }


            val response = recipeRemoteDataSource.getAllRecipes()
            Assert.assertNotSame(response,RecipeException.RecipeNotAvailable())
        }
}