package fuji_151a.twitter.analysis.utils

import java.io.{BufferedInputStream, FileInputStream}
import java.util.zip.GZIPInputStream

import scala.io.BufferedSource

/**
 * Created by yuya on 2015/05/05.
 */
object FileUtils {

  /**
   * @param file gzipの形式で圧縮されてるテキスト形式のファイル
   * @param enc エンコード
   */
  def fromGzip(file: String, enc:String = "UTF-8" ): BufferedSource = {
    io.Source.fromInputStream(
      new GZIPInputStream(
        new BufferedInputStream(
          new FileInputStream(file)
        )
      )
      ,enc )
  }
}
