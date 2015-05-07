package fuji_151a.twitter.analysis.utils

import org.junit.Test
import org.scalatest.Assertions

/**
 * Created by yuya on 2015/05/06.
 */
class JsonParser$Test extends Assertions {

  @Test
  def parseJsonTest():Unit = {
    val text = TestEnv.createTestData()
    val parse = JsonParser.parseTweetData(text)
    val expected = "あけおめ！！"
    assert(parse.getString("text") == expected)
  }
}
