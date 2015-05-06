package fuji_151a.twitter.analysis

import java.io.File

import fuji_151a.twitter.analysis.utils.FileUtils

/**
 * Created by yuya on 2015/05/06.
 */
object TweetCountMain {
  /**
   * メモリバカ食い問題
   * @param args
   */
  def main(args: Array[String]) {
    if (args.length < 1) {
      println("Please Input args[0]")
      sys.exit(1)
    }
    val twa:TweetCount = new TweetCount
    val fileList:Seq[File] = FileUtils.getFilesInDir(args(0))
    val date = args(0).takeRight(9)
    var count = 0
    for (file <- fileList) {
      count += twa.tweetCountFromFile(file.getAbsolutePath)
    }
    println(date.replace("/", "") + "\t" + count)
  }
}
