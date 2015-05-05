package fuji_151a.twitter.analysis

import org.junit.{Before,Test}
import org.scalatest.Assertions
/**
 * Created by yuya on 2015/05/06.
 */
class TwitterAnalysisTest extends Assertions {

  private var filePath: String = ""


  @Before
  def setUp:Unit = {
    val fileName = "201501010000.json"
    val twcntTest = new TwitterAnalysisTest()
    filePath = twcntTest.getClass.getClassLoader.getResource(fileName).getPath
  }

  @Test
  def twitterCountTest():Unit = {
    val twcnt = new TwitterAnalysis()
    val actual:Int = twcnt.tweetCountFromFile(filePath)
    val expected:Int = 36398
    assert(actual == expected)
  }

}
