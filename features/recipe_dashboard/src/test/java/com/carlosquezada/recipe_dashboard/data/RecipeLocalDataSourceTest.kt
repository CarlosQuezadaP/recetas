package com.carlosquezada.recipe_dashboard.data


import com.carlosquezada.recipe_dashboard.data.mapper.toDomain
import com.carlosquezada.recipe_dashboard.data.response.RecipeDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class RecipeLocalDataSourceTest {

    private val queryNameTest = "queryNameTest"

    private val recipe = RecipeDto(
        "",
        emptyList(),
        queryNameTest,
        "",
        emptyList(),
        emptyList()
    ).toDomain()

    private val recipeList = listOf(
        recipe
    )


    private var recipeLocalDataSource = RecipeLocalDataSource()


    @Test
    fun `when recipesIsEmpty is call should get a false value`() =
        runTest {
            recipeLocalDataSource.setRecipesData(recipeList)
            Assert.assertFalse(recipeLocalDataSource.recipesIsEmpty())
        }

    @Test
    fun `when recipesIsEmpty is call should get a true value`() =
        runTest {
            Assert.assertTrue(recipeLocalDataSource.recipesIsEmpty())
        }

    @Test
    fun `given a queryName parameter when getRecipeByName is call should get a RecipeDomain`() =
        runTest {
            recipeLocalDataSource.setRecipesData(recipeList)
            Assert.assertEquals(recipeLocalDataSource.getRecipeByName(queryNameTest), recipe)
        }

    @Test
    fun `given a queryName parameter  when getRecipeByName is call shouldnt get a RecipeDomain`() =
        runTest {
            recipeLocalDataSource.setRecipesData(recipeList)
            Assert.assertNotEquals(recipeLocalDataSource.getRecipeByName("other"), recipe)
        }


}