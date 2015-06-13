package fuji_151a.twitter.analysis.utils

import org.junit.Test
import org.scalatest.Assertions

/**
 * Created by yuya on 2015/06/13.
 */
class FilterUtils$Test extends Assertions {
  @Test
  def nodeFilterNounTest():Unit = {
    val str:String = "名詞,一般,*,*,*,*,りんご,リンゴ,リンゴ";
    val actual:Boolean = FilterUtils.nodeFilter(str)
    val expected:Boolean = true
    assert(actual == expected)
  }

  @Test
  def nodeFilterProperNounTest():Unit = {
    val str:String = "名詞,固有名詞,人名,姓,*,*,京,キョウ,キョー"
    val actual:Boolean = FilterUtils.nodeFilter(str)
    val expected:Boolean = true
    assert(actual == expected)
  }

  @Test
  def nodeFilterFalseTest():Unit = {
    val str:String = "形容詞,自立,*,*,形容詞・イ段,基本形,美しい,ウツクシイ,ウツクシイ"
    val actual:Boolean = FilterUtils.nodeFilter(str)
    val expected:Boolean = false
    assert(actual == expected)
  }
}
