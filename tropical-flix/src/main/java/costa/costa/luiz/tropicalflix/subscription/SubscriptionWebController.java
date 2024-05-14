package costa.costa.luiz.tropicalflix.subscription;

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

    public SubscriptionWebController(SubscriptionService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView subscribe() {
        ModelAndView modelAndView = new ModelAndView("subscribe");
        modelAndView.addObject("subscription", new StartSubscriptionRequest(null,
                null, "42,99", "3 meses", "Brasil"));
        return modelAndView;
    }
    @PostMapping("/start")
    public String startSubscription(
            @ModelAttribute("subscription") StartSubscriptionRequest request,
            RedirectAttributes attributes) {
        service.triggerSubscriptionWorkflow(request);
        attributes.addFlashAttribute("message", "Esperando confirmação da instituição financeira");
        return "redirect:/ui/subscribe";
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelSubscription() {
        throw new IllegalStateException("No no, you can't cancel the best streaming ever ;)");
    }

}
