package rzk.interceptor;

import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import model.Blog;
import rzk.KategorijeAndStuff;

public class BrojPregledaInterceptor {
	@EJB
	KategorijeAndStuff k;
	
	@AroundInvoke
	public Object InterceptPregleda(InvocationContext ctx) throws Exception{
		List<Blog> blogovi =(List<Blog>) ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
		k.azurirajBrojPregleda(blogovi);
		return ctx.proceed();
	}

}
