package fuji_151a.twitter.analysis

/**
 * Created by yuya on 2015/06/19.
 */
class TfIdf {
  def tf(nodeList:Seq[String]) = {
    var tfMap = scala.collection.mutable.Map[String, Int]()
    val maxNode = nodeList.length
    nodeList.foreach({word =>
    if (tfMap.contains(word)) {
      tfMap(word) += (1 / maxNode)
    } else {
      tfMap(word) = (1 / maxNode)
    }
    })
    tfMap
  }

  /**
   * Create Document Frequency's List
   * @param word
   * @return dfMap
   */
  def createDfList(word:String) = {
    var dfMap = scala.collection.mutable.Map[String, Int]()
    if (dfMap.contains(word)) {
      dfMap(word) += 1
    } else {
      dfMap(word) = 1
    }
    dfMap
  }

}
