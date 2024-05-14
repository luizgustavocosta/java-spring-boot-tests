package costa.costa.luiz.tropicalflix.shared;

import org.springframework.web.servlet.ModelAndView;

public interface ThymeleafPagination {

    default ModelAndView addPaginationToView(String viewName, int page, long totalItens, int pageSize,
                                             String objectName, Object objectValue) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalItems", totalItens);
        modelAndView.addObject("totalPages", totalItens % pageSize == 0
                ? (totalItens / pageSize)
                : (totalItens / pageSize) + 1);
        modelAndView.addObject("pageSize", pageSize);
        modelAndView.addObject(objectName, objectValue);
        return modelAndView;
    }
}
