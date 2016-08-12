package ru.andrunov;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 20.07.2016.
 */
@Controller
public class UserController {

    private final int recordsPerPage = 10;
    private int currentPage = 1;
    private final Filters filters = new Filters();
    private final UserHolder userHolder = new UserHolder();


    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String showUsers(@RequestParam(value = "page", required = false) Integer page, ModelMap model){
        int numberOfPages = (int)Math.ceil(userHolder.countRecords(filters)*1.0/recordsPerPage);
        if (numberOfPages == 0){
            numberOfPages = 1;
        }
        if (currentPage > numberOfPages){
            currentPage = numberOfPages;
        }
        if (page == null) {
            page = currentPage;
        }
        else {
            currentPage = page;
        }
        model.addAttribute("filters", filters);
        model.addAttribute("items", userHolder.values((page-1)*recordsPerPage,recordsPerPage, filters));
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("currentPage", page);
        return "index";
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public String save(ModelMap model){
        return "redirect:users.do";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editView(@RequestParam int id, ModelMap model) {
        model.addAttribute("user", userHolder.findById(id));
        return "updateUser";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("User") User user) {
        userHolder.update(user);
        return "redirect:/users.do";
    }

    @RequestMapping(value = "/editFilters", method = RequestMethod.GET)
    public String filtersView(ModelMap model) {
        model.addAttribute("filters", filters);
        return "filters";
    }

    @RequestMapping(value = "/editFilters", method = RequestMethod.POST)
    public String filtersApply(@ModelAttribute("Filters") Filters filters) {
        this.filters.setNameFilter(filters.getNameFilter());
        return "redirect:/users.do";
    }

    @RequestMapping(value = "/deleteFilters", method = RequestMethod.GET)
    public String filtersDelete() {
        this.filters.setNameFilter(null);
        return "redirect:/users.do";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView(ModelMap model) {
        model.addAttribute("user", new User());
        return "createUser";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("User") User user) {
        userHolder.create(user);
        int numberOfPages = (int)Math.ceil(userHolder.countRecords(filters)*1.0/recordsPerPage);
        return "redirect:/users.do?page=" + numberOfPages;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int id) {
        userHolder.delete(id);
        return "redirect:/users.do";
    }

    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public String fillBase() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String[] userTemplates = new UsersHolderFiller().getFiller();
        for (String template : userTemplates){
            String[] userTemplate = template.split("/");
            User user = new User();
            user.setName(userTemplate[0]);
            user.setAge(Integer.parseInt(userTemplate[1]));
            user.setIsAdmin(Integer.parseInt(userTemplate[2]));
            try {
                user.setCreated(format.parse(userTemplate[3]));
            }
            catch (ParseException e){e.printStackTrace();}
            userHolder.create(user);
        }
        int numberOfPages = (int)Math.ceil(userHolder.countRecords(filters)*1.0/recordsPerPage);
        return "redirect:/users.do?page=" + numberOfPages;
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clearBase() {
        Filters filters = new Filters();
        List<User> users = userHolder.values(0,userHolder.countRecords(filters),filters);
        for (User user : users){
            userHolder.delete(user.getId());
        }
        return "redirect:/users.do";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
