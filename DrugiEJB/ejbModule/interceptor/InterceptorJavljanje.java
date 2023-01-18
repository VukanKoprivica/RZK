package interceptor;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import bean.Statistika;

public class InterceptorJavljanje {
	
	@EJB
	Statistika stat;
	
	@AroundInvoke
	public Object presretni(InvocationContext ctx) throws Exception {
		System.out.println("Uspesno presretnut");
		stat.azurirajJavljanja();
		return ctx.proceed();
	}

}
