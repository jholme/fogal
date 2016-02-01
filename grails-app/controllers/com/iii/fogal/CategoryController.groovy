package com.iii.fogal

class CategoryController {
    static scaffold = Category
	
	def categoryService
	
	def save() {
		log.debug params
		Category category = new Category(name:params.name, description:params.description, path:params.path)
		category.save()
		forward action:"show", id:category.id
	}
	
	def delete() {
		log.debug params
		Category category = Category.get(params.id)
		Category.withTransaction {
			category.delete(flush:true)
		}
		redirect(uri:'/')
	}
	
}
