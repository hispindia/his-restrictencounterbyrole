package org.openmrs.module.restrictbyenctype.advice;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.Patient;
import org.openmrs.Role;
import org.openmrs.api.EncounterService;
import org.openmrs.api.context.Context;
import org.openmrs.module.restrictbyenctype.EncTypeRestriction;
import org.openmrs.module.restrictbyenctype.RestrictByRoleService;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

@SuppressWarnings("serial")
public class EncounterGetterAdvisor extends StaticMethodMatcherPointcutAdvisor implements Advisor  {

	private Log log = LogFactory.getLog(this.getClass());
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean matches(Method method, Class targetClass) {
		String methodName = method.getName();
		return methodName.equals("getEncountersByPatient") || methodName.equals("getEncountersByPatientId");
	}

	@Override
	public Advice getAdvice() {
		log.debug("Getting EncounterService.getEncounters() around advice");
		return new EncounterAroundAdvice();
	}
	
	private class EncounterAroundAdvice implements MethodInterceptor {
		@SuppressWarnings("unchecked")
		public Object invoke(MethodInvocation invocation) throws Throwable {
			
			RestrictByRoleService service = (RestrictByRoleService) Context.getService(RestrictByRoleService.class);
			
			EncounterService encService = Context.getEncounterService();
			
			log.warn("Before " + invocation.getMethod().getName() + ".");
			
			Object[] args = invocation.getMethod().getParameterTypes();

			Iterator it = Context.getAuthenticatedUser().getRoles().iterator();
			
			Patient patient = new Patient();
			
			Set<EncounterType> encTypes = new HashSet<EncounterType>();
			args = invocation.getArguments();
			
			patient = (Patient) args[0];
			
			while(it.hasNext()){
				Role role = (Role) it.next();
				EncTypeRestriction typeRes = service.getEncTypeRestrictionForRole(role);
				if(typeRes!=null){
					encTypes.addAll(typeRes.getEncTypes());	
				}
				
			}
			
			List<Encounter> encs = encService.getEncounters(patient, null, null, null, null, encTypes, null, false);
			
			// the proceed() method does not have to be called
			//Object o = invocation.proceed();
			
			
			log.warn("After " + invocation.getMethod().getName() + ".");
			
			return encs;//o;
		}
	}
}
