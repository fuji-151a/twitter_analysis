package fuji_151a.twitter.analysis

/**
 * Created by yuya on 2015/06/06.
 */
class WordCount {
  /**
   * Seqを受け取り単語をカウント.
   * @param nodeList 単語のリスト.
   * @return Map[Word, Count]
   */
  def cnt(nodeList:Seq[String]) = {
    var map = scala.collection.mutable.Map[String, Int]()
    nodeList.foreach({word =>
      if (map.contains(word)) {
        map(word) += 1
      } else {
        map(word) = 1
      }
    })
    map
  }
}
