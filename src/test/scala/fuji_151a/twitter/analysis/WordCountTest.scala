package fuji_151a.twitter.analysis

import org.junit.Test
import org.scalatest.Assertions

/**
 * Created by yuya on 2015/06/12.
 */
class WordCountTest extends Assertions {

  @Test
  def cntTest():Unit = {
    val wc = new WordCount
    val wordList:Seq[String] = Seq("りんご", "りんご", "りんご",
      "レモン", "メロン", "メロン")
    val map:scala.collection.mutable.Map[String, Int] = wc.cnt(wordList)
    assert(map("りんご") == 3)
    assert(map("メロン") == 2)
    assert(map("レモン") == 1)
  }
}
