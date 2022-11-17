package com.ldap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;

import com.exception.ServiciosException;


public class ActiveDirectoryConnect {

	private Hashtable<String, String> env;
	public static final String CONFIGURACION = "/com/properties/ldap.properties";
	
	
	public ActiveDirectoryConnect() throws IOException {
		this.env =  new Hashtable<String, String>();
		Properties conf = new Properties();
		InputStream in = getClass().getResourceAsStream(ActiveDirectoryConnect.CONFIGURACION);
		conf.load(in);
		env.put(Context.INITIAL_CONTEXT_FACTORY, conf.getProperty("package"));
        env.put(Context.PROVIDER_URL, conf.getProperty("url"));
        env.put(Context.SECURITY_AUTHENTICATION, conf.getProperty("seguridad"));
   
	}
	

	public void userAutenticar(String user, String token) throws NamingException, ServiciosException {
		 env.put(Context.SECURITY_PRINCIPAL, user);
         env.put(Context.SECURITY_CREDENTIALS, token);
         try {
        	 new InitialDirContext(env);
        	 
		} catch (AuthenticationException e) {
		
			e.printStackTrace();
			throw new ServiciosException(e.getMessage());
		}
	}
	
	

}