/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demoNowe.Model;

/**
 *
 * @author dozyc1
 */
public class Book {
    
	public Long id;
	public static Long nextInt =  new Long(0);
	private String title;
	private String author;
	private String description;
	
	
	{
		nextInt++;
		id = nextInt;
	}
	
	public Book() {
	};
	
	public Book(  String title, String name, String description) {
		super();
	
		this.title = title;
		this.author = name;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return author;
	}
	public void setName(String name) {
		this.author = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
