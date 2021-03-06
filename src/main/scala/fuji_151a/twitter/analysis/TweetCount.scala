package fuji_151a.twitter.analysis

import java.io.File

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

  def tweetCountSegHour(tweetData: String, format: String) = {
    val parseData = JsonParser.parseTweetData(tweetData)
    val timeMs = parseData.get("timestamp_ms").toString.toLong
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

  def tweetCount(fileList:Seq[File]) = {
    var count = 0
    for (file <- fileList) {
      count += tweetCountFromFile(file.getAbsolutePath)
    }
    count
  }

  def getCnt() = {
    cnt
  }

  def getWordCount(tweetData: String):Int = {
    val parseData = JsonParser.parseTweetData(tweetData)
    val text = parseData.get("text").toString;
    text.length;
  }
}
