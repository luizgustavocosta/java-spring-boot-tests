package com.costa.luiz.tropicalflix.shared;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

class DefaultThymeleafPaginationTest {

    // Helped by Google AI Studio
    private final ThymeleafPagination thymeleafPagination = spy(ThymeleafPagination.class);

    @Test
    void testAddPaginationToView() {
        String viewName = "testView";
        int page = 2;
        long totalItems = 25;
        int pageSize = 10;
        String objectName = "testObject";
        Object objectValue = new Object();

        ModelAndView modelAndView = thymeleafPagination.addPaginationToView(viewName, page, totalItems, pageSize, objectName, objectValue);

        assertEquals(viewName, modelAndView.getViewName());
        assertEquals(page, modelAndView.getModel().get("currentPage"));
        assertEquals(totalItems, modelAndView.getModel().get("totalItems"));
        assertEquals(3L, modelAndView.getModel().get("totalPages")); // 25 items / 10 per page = 2.5 -> 3 pages
        assertEquals(pageSize, modelAndView.getModel().get("pageSize"));
        assertEquals(objectValue, modelAndView.getModel().get(objectName));
    }

    @Test
    void testAddPaginationToView_ExactPages() {
        // Test the case where totalItems is an exact multiple of pageSize
        String viewName = "testView";
        int page = 1;
        long totalItems = 20;
        int pageSize = 10;
        String objectName = "testObject";
        Object objectValue = new Object();

        ModelAndView modelAndView = thymeleafPagination.addPaginationToView(viewName, page, totalItems, pageSize, objectName, objectValue);

        assertEquals(2L, modelAndView.getModel().get("totalPages")); // 20 items / 10 per page = 2 pages
    }
}