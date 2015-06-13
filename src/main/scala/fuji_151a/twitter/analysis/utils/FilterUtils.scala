package fuji_151a.twitter.analysis.utils

/**
 * Created by yuya on 2015/06/13.
 */
object FilterUtils {
  /**
   * 名詞と固有名詞のみFilter
   * @param node Node
   * @return true or false
   */
  def nodeFilter(node:String):Boolean = {
    val filterNoun = "名詞,一般,.*".r
    val filterProperNoun = "名詞,固有名詞,.*".r
    node match {
      case  filterNoun() => true
      case  filterProperNoun() => true
      case _ => false
    }
  }
}
