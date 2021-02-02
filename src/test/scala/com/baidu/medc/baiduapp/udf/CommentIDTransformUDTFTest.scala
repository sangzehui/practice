package com.baidu.medc.baiduapp.udf

import szh.common.MyAssert

;


object CommentIDTransformUDTFTest {
  def main(args: Array[String]): Unit = {
    val udtf = new CommentIDTransformUDTF();
    val source =
      """
        |[{
        |    "1115444211489266612": ["1115444345196623117", "1115444318256714416"]
        |}, {
        |    "1115444234821975214": ["1115444408919549011", "1115444494473420317"]
        |},
        |{
        | "1115444440234013815": []
        |}
        |]
        |""".stripMargin;
    val target =
      """[
        |[{
        |		"order": 1,
        |		"level": 1,
        |		"id": "1115444211489266612"
        |	},
        |	{
        |		"order": 1,
        |		"level": 2,
        |		"id": "1115444345196623117"
        |	},
        |	{
        |		"order": 1,
        |		"level": 2,
        |		"id": "1115444318256714416"
        |	},
        |	{
        |		"order": 2,
        |		"level": 1,
        |		"id": "1115444234821975214"
        |	},
        |	{
        |		"order": 1,
        |		"level": 2,
        |		"id": "1115444408919549011"
        |	},
        |	{
        |		"order": 1,
        |		"level": 2,
        |		"id": "1115444494473420317"
        |	},
        |	{
        |		"order": 1,
        |		"level": 1,
        |		"id": "1115444440234013815"
        |	}
        |]
        |""".stripMargin;
    val res = udtf.process(Array(source));
    println(res)
    MyAssert.equals(target, res)
  }
}
