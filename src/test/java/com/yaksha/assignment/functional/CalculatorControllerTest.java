package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import com.yaksha.assignment.controller.CalculatorController;
import com.yaksha.assignment.utils.CustomParser;

public class CalculatorControllerTest {

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCalculatorControllerWithCorrectModel() throws Exception {
		// Mock HttpServletRequest and Model
		HttpServletRequest request = mock(HttpServletRequest.class);
		Model model = mock(Model.class);

		// Mock URL parameters and behavior
		when(request.getParameter("num1")).thenReturn("5");
		when(request.getParameter("num2")).thenReturn("10");

		// Create controller instance
		CalculatorController controller = new CalculatorController();

		// Call method with mocked request and model
		String viewName = controller.calculateSum(5, 10, model);

		// Verify the view returned is "result"
		boolean isResultViewReturned = "result".equals(viewName);

		// Verify model contains the sum attribute
		verify(model).addAttribute("sum", 15);

		// Auto-grading with yakshaAssert
		yakshaAssert(currentTest(), isResultViewReturned, businessTestFile);
	}

	@Test
	public void testJspTagsAndHtmlTagClosureInIndexJsp() throws Exception {
		String filePath = "src/main/webapp/index.jsp";

		// Check for form submission and input elements in index.jsp
		boolean hasFormTag = CustomParser.checkJspTagPresence(filePath, "<form");
		boolean hasInputTags = CustomParser.checkJspTagPresence(filePath, "<input");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(), hasFormTag && hasInputTags, businessTestFile);
	}

	@Test
	public void testJspTagsAndHtmlTagClosureInResultJsp() throws Exception {
		String filePath = "src/main/webapp/WEB-INF/views/result.jsp";

		// Ensure that result.jsp correctly displays the sum
		boolean hasSumTag = CustomParser.checkJspTagPresence(filePath, "${sum}");

		// Run auto-grading using yakshaAssert
		yakshaAssert(currentTest(), hasSumTag, businessTestFile);
	}
}
