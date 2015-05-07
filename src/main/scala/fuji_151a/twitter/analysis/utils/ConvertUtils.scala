package fuji_151a.twitter.analysis.utils

import java.util.Calendar

import org.joda.time.DateTime

/**
 * Created by yuya on 2015/05/07.
 */
object ConvertUtils {

  def unix2Date(time_ms:Long, format:String) = {
    val cal = Calendar.getInstance();
    cal.setTimeInMillis(time_ms);
    val dt = new DateTime(cal)
    dt.toString(format)
  }
}
