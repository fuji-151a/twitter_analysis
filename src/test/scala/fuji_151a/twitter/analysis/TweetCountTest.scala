package fuji_151a.twitter.analysis

import fuji_151a.twitter.analysis.utils.TestEnv
import org.junit.{Before,Test}
import org.scalatest.Assertions
/**
 * Created by yuya on 2015/05/06.
 */
class TweetCountTest extends Assertions {

  private var filePath: String = ""


  @Before
  def setUp:Unit = {
    val fileName = "201501010000.json"
    val twcntTest = new TweetCountTest()
    filePath = twcntTest.getClass.getClassLoader.getResource(fileName).getPath
  }

  @Test
  def tweetCountFromFileTest():Unit = {
    val twcnt = new TweetCount()
    val actual:Int = twcnt.tweetCountFromFile(filePath)
    val expected:Int = 36398
    assert(actual == expected)
  }

  @Test
  def tweetCountSegHourTest():Unit = {
    val twcnt = new TweetCount
    val expected = 10
    for (i <- 1 to 10) {
      val testData = TestEnv.createTestData()
      twcnt.tweetCountSegHour(testData)
    }
    val actual = twcnt.getCnt().get("2015010100").get
    assert(actual == expected)
  }
}
