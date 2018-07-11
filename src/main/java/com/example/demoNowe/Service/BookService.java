/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demoNowe.Service;

import com.example.demoNowe.Model.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author dozyc1
 */

@Service
public class BookService {
	 private static List<Book> books  = new ArrayList<>(Arrays.asList( 
				new Book("Pan Tadeusz","Adam Mickiewicz","Epopeja Narodowa z elementami gawędy szlacheckiej "),
				new Book("Juliusz Słowacki","Kordian", "Dramat Kordian jest polemiką niezdolności do romantycznego czynu spiskowca, obezwładnionego moralną rozterką legalizmu i krytyką postawy Konrada z Dziadów."),
				new Book("Henryk Sienkiewicz", "Ogniem i mieczem", "Pierwsza z trzech powieści historycznych będących częścią Trylogii, pisanej dla pokrzepienia serc"))
				);
				
		public List <Book> getAllBooks(){
			return books;
		}
		
		
		public Book getBook(Long id) {
			for(Book book : books) {
	    		if(book.getId() == id) {
	    			return book;
	    		}
	    	}
			
			return null;
		}

		public void addBook(Book topic) {
			books.add(topic);
			
			
		}

		public boolean updateBook(Long id, Book topic) {
			for(int i =0; i<books.size(); i++) {
				Book t = books.get(i);
				if(t.getId().equals(id)) {
					books.set(i,topic);
					return true ;
				}
			}
                        return false;
		}

		public void deleteBook(Long id) {
			books.removeIf(t -> t.getId().equals(id));
		}
                
                public boolean checkIfBookExists(Long id){
                    for(Book book : books){
                        if(book.getId() == id){
                            return true;
                        }
                    }
                        return false;
                    }
               
}