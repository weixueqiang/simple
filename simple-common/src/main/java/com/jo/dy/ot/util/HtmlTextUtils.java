package com.jo.dy.ot.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.util.HtmlUtils;
/**
 * HTML 转换为文本
 * @author  weixueqiang
 * @version 1.0.0
 * @date 2018年8月7日 下午5:35:22
 */
public class HtmlTextUtils {

	/**
	 *	将html转换为存文本,HtmlUtils.htmlEscape的效果也行
	 * @param html
	 * @return
	 * @date 2018年3月12日 下午3:02:39
	 * @author 魏雪强
	 */
	public static String simpleGet(String html) {
		if(StringUtils.isBlank(html)) {
			return "";
		}
		Document document = Jsoup.parse(html);
		if(document==null) {
			return "";
		}
		return document.text();
	}
	
	/**
	 * 获取允许长度的html纯文本
	 * @date 2018年8月7日 下午5:25:50
	 * @author weixueqiang
	 */
	public static String getByLength(String html,int length) {
		if(length<=0) {
			return "";
		}
		String simpleGet = simpleGet(html);
		if(simpleGet.length()<=length) {
			return simpleGet;
		}
		return simpleGet.substring(0, length);
	}
	
	
	
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
    	sb.append(" <meta name=\"keywords\" content=\"Android,String\" />\r\n" + 
    			"    <meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" /><link rel=\"alternate\" media=\"handheld\" href=\"#\" />\r\n" + 
    			"    <meta name=\"shenma-site-verification\" content=\"5a59773ab8077d4a62bf469ab966a63b_1497598848\">\r\n" + 
    			"    <script src=\"https://csdnimg.cn/release/phoenix/vendor/tingyun/tingyun-rum-blog.js\"></script>\r\n" + 
    			"\r\n" + 
    			"    <link href=\"https://csdnimg.cn/public/favicon.ico\" rel=\"SHORTCUT ICON\">\r\n" + 
    			"    <title>String 、InputStream、Reader <><=1 之间的转换 - CSDN博客</title>\r\n" + 
    			"    \r\n" + 
    			"            <link rel=\"stylesheet\" href=\"https://csdnimg.cn/release/phoenix/template/css/detail-23915aa032.min.css\">\r\n" + 
    			"        <link rel=\"stylesheet\" href=\"https://csdnimg.cn/release/phoenix/themes/skin3-template/skin3-template-8195ccbe2d.min.css\">\r\n" + 
    			"\r\n" + 
    			"    <script type=\"text/javascript\">\r\n" + 
    			"        var username = \"u014649598\";\r\n" + 
    			"        var blog_address = \"https://blog.csdn.net/u014649598\";\r\n" + 
    			"        var static_host = \"https://csdnimg.cn/release/phoenix/\";\r\n" + 
    			"        var currentUserName = \"\"; \r\n" + 
    			"        var isShowAds = true;\r\n" + 
    			"        var isOwner = false;\r\n" + 
    			"        var loginUrl = \"https://passport.csdn.net/account/login?from=https://blog.csdn.net/u014649598/article/details/53944796\"\r\n" + 
    			"        var blogUrl = \"https://blog.csdn.net/\";\r\n" + 
    			"        //页面皮肤样式\r\n" + 
    			"        var curSkin = \"skin3-template\";\r\n" + 
    			"    </script>");
		
		String html=simpleGet(sb.toString());
		String hh= getByLength(sb.toString(), 1);
		System.out.println(html);
		System.out.println(html.length());
		System.out.println(hh);
    	String s="<h1>我是中文呀</h1><script>你不支持</script>";
    	
		String html1 = HtmlUtils.htmlEscape(sb.toString());
		System.out.println(html);
		 html = HtmlUtils.htmlEscapeDecimal(s);//
		System.out.println(html);
		 html = HtmlUtils.htmlEscapeHex(s);
		System.out.println(html);
		 html = HtmlUtils.htmlUnescape(s);
		System.out.println(html);
		
	}
	
}
