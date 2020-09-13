package com.ds.springbootfirstwebapplication.controller;

import com.ds.springbootfirstwebapplication.model.Todo;
import com.ds.springbootfirstwebapplication.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//@SessionAttributes("name") - now get using spring security
public class TodoController {

    @Autowired
    TodoService todoService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodoList(ModelMap model){
        String userName = getLoggedInUserName(model);
        model.put("todos",todoService.retrieveTodos(userName));
        return "list-todos";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodo(ModelMap model){
        model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "Default Desc",
                new Date(), false));
        return "add-todo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo( ModelMap model,@Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "add-todo";
        }

        todoService.addTodo(getLoggedInUserName(model),todo.getDesc(),todo.getTargetDate(), false);
        model.put("todos",todoService.retrieveTodos(getLoggedInUserName(model)));
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id){

        if(id ==1) {
            throw new RuntimeException("Exception Occured");
        }

        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.retrieveTodo(id);
        model.put("todo", todo);
        return "add-todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "add-todo";
        }

        todo.setUser(getLoggedInUserName(model));

        todoService.updateTodo(todo);

        return "redirect:/list-todos";
    }


}
