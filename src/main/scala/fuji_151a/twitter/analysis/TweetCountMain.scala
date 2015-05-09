package fuji_151a.twitter.analysis

import java.io.File

import fuji_151a.twitter.analysis.utils.FileUtils

import scala.io.Source

/**
 * Created by yuya on 2015/05/06.
 */
object TweetCountMain {
  /**
   * メモリバカ食い問題
   * @param args
   */
  def main(args: Array[String]) {
    if (args.length < 2) {
      println("Please Input args[0]:Input Tweet File")
      println("Please Input args[1]:Mode")
      sys.exit(1)
    }
    val fileList:Seq[File] = FileUtils.getFilesInDir(args(0))
    args(1) match {
      case "-d" => val date = args(0).takeRight(9)
        val count = dayTweetCnt(fileList)
        println(date.replace("/", "") + "\t" + count)
      case "-h" => val count = hourTweetCnt(fileList)
        for((key, value) <- count) {
          println("%s\t%s".format(key, value))
        }
    }
  }

  private def dayTweetCnt(fileList:Seq[File]) = {
    val twa:TweetCount = new TweetCount
    twa.tweetCount(fileList)
  }

  private def hourTweetCnt(fileList:Seq[File]) = {
    val twa:TweetCount = new TweetCount
    for (file <- fileList) {
      for(line <- Source.fromFile(file.getAbsolutePath).getLines) {
        twa.tweetCountSegHour(line)
      }
    }
    twa.getCnt()
  }
}
