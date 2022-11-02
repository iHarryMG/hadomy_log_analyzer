package net.daum.view.controller;

import java.sql.SQLException;
import java.util.List;
import net.daum.view.model.Category;
import net.daum.view.model.Date;
import net.daum.view.model.LogData;
import net.daum.view.model.Tab;
import net.daum.view.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/list.htm")
@SessionAttributes("logData")
public class LogController {
	
	@Autowired
	private LogService logService;

	@ModelAttribute("categoryList")
	public List<Category> populateCategoryList() throws SQLException{
		return logService.getAllCategories();
	}
	
	@ModelAttribute("tabList")
	public List<Tab> populateTabList() throws SQLException{
		return logService.getAllTabs();
	}
	
	@ModelAttribute("dateList")
	public List<Date> populateDateList() throws SQLException{
		return logService.getAllDate();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showListForm(ModelMap modelMap){
		LogData logData = new LogData();
		modelMap.addAttribute("logData", logData);
		return "listForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(@ModelAttribute("logData")LogData logData) throws SQLException{
		List<LogData>logs = logService.getAllList(logData);
		return new ModelAndView("listForm", "logs", logs);
	}
	
}
