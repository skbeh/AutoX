package org.autojs.autojs

import org.junit.Test
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun test() {
        val matcher = Pattern.compile("[0-9]+").matcher("2937Finish!")
        if (matcher.find()) {
            println(matcher.group())
        }
    }

    @Test
    fun testAutoReorder() {
    }
}