package com.costa.luiz.tropicalflix.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class TropicalFlixController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("covers", IntStream.rangeClosed(1, 16)
                .boxed()
                .map(index -> "/imgs/covers/" + index + ".jpg")
                .toList());
        return model;
    }
}
