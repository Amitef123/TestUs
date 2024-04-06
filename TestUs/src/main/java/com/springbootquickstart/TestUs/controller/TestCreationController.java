package com.springbootquickstart.TestUs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootquickstart.TestUs.dto.TestCreationDto;
import com.springbootquickstart.TestUs.test.TestService;

@Controller
public class TestCreationController {
    @Autowired
    private TestService testService;
    private TestCreationDto testCreationDto = new TestCreationDto();

    public TestCreationController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/teacher/create-test")
    public String showCreateTestForm(@ModelAttribute("test") TestCreationDto test) {
        return "create-test";
    }

    @PostMapping("/teacher/create-test")
    public String createTest(@ModelAttribute("test") TestCreationDto test,
            RedirectAttributes redirectAttributes) {
        testService.createTest(test);
        redirectAttributes.addFlashAttribute("successMessage", "Test created successfully!");
        return "redirect:/teacher/create-test";
    }

    @PostMapping("/teacher/add-question")
    public String addQuestion(String questionType, RedirectAttributes redirectAttributes) {
        // Redirect to the appropriate page based on the selected question type
        if ("american".equals(questionType)) {
            return "redirect:/teacher/create-american-question";
        } else if ("truefalse".equals(questionType)) {
            return "redirect:/teacher/create-truefalse-question";
        } else {
            // Invalid question type, handle accordingly
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid question type selected.");
            return "redirect:/teacher/create-test";
        }
    }

    @PostMapping("/teacher/save-test")
    public String saveTest(RedirectAttributes redirectAttributes) {
        testService.createTest(testCreationDto); // Save the created test
        redirectAttributes.addFlashAttribute("successMessage", "Test created successfully!");
        return "redirect:/teacher/create-test";
    }
}