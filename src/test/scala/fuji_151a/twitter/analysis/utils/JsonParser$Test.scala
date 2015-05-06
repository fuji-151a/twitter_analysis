package fuji_151a.twitter.analysis.utils

import org.junit.Test
import org.scalatest.Assertions

/**
 * Created by yuya on 2015/05/06.
 */
class JsonParser$Test extends Assertions {

  @Test
  def parseJsonTest():Unit = {
    val text = createTestData()
    val parse = JsonParser.parseJson(text)
    val expected = "あけおめ！！"
    assert(parse.getString("text") == expected)
    val
  }

  private def createTestData():String = {
    val sb:StringBuilder = new StringBuilder
    sb.append("{\"filter_level\":\"medium\",\"retweeted\":false,\"in_reply_to_screen_name\":null,\"possibly_sensitive\":false,\"truncated\":false,\"lang\":\"ja\",\"in_reply_to_status_id_str\"\n:null,\"id\":")
    sb.append("550305376768241665,\"in_reply_to_user_id_str\":null,\"timestamp_ms\":\"1420038000658\",\"in_reply_to_status_id\":null,\"created_at\":\"Wed Dec 31 15:00:00 +00")
    sb.append("00 2014\",\"favorite_count\":0,\"place\":null,\"coordinates\":null,\"text\":\"あけおめ！！\",\"contributors\":null,\"geo\":null,\"entities\":{\"trends\":[],\"symbols\":[],\"urls\":[],\"hashtags\":")
    sb.append("[],\"user_mentions\":[]},\"source\":\"<a href=\\\"http://twitter.com/download/iphone\\\" rel=\\\"nofollow\\\">Twitter for iPhone<\\/")
    sb.append("a>\",\"favorited\":false,\"in_reply_to_user_id\":null,\"retweet_count\":0,\"id_str\":\"550305376768241665\",\"user\":")
    sb.append("{\"location\":\"\",\"default_profile\":true,\"profile_background_tile\":false,\"statuses_count\":7942,\"lang\":\"ja\",\"profile_link_color\":\"0084B4\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/2351952397/1410936849\",\"id\":")
    sb.append("2351952397,\"following\":null,\"protected\":false,\"favourites_count\":1235,\"profile_text_color\":\"333333\",\"verified\":false,\"description\":\"千種206 打楽器（´-`）.｡oO（Gummy'sのドラム")
    sb.append("の人)\",\"contributors_enabled\":false,\"profile_sidebar_border_color\":\"C0DEED\",\"name\":\"れいかー\",\"profile_background_color\":\"C0DEED\",\"created_at\":\"Wed Feb 19 16:35:27 +0000")
    sb.append("2014\",\"default_profile_image\":false,\"followers_count\":105,\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/515498826358996993/")
    sb.append("Cvss6Xqn_normal.jpeg\",\"geo_enabled\":true,\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme1/bg.png\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme1/")
    sb.append("bg.png\",\"follow_request_sent\":null,\"url\":null,\"utc_offset\":null,\"time_zone\":null,\"notifications\":null,\"profile_use_background_image\":true,\"friends_count\":")
    sb.append("93,\"profile_sidebar_fill_color\":\"DDEEF6\",\"screen_name\":\"skyraker357x\",\"id_str\":\"2351952397\",\"profile_image_url\":\"http://pbs.twimg.com/profile_images/")
    sb.append("515498826358996993/Cvss6Xqn_normal.jpeg\",\"listed_count\":0,\"is_translator\":false}}")
    sb.toString
  }
}
