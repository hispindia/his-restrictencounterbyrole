package org.openmrs.module.restrictbyenctype;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Role;
import org.openmrs.api.context.Context;

public class RolePropertyEditor extends PropertyEditorSupport {

private Log log = LogFactory.getLog(this.getClass());
	
	public RolePropertyEditor() {
	}
	
	public void setAsText(String text) throws IllegalArgumentException {

		if (text != null && text.trim().length() > 0 ) {
			try {
				setValue(Context.getUserService().getRole(text));
			}
			catch (Exception ex) {
				log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("Role not found: " + ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}
	
	public String getAsText() {
		Role r = (Role) getValue();
		if (r == null ) {
			return null; 
		} else {
			return r.getRole()+"";
		}
	}
}
