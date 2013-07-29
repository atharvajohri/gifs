package com.core

class Gif {
	
	String title
	String link
	String encodedImage
	Double size
	Date dateCreated
	
    static constraints = {
		link url: true
    }
	
	static mapping = {
		encodedImage type: 'text'
	}
}
