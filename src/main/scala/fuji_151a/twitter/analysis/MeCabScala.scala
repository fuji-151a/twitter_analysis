package fuji_151a.twitter.analysis

import org.chasen.mecab.Tagger

/**
 * Created by yuya on 2015/06/06.
 */
object MeCabScala {
  def main(args: Array[String]) {
    System.loadLibrary("MeCab");
    val tagger = new Tagger("-d /usr/local/Cellar/mecab/0.996/lib/mecab/dic/mecab-ipadic-neologd")
    val str = "本日は晴天なり，ドラゴンボール";
    println(tagger.parse(str));
    var node = tagger.parseToNode(str);

    while(node != null){
      println(node.getSurface() + "\t" + node.getFeature());
      node = node.getNext();
    }
  }
}
