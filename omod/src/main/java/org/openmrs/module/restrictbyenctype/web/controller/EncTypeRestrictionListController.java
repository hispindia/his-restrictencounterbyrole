package org.openmrs.module.restrictbyenctype.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.restrictbyenctype.EncTypeRestriction;
import org.openmrs.module.restrictbyenctype.RestrictByRoleService;
import org.openmrs.module.restrictbyenctype.paging.PagingUtil;
import org.openmrs.module.restrictbyenctype.paging.RequestUtil;
import org.openmrs.web.WebConstants;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class EncTypeRestrictionListController extends SimpleFormController {
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * Returns any extra data in a key-->value pair kind of way
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	protected Map<String, Object> referenceData(HttpServletRequest request,
			Object obj, Errors err) throws Exception {
		RestrictByRoleService encTypeRestrictionService = (RestrictByRoleService) Context
		.getService(RestrictByRoleService.class);
		int defaultPageSize = 20;
		String sPageSize = request.getParameter("pageSize");
		String sCurrentPage = request.getParameter("currentPage");
		int pageSize = sPageSize == null ? defaultPageSize : Integer.parseInt(sPageSize);
		int currentPage = sCurrentPage == null ? 0 : Integer.parseInt(sCurrentPage);
		PagingUtil pagingUtil = new PagingUtil( RequestUtil.getCurrentLink(request) , pageSize );
		pagingUtil.setCurrentPage( currentPage );
        pagingUtil.setTotal( encTypeRestrictionService.countEncTypeRestrictions() );
		List<EncTypeRestriction> listEncTypeRestriction = encTypeRestrictionService.listlistEncTypeRestriction(pagingUtil.getStartPos(), pagingUtil.getPageSize());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listEncTypeRests", listEncTypeRestriction);
		map.put("pagingUtil", pagingUtil);
		return map;
	}

	/**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object object,
			BindException exceptions) throws Exception {
		HttpSession httpSession = request.getSession();
		Integer encTypeRestId  = null;
		try{
			RestrictByRoleService encTypeRestrictionService = (RestrictByRoleService) Context
			.getService(RestrictByRoleService.class);

			String[] encTypeResIds = request.getParameterValues("encTypeRes");
			if( encTypeResIds != null && encTypeResIds.length > 0 ){
				for(String eId : encTypeResIds )
				{
					encTypeRestId = Integer.parseInt(eId);
					EncTypeRestriction encTypeRestriction = encTypeRestrictionService.getEncTypeRestriction(encTypeRestId);
					if( encTypeRestriction != null )
					{
						encTypeRestrictionService.deleteEncTypeRestriction(encTypeRestriction);
					}else{
						exceptions.reject("restrictbyenctype.notfound", "Can not find encounter type Restriction with id: "+encTypeRestId);
						return showForm(request, response, exceptions);
					}
				}
			}
			
			
		}catch (Exception e) {
			exceptions.reject("restrictbyenctype.cannotdelete", "Can not delete encounter type Restriction with id: "+encTypeRestId);
			log.error(e);
			return showForm(request, response, exceptions);
		}
		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
		"restrictbyenctype.deleted");
		return showForm(request, response, exceptions);
	}

	/**
	 * This class returns the form backing object. This can be a string, a
	 * boolean, or a normal java pojo. The type can be set in the
	 * /config/moduleApplicationContext.xml file or it can be just defined by
	 * the return type of this method
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		return new Object();
	}
}
