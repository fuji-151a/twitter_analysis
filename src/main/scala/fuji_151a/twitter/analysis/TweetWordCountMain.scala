package fuji_151a.twitter.analysis

import java.io.File

import fuji_151a.twitter.analysis.utils.{FileUtils, FilterUtils, JsonParser}
import org.chasen.mecab.Tagger

import scala.io.Source

/**
 * Created by yuya on 2015/06/13.
 */
object TweetWordCountMain {
  private val dicPath = "/usr/local/Cellar/mecab/0.996/lib/mecab/dic/mecab-ipadic-neologd"
  def main(args: Array[String]) {
    if (args.length < 1) {
      println("Please Input args[0]:Input Tweet File")
      sys.exit(1)
    }
    val fileList:Seq[File] = FileUtils.getFilesInDir(args(0))
    System.loadLibrary("MeCab");
    val tagger:Tagger = new Tagger("-d " + dicPath)
    val wc:WordCount = new WordCount
    var wordcnt = scala.collection.mutable.Map[String, Int]()
    for (file <- fileList) {
      for(line <- Source.fromFile(file.getAbsolutePath).getLines) {
        val parseData = JsonParser.parseTweetData(line)
        val tweet:String = parseData.get("text").toString.replaceAll("\n", " ")
        var node = tagger.parseToNode(tweet)
        while (node != null) {
          val text = node.getSurface
          if (FilterUtils.nodeFilter(node.getFeature)) {
            if (wordcnt.contains(text)) {
              wordcnt(text) += 1
            } else {
              wordcnt(text) = 1
            }
          }
          node = node.getNext
        }
      }
    }
//    val sort = ListMap(wordcnt.toSeq.sortWith(_._2 > _._2):_*)
    wordcnt.foreach {case (key, value) =>
        println(key + "\t" + value)
    }
  }
}
