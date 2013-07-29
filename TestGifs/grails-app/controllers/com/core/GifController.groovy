package com.core

import grails.converters.JSON

class GifController {

	def utilService
	
    def index() {
		[message: flash.message]
	}
	
	def add(){
		log.info "got request to add ${params.link}..."
		def extractedData = utilService.extractUrlImageData(params.link)
		params.size = extractedData.imageSize
		params.encodedImage = extractedData.encodedImage
		def gif = new Gif(params)
		if (gif.save()){
			log.info "added new gif"
			flash.message = "Saved ${params.title ?: params.url}"
		}else{
			log.info "error while adding gif"
			flash.message = "Could not save ${params.title ?: params.url}"
			gif.errors.each {
				log.info it
			}
		}
		
		redirect action:'index'
	}
	
	def getUrlFileSize(params){
		render utilService.extractUrlImageData(params.url).size
	}
	
	def viewer(){
		
	}
	
	def getGif(){
		def gif;
		if (!params.id){
			Random rand = new Random()
			int gifIndex = rand.nextInt(Gif.count())
			gif = Gif.list(max:1, offset:gifIndex)[0]
		}else{
		 	gif = Gif.get(Long.parseLong(params.id.toString()))
		}
		render gif as JSON
	}
}
