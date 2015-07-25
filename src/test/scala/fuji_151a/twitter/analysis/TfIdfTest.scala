package fuji_151a.twitter.analysis

import fuji_151a.twitter.analysis.utils.{FilterUtils, JsonParser, FileUtils}
import org.chasen.mecab.Tagger
import org.junit.{Ignore, Before, Test}
import org.scalatest.Assertions
import java.io.File

import scala.io.Source

/**
 * Created by yuya on 2015/07/08.
 */
class TfIdfTest extends Assertions {

  private var filePath: String = ""
  private val dicPath = "/usr/local/Cellar/mecab/0.996/lib/mecab/dic/mecab-ipadic-neologd"

  @Before
  def setUp:Unit = {
    val fileName = "data/"
    val tfidfTest = new TfIdfTest;
    filePath = tfidfTest.getClass.getClassLoader.getResource(fileName).getPath

  }

  @Ignore
  @Test
  def test():Unit = {
    val dirList: Seq[File] = FileUtils.getFilesInDir(filePath)
    var dfcnt = scala.collection.mutable.Map[String, Int]()
    var wordcnt = scala.collection.mutable.Map[String, Int]()
    var wordcntAll = scala.collection.mutable.Map[String, scala.collection.mutable.Map[String, Int]]()
    var maxWord = scala.collection.mutable.Map[String, Int]()
    System.loadLibrary("MeCab");
    val tagger: Tagger = new Tagger("-d " + dicPath)
    for (dir <- dirList) {
      val fileList: Seq[File] = FileUtils.getFilesInDir(filePath + "/" + dir.getName)
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

      //    for(file <- fileList) {
      //      val node = wordcntAll(file.getName)
      //      val nodeSize = maxWord(file.getName)
      //      dfcnt.foreach( { case(k, v) =>
      //        if (node.contains(k)) {
      //          val tf = node.get(k).get.toDouble / nodeSize.toDouble
      //          val idf = Math.log(maxDoc.toFloat / v.toFloat)
      //          val tfIdf:Double = tf * idf
      //          println(k + "\t" + tfIdf)
      //        } else {
      //          println(k + "\t" + 0)
      //        }
      //      })
      //    }
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

  @Test
  def tfidfTest() = {
    val m1 = {
      val b = Map.newBuilder[String, Int]
      b += "サッカー" -> 3
      b += "選手" -> 2
      b.result
    }

    val m2 = {
      val b = Map.newBuilder[String, Int]
      b += "サッカー" -> 5
      b += "試合" -> 3
      b.result
    }

    val m3 = {
      val b = Map.newBuilder[String, Int]
      b += "サッカー" -> 4
      b += "スポーツ" -> 2
      b.result
    }

    val lists:(Map[String, Int], Map[String, Int], Map[String, Int]) = (m1, m2, m3)
    for(list <- lists) {
      println(list)
    }
  }
}
