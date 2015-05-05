package fuji_151a.twitter.analysis

import scala.io.Source

/**
 * Created by yuya on 2015/05/05.
 */
class TwitterAnalysis {

  def tweetCountFromFile(file: String, enc: String = "UTF-8"): Int = {
    val source = Source.fromFile(file)
    source.getLines().size
  }
}
