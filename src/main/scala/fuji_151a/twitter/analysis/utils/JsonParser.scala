package fuji_151a.twitter.analysis.utils

import twitter4j.JSONObject

/**
 * Created by yuya on 2015/05/06.
 */
object JsonParser {

  def parseTweetData(text:String) = {
    new JSONObject(text)
  }
}
