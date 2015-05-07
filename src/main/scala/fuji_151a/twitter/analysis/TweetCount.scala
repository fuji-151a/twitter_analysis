package fuji_151a.twitter.analysis

import fuji_151a.twitter.analysis.utils.{ConvertUtils, JsonParser}

import scala.collection.mutable
import scala.io.Source

/**
 * Created by yuya on 2015/05/05.
 */
class TweetCount {

  private var cnt: mutable.HashMap[String, Int] = mutable.HashMap()

  def tweetCountFromFile(file: String, enc: String = "UTF-8"): Int = {
    Source.fromFile(file).getLines().size
  }

  def tweetCountSegHour(tweetData: String) = {
    val parseData = JsonParser.parseTweetData(tweetData)
    val timeMs = parseData.get("timestamp_ms").toString.toLong
    val format = "yyyyMMddHH"
    val date = ConvertUtils.unix2Date(timeMs, format)
    tweetCount(date)
  }

  private def tweetCount(date: String) = {
    if (cnt.contains(date)) {
      val num:Int= cnt.get(date).get + 1
      cnt.put(date, num)
    } else {
      cnt.put(date, 1)
    }
  }

  def getCnt() = {
    cnt
  }
}
