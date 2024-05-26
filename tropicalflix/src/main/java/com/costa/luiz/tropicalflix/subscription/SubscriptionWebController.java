package com.costa.luiz.tropicalflix.subscription;

import com.costa.luiz.tropicalflix.shared.UIException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ui/subscribe")
class SubscriptionWebController {

    private final SubscriptionService service;

    SubscriptionWebController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView subscribe() {
        var modelAndView = new ModelAndView("subscribe");
        modelAndView.addObject("subscription",
                new StartSubscriptionRequest(
                null, "42,00", "3 meses", "Brasil"));
        return modelAndView;
    }

    @PostMapping("/start")
    String startSubscription(
            @ModelAttribute("subscription") StartSubscriptionRequest request,
            RedirectAttributes attributes) {
        try {
            service.triggerSubscriptionWorkflow(request);
            attributes.addFlashAttribute("message", "Esperando confirmação da instituição financeira");
            return "redirect:/ui/subscribe";
        } catch (Exception exception) {
            throw new UIException(exception);
        }
    }

    @PostMapping("/cancel")
    ResponseEntity<String> cancelSubscription() {
        throw new IllegalStateException("No no, you can't cancel the best streaming ever ;)");
    }

}
