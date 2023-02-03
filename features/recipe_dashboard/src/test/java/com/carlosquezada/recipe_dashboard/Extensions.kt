package com.carlosquezada.recipe_dashboard

import io.mockk.MockK
import io.mockk.MockKDsl
import kotlin.reflect.KClass

/*
 * Created by E Alexis Duque ON 8/07/2021.
 * duque.duquegarcia@gmail.com
 */
inline fun <reified T : Any> relaxedMockk(
    name: String? = null,
    vararg moreInterface: KClass<*>,
    block: T.() -> Unit = {}
): T = MockK.useImpl {
    MockKDsl.internalMockk(
        name,
        true,
        *moreInterface,
        relaxUnitFun = true,
        block = block
    )
}
