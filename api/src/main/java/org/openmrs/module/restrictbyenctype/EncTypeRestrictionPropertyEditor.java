package org.openmrs.module.restrictbyenctype;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;

public class EncTypeRestrictionPropertyEditor extends PropertyEditorSupport {

private Log log = LogFactory.getLog(this.getClass());
	
	public EncTypeRestrictionPropertyEditor() {
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		RestrictByRoleService restrictByRoleService = (RestrictByRoleService) Context
		.getService(RestrictByRoleService.class);
		if (text != null && text.trim().length() > 0 ) {
			try {
				setValue(restrictByRoleService.getEncTypeRestriction(Integer.valueOf(text)));
			}
			catch (Exception ex) {
				log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("STock not found: " + ex.getMessage());
			}
		} else {
			setValue(null);
		}
	}
	
	public String getAsText() {
		EncTypeRestriction etr = (EncTypeRestriction) getValue();
		if (etr == null ) {
			return null; 
		} else {
			return etr.getRoleRestrictionId()+"";
		}
	}
}
