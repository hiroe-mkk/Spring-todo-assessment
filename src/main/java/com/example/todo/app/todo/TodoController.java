package com.example.todo.app.todo;

import com.example.todo.common.exception.BusinessException;
import com.example.todo.common.message.ResultMessage;
import com.example.todo.domain.model.todo.Todo;
import com.example.todo.domain.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

import static com.example.todo.app.todo.TodoForm.*;

@Controller
@RequestMapping("todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @ModelAttribute
    public TodoForm setUpForm() {
        return new TodoForm();
    }

    @GetMapping("list")
    public String list(Model model) {
        Collection<Todo> todos = todoService.findAll();
        model.addAttribute("todos", todos);
        return "todo/list";
    }

    @PostMapping("create")
    public String create(@Validated({TodoCreate.class}) TodoForm todoForm, BindingResult bindingResult,
                         Model model, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return list(model);
        }

        Todo todo = todoForm.convertToTodo();
        try {
            todoService.create(todo);
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessage());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessage.success("Created successfully!"));
        return "redirect:/todo/list";
    }

    @PostMapping("finish")
    public String finish(@Validated({TodoFinish.class})
                         TodoForm form, BindingResult bindingResult,
                         Model model, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return list(model);
        }

        try {
            todoService.finish(form.getTodoId());
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessage());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessage.success("Finished successfully!"));
        return "redirect:/todo/list";
    }

    @PostMapping("delete")
    public String delete(@Validated({TodoDelete.class})
                         TodoForm form, BindingResult bindingResult,
                         Model model, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return list(model);
        }

        try {
            todoService.delete(form.getTodoId());
        } catch (BusinessException e) {
            model.addAttribute(e.getResultMessage());
            return list(model);
        }

        attributes.addFlashAttribute(ResultMessage.success("Deleted successfully!"));
        return "redirect:/todo/list";
    }
}
