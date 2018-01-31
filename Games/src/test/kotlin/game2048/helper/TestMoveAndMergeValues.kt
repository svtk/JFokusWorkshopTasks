package tests

import org.junit.Assert
import org.junit.Test
import task.moveAndMergeEqual

abstract class AbstractTestMoveAndMergeValues {
    abstract fun <T : Any> moveAndMergeEqual(list: List<T?>, double: (T) -> T): List<T>

    @Test
    fun testSimpleMove1() = testMerge(listOf(null, null, null, "a"), listOf("a"))

    @Test
    fun testSimpleMove2() = testMerge(listOf("b", null, null, "a"), listOf("b", "a"))

    @Test
    fun testSimpleMove3() = testMerge(listOf(null, "b", null, "a"), listOf("b", "a"))

    @Test
    fun testSimpleMerge1() = testMerge(listOf("a", "a", null, null), listOf("aa"))

    @Test
    fun testSimpleMerge2() = testMerge(listOf(null, "a", "a", null), listOf("aa"))

    @Test
    fun testSimpleMerge3() = testMerge(listOf(null, null, "a", "a"), listOf("aa"))

    @Test
    fun testSimpleMerge4() = testMerge(listOf("a", null, "a", null), listOf("aa"))

    @Test
    fun testSimpleMerge5() = testMerge(listOf("a", null, null, "a"), listOf("aa"))

    @Test
    fun testSimpleMerge6() = testMerge(listOf(null, "a", null, "a"), listOf("aa"))

    @Test
    fun testMergeWithExtraElement1() = testMerge(listOf("a", null, "a", "a"), listOf("aa", "a"))

    @Test
    fun testMergeWithExtraElement2() = testMerge(listOf("a", null, "a", "b"), listOf("aa", "b"))

    @Test
    fun testMergeWithExtraElement3() = testMerge(listOf("a", "a", null, "b"), listOf("aa", "b"))

    @Test
    fun testNoMerge1() = testMerge(listOf("a", "b", "a", null), listOf("a", "b", "a"))

    @Test
    fun testNoMerge2() = testMerge(listOf("a", null, "b", "a"), listOf("a", "b", "a"))

    @Test
    fun testMergeInFull1() = testMerge(listOf("a", "a", "b", "a"), listOf("aa", "b", "a"))

    @Test
    fun testMergeInFull2() = testMerge(listOf("a", "a", "b", "b"), listOf("aa", "bb"))

    @Test
    fun testMergeOfThree1() = testMerge(listOf("a", "a", "a", null), listOf("aa", "a"))

    @Test
    fun testMergeOfThree2() = testMerge(listOf("a", null, "a", "a"), listOf("aa", "a"))

    fun testMerge(input: List<String?>, expected: List<String?>) {
        val result = moveAndMergeEqual(input) { "$it$it" }
        Assert.assertEquals(expected, result)
    }
}