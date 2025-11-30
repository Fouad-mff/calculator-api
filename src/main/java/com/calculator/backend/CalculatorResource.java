/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calculator.backend;

// Jakarta RESTful Web Services (JAX-RS) API
import jakarta.ws.rs.GET; 
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// The exp4j external library is used for mathematical expression evaluation
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * CalculatorResource is a Jakarta RESTful Web Service endpoint for evaluating
 * mathematical expressions.
 * * @author Merad Fouad
 */
// The @Path annotation defines the relative URI path for this resource class.
// The full path will typically be /<context-root>/api/calculate
@Path("calculate") 
public class CalculatorResource {

    // The @GET annotation maps this method to HTTP GET requests.
    // The @Produces annotation specifies that the method's output format is plain text.
    @GET 
    @Produces(MediaType.TEXT_PLAIN) 
    public Response evaluateExpression(
        // The @QueryParam annotation binds the value of the 'expression' query parameter
        // from the request URL (e.g., ?expression=25*15) to this String variable.
        @QueryParam("expression") String expression) {
        
        // --- Input Validation ---
        
        // Check if the expression is null or contains only whitespace.
        if (expression == null || expression.trim().isEmpty()) {
            // Return an HTTP 400 Bad Request error if the expression is empty          
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("The expression cannot be empty.").build();
        }

        try {
            // --- Evaluation using exp4j ---
            
            // Creates an Expression instance from the input string using ExpressionBuilder.
            // This step parses the expression and checks its structure.
            Expression exp = new ExpressionBuilder(expression).build();
            
            // Evaluates the parsed expression and calculates the numerical result.
            double result = exp.evaluate();
            
            // --- Success Response (Formatting) ---
            
            String formattedResult;

            // Check if the double result is mathematically equivalent to an integer 
            // (e.g., 110.0) and is not infinite (which can happen with division by zero).
            if (result == Math.floor(result) && !Double.isInfinite(result)) {
                // If it's an integer, convert it to a long integer to suppress the ".0" display.
                formattedResult = String.valueOf((long) result);
            } else {
                // Otherwise, keep the double format (for results like 27.5).
                formattedResult = String.valueOf(result);
            }

            // Return the formatted result as a plain string with an HTTP 200 OK status code.
            return Response.ok(formattedResult).build();
            
        } catch (IllegalArgumentException e) {
            // exp4j throws this exception for syntax errors in the expression.
            // Return an HTTP 400 Bad Request status code.
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Syntax error in the expression.").build();
        } catch (Exception e) {
            // Handles any other unexpected exceptions, such as division by zero.
            // Return an HTTP 500 Internal Server Error status code.
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An unexpected error occurred.").build();
        }
    }
}