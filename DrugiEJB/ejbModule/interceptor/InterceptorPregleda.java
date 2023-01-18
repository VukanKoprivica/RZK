package interceptor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import bean.Statistika;
import model.Ogla;

public class InterceptorPregleda {
	
	@EJB
	Statistika stat;
	
	@AroundInvoke
	public Object presretni(InvocationContext ctx) throws Exception {
		System.out.println("presretnut");
		List<Ogla> oglasi = (List<Ogla>)ctx.getMethod().invoke(ctx.getTarget(),ctx.getParameters());
		stat.azurirajBrojPregleda(oglasi);
		return ctx.proceed();
	}

}
