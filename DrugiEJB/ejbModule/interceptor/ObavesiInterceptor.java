package interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ObavesiInterceptor {

	
	@AroundInvoke 
	public Object obavesti(InvocationContext ctx)throws Exception{
		System.out.println("Pozvana metoda"+ctx.getMethod());
		return ctx.proceed();
	}
}
