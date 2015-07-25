package fuji_151a.twitter.analysis

import java.io.File

import fuji_151a.twitter.analysis.utils.{FileUtils, FilterUtils, JsonParser}
import org.chasen.mecab.Tagger

import scala.io.Source

/**
 * Created by yuya on 2015/06/21.
 */
object TfIdfMain {
  private val dicPath = "/usr/local/Cellar/mecab/0.996/lib/mecab/dic/mecab-ipadic-neologd"
  /**
   * Main Class
   * @param args
   */
  def main(args: Array[String]): Unit = {
    val dirList: Seq[File] = FileUtils.getFilesInDir(args(0))
    var dfcnt = scala.collection.mutable.Map[String, Int]()
    var wordcnt = scala.collection.mutable.Map[String, Int]()
    var wordcntAll = scala.collection.mutable.Map[String, scala.collection.mutable.Map[String, Int]]()
    var maxWord = scala.collection.mutable.Map[String, Int]()
    System.loadLibrary("MeCab");
    val tagger: Tagger = new Tagger("-d " + dicPath)
    for (dir <- dirList) {
      println(dir.getName)
      val fileList: Seq[File] = FileUtils.getFilesInDir(args(0) + "/" + dir.getName)
      var max = 0
      for (file <- fileList) {
        var dfSet: Set[String] = Set()
        for (line <- Source.fromFile(file.getAbsolutePath).getLines) {
          val parseData = JsonParser.parseTweetData(line)
          val tweet: String = parseData.get("text").toString.replaceAll("\n", " ")
          var node = tagger.parseToNode(tweet)
          while (node != null) {
            val text = node.getSurface
            if (FilterUtils.nodeFilter(node.getFeature)) {
              if (wordcnt.contains(text)) {
                wordcnt(text) += 1
              } else {
                wordcnt(text) = 1
              }

              if (dfSet.contains(text)) {
                dfcnt(text) += 1
              } else {
                dfSet += text
                dfcnt(text) = 1
              }
              max += 1
            }
            node = node.getNext
          }
        }
      }
      wordcntAll += dir.getName -> wordcnt.clone()
      maxWord += dir.getName -> max
      wordcnt.clear()
    }
    val maxDoc = 7
    var output = scala.collection.mutable.Map[Double, String]()

    dfcnt.foreach({ case (k, v) =>
      print(k + "\t")
      var cnt = 0
      for (dir <- dirList) {
        cnt += 1
        val node = wordcntAll(dir.getName)
        val nodeSize = maxWord(dir.getName)
        if (node.contains(k)) {
          val tf = node.get(k).get.toDouble / nodeSize.toDouble
          val idf = Math.log(maxDoc.toDouble / v.toDouble)
          val tfIdf: Double = tf * idf
          print(tfIdf)
        } else {
          print(0)
        }
        if (cnt == 7) {
          print("\n")
        } else {
          print("\t")
        }
      }
    })
  }
}
