package fuji_151a.twitter.analysis.utils

import org.junit.{Before, Test}
import org.scalatest.Assertions

/**
 * Created by yuya on 2015/05/05.
 */
class FileUtils$Test extends Assertions {

  private var filePath: String = ""

  @Before
  def setUp():Unit = {
    val fileName = "gz/201501010000.json.tar.gz"
    filePath = FileUtils.getClass.getClassLoader.getResource(fileName).getPath()
  }

  @Test
  def fromGzipTest():Unit = {
    val bfs = FileUtils.fromGzip(filePath)
    val expected = 10
    val actual = bfs.getLines().size - 1 // lastの制御文字を除く

    assert(actual == expected)
  }

  @Test
  def getFilesInDirTest():Unit = {
    val path = "./src/test/resources/json/"
    val list = FileUtils.getFilesInDir(path)
    assert(list(0).getName == "201501010000.json")

  }
}
