/*
 * Created on Sep 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.adara.pixeldataengineui.util;

import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * @author hmei
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExceptionUtil {
	static public String getStack(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
	
	public static void main(String[] args) {
		try{
			String s = null;
			s.toString();
		}catch(Exception e){
		  System.out.println(getStack(e));
		}
	}
}
