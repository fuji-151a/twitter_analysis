package fuji_151a.twitter.analysis.utils

import org.scalatest.Assertions
import org.junit.Test

/**
 * Created by yuya on 2015/05/07.
 */
class ConvertUtils$Test extends Assertions {

  @Test
  def unix2DateTest() = {
    val timestamp = 1420038000658L
    val format="yyyyMMddHH"
    val actual = ConvertUtils.unix2Date(timestamp, format)
    val expected = "2015010100"
    assert(actual == expected)
  }
}
