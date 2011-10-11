package org.openmrs.module.restrictbyenctype.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.EncounterType;
import org.openmrs.Role;
import org.openmrs.api.EncounterService;
import org.openmrs.api.context.Context;
import org.openmrs.module.restrictbyenctype.EncTypeRestriction;
import org.openmrs.module.restrictbyenctype.EncTypeRestrictionConstants;
import org.openmrs.module.restrictbyenctype.RestrictByRoleService;
import org.openmrs.module.restrictbyenctype.RolePropertyEditor;
import org.openmrs.web.WebConstants;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

public class EncTypeRestrictionFormController extends SimpleFormController {
	Log log = LogFactory.getLog(getClass());
	
	/**
	 * Allows for Integers to be used as values in input tags. Normally, only
	 * strings and lists are expected
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(request, binder);

		binder.registerCustomEditor(java.lang.Integer.class,
				new CustomNumberEditor(java.lang.Integer.class, true));
		binder.registerCustomEditor(java.lang.Double.class,
				new CustomNumberEditor(java.lang.Double.class, true));
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
				Context.getDateFormat(), true));
		binder.registerCustomEditor(Role.class, new RolePropertyEditor());
		binder.registerCustomEditor(java.lang.Boolean.class,
				new CustomBooleanEditor(EncTypeRestrictionConstants.TRUE,EncTypeRestrictionConstants.FALSE,true));
		binder.registerCustomEditor(Set.class, "encTypes",new CustomCollectionEditor(Set.class){
			EncounterService encService = Context.getEncounterService();
			protected Object convertElement(Object element)
			    {
				  Integer encounterTypeId = null;
			      if (element instanceof Integer)
			    	  encounterTypeId = (Integer) element;
			      else if (element instanceof String)
			    	  encounterTypeId = Integer.valueOf((String) element);
			      return encounterTypeId != null ? encService.getEncounterType(encounterTypeId) : null;
			    }
		});
	}

	/**
	 * This is called prior to displaying a form for the first time. It tells
	 * Spring the form/command object to load into the request
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException {

		EncTypeRestriction encTypeRestriction = null;

		RestrictByRoleService service = (RestrictByRoleService) Context
				.getService(RestrictByRoleService.class);
		String restrictionId = request.getParameter("restrictionId");
		if (restrictionId != null)
			encTypeRestriction = service.getEncTypeRestriction(Integer
					.valueOf(restrictionId));

		if (encTypeRestriction == null)
			encTypeRestriction = new EncTypeRestriction();
		return encTypeRestriction;
	}

	@Override
	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		RestrictByRoleService service = (RestrictByRoleService) Context
		.getService(RestrictByRoleService.class);
		EncTypeRestriction encTypeRestriction = (EncTypeRestriction) command;

		if (encTypeRestriction.getDescription() == null
				|| encTypeRestriction.getDescription().trim().length() == 0) {
			errors.reject("error.description.invalid", "Description value is invalid");
		}

		Integer roleRestrictionId = encTypeRestriction.getRoleRestrictionId();
		if (roleRestrictionId == null) {
			// Add new
			if (service.getEncTypeRestrictionByDescription(encTypeRestriction.getDescription()) != null) {
				errors
						.reject("error.description.existed",
								"This description already existed");
			}
		} else {
			// Update
//			EncTypeRestriction dbEncTypeRestriction = service.getEncTypeRestriction(roleRestrictionId);
//			
//			if (!dbEncTypeRestriction.getDescription()
//					.equalsIgnoreCase(encTypeRestriction.getDescription())) {
//				if (service.getEncTypeRestrictionByDescription(encTypeRestriction.getDescription()) != null) {
//					errors.reject("error.description.existed",
//					"This description already existed");
//				}
//			}
		}

		super.onBindAndValidate(request, command, errors);
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		return super.onSubmit(request, response, command, errors);

	}

	@Override
	protected ModelAndView processFormSubmission(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		if (errors.hasErrors()) {
			log.error("has Error : " + errors.getAllErrors());
			return super.processFormSubmission(request, response, command,
					errors);
		}
		HttpSession httpSession = request.getSession();
		RestrictByRoleService service = (RestrictByRoleService) Context
		.getService(RestrictByRoleService.class);
		EncTypeRestriction encTypeRestriction = (EncTypeRestriction) command;
		
		if (encTypeRestriction.getRoleRestrictionId() == null) {
			encTypeRestriction.setCreator(Context.getAuthenticatedUser());
			encTypeRestriction.setDateCreated(new Date());
		}

		if (encTypeRestriction.getVoided()) {
			encTypeRestriction.setVoidedBy(Context.getAuthenticatedUser());
			encTypeRestriction.setDateVoided(new Date());
		}
		if(encTypeRestriction.getRoleRestrictionId() == null){
			service.createEncTypeRestriction(encTypeRestriction);
		}else{
			service.updateEncTypeRestriction(encTypeRestriction);
		}

		
		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
				"encTypeRestriction.saved");
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
	
	/**
	 * Returns any extra data in a key-->value pair kind of way
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request,
			Object obj, Errors err) throws Exception {

		List<EncounterType> listEncTypes = Context.getEncounterService().getAllEncounterTypes();
		List<Role> roles = (List<Role>) Context.getUserService().getAllRoles();
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("listEncTypes", listEncTypes);
		map.put("roles", roles);
		return map;
	}

}
