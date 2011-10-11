package org.openmrs.module.restrictbyenctype;

import org.openmrs.api.APIAuthenticationException;

public class RestrictByRoleAuthorizationException extends APIAuthenticationException {

	private static final long serialVersionUID = 1L;

	public RestrictByRoleAuthorizationException() {
		super();
	}
	
	public RestrictByRoleAuthorizationException(String message) {
		super(message);
	}
	
	public RestrictByRoleAuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RestrictByRoleAuthorizationException(Throwable cause) {
		super(cause);
	}
	
}
