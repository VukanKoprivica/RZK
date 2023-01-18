package rzk.interceptor;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import rzk.KategorijeAndStuff;

public class BrojPostavljenihInterceptor {

	@EJB
	KategorijeAndStuff k;
	
	
	@AroundInvoke
	public Object presretniPreglede(InvocationContext ctx) throws Exception {

		k.povecajBrojPostavljenih();

		
		return ctx.proceed();
	}
}
