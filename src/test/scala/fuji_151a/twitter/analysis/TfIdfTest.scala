package fuji_151a.twitter.analysis

import java.io.File

import fuji_151a.twitter.analysis.utils.{FilterUtils, JsonParser, FileUtils}
import org.junit.{Before, Test}
import org.chasen.mecab.Tagger
import org.scalatest.Assertions

import scala.io.Source

/**
 * Created by yuya on 2015/07/08.
 */
class TfIdfTest extends Assertions {

  private var filePath: String = ""

  @Before
  def setUp:Unit = {
    val fileName = "json/201501010000.json"
    filePath = twcntTest.getClass.getClassLoader.getResource(fileName).getPath
  }

  @Test
  def test():Unit = {
    val fileList:Seq[File] = FileUtils.getFilesInDir(args(0))
    val tagger:Tagger = new Tagger("-d " + dicPath)
    var wordcnt = scala.collection.mutable.Map[String, Int]()
    var dfcnt = scala.collection.mutable.Map[String, Int]()
    for (file <- fileList) {
      var dfSet:Set[String] = Set()
      for (line <- Source.fromFile(file.getAbsolutePath).getLines) {
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

            if (dfSet.contains(text)) {
              dfSet += text
              dfcnt(text) += 1
            } else {
              dfcnt(text) = 1
            }
          }
          node = node.getNext
        }
      }
    }
  }
}
