package fuji_151a.twitter.analysis

import scala.io.Source

/**
 * Created by yuya on 2015/05/05.
 */
class TweetCount {

  def tweetCountFromFile(file: String, enc: String = "UTF-8"): Int = {
    val source = Source.fromFile(file)
    try {
      source.getLines().size
    } catch {
      case e:Exception => println(e); 0
    } finally {
      source.close
    }
  }
}
