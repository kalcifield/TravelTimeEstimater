package com.codecool.app.controller;

import com.codecool.app.model.EmailComposer;
import com.codecool.app.model.MailConfig;
import com.codecool.app.model.MailContent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;


@Controller
public class WelcomeController {


    @RequestMapping("/")
    public String welcome(Model model) {

        model.addAttribute("mailcontent", new MailContent());
        return "welcome";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute MailContent mailContent) throws IOException {
        String time = EmailComposer.apiRequestHandler(mailContent.getLocation());
        MailConfig.sendMail(time);


        return "emailsent";
    }

}