package com.gabriel.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		/*out.println(o.toString());*/
		out.write(o.toString());
		out.flush();
		out.close();
	}
}
