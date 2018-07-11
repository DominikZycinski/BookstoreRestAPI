package com.example.demoNowe.Controller;

import com.example.demoNowe.Exception.ResourceNotFoundException;
import com.example.demoNowe.Model.Book;
import com.example.demoNowe.Service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dozyc1
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Get all books", notes = " Return all books")
    @ApiResponses(value = {
        @ApiResponse(code = 200,
                message = "Found all books")
        ,
            @ApiResponse(code = 204,
                message = "Books are not found")
    })
    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            if (bookService.getAllBooks().isEmpty()) {
                throw new ResourceNotFoundException();
            }
            return new ResponseEntity<List<Book>>(bookService.getAllBooks(), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Find Book by id", notes = "Returns Book based on ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200,
                message = "Found book by specified ID")
        ,
            @ApiResponse(code = 204,
                message = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        try {
            if (bookService.checkIfBookExists(id) == false) {
                throw new ResourceNotFoundException();
            } else {
                return new ResponseEntity<Book>(bookService.getBook(id),HttpStatus.OK);
            }
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    
    @ApiOperation(value = "Add a new book", notes = "Returns Id of added new book")
    @ApiResponses(value = {
        @ApiResponse(code = 200,
                message = "Added book")
        ,
            @ApiResponse(code = 204,
                message = "Book not added")
    })
    @PostMapping()
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookService.addBook(book);

        return new ResponseEntity<String>("Book is added to the bookstore with id " + book.getId(), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a book", notes = "Returns succes or id book that could not be updated")
    @ApiResponses(value = {
        @ApiResponse(code = 200,
                message = "Updated book")
        ,
            @ApiResponse(code = 204,
                message = "Book can not be updated")
    })

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@RequestBody Book book, @PathVariable Long id) {

        try {
            if (bookService.updateBook(id, book) == false) {
                throw new ResourceNotFoundException();
            } else {
                bookService.updateBook(id, book);
                return new ResponseEntity<String>("Success" + book.getId() , HttpStatus.OK);

            }
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<String>("Could not update that book, id is not exist in bookstore", HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Delete a book", notes = "Returns success or id of book that could not be removed")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Book deleted")
        ,
	   @ApiResponse(code = 204, message = "Book can't be deleted, not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {

        try {
            if (bookService.checkIfBookExists(id) == false) {
                throw new ResourceNotFoundException();
            } else {
                bookService.deleteBook(id);
                return new ResponseEntity<String>("Success", HttpStatus.OK);
            }
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<String>("\"Could not delete that book, id is not exist in bookstore" + id, HttpStatus.NO_CONTENT);
        }
    }
}
