package com.szy.local;

import com.szy.cache.Session;

public class LocalUtil {
	private final static ThreadLocal<Local> locals = new ThreadLocal<>();
	public static void setSession(Session session){
		Local local = locals.get();
		if(local == null){
			local = new Local();
			locals.set(local);
		}
		local.setSession(session);
	}
	
	public static Session getSession(){
		Local local = locals.get();
		if(local == null){
			return null;
		}
		return local.getSession();
	}
	
}
